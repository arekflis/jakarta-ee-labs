package pl.edu.pg.eti.kask.ucm.course.view;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.ucm.component.ModelFunctionFactory;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseEditModel;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewScoped
@Named
public class CourseEdit implements Serializable {

    private CourseService service;

    private final ModelFunctionFactory factory;

    private final FacesContext facesContext;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private CourseEditModel course;

    @Inject
    public CourseEdit(ModelFunctionFactory factory, FacesContext facesContext) {
        this.factory = factory;
        this.facesContext = facesContext;
    }

    @EJB
    public void setCourseService(CourseService courseService) {
        this.service = courseService;
    }

    public void init() throws IOException {
        Optional<Course> entity = this.service.find(id);
        if (entity.isPresent()) {
            this.course = this.factory.courseToEditModel().apply(entity.get());
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
        }
    }

    public String saveAction() throws IOException {
        String userEnteredName = course.getName();
        String userEnteredDescription = course.getDescription();
        Double userEnteredPassingThreshold = course.getPassingThreshold();
        
        try {
            this.service.update(this.factory.updateCourseWithModel().apply(this.service.find(id).orElseThrow(), course));
            return "/course/course_view.xhtml?faces-redirect=true&amp;id=" + id;
        } catch (EJBException ex) {
            if (ex.getCause() instanceof OptimisticLockException) {
                init();
                addOptimisticLockMessages(userEnteredName, userEnteredDescription, userEnteredPassingThreshold);
                return null;
            }
            throw ex;
        } catch (OptimisticLockException ex) {
            init();
            addOptimisticLockMessages(userEnteredName, userEnteredDescription, userEnteredPassingThreshold);
            return null;
        }
    }

    private void addOptimisticLockMessages(String userEnteredName, String userEnteredDescription, Double userEnteredPassingThreshold) {
        ResourceBundle courseMessages = ResourceBundle.getBundle(
            "org.example.courses.view.i18n.messages",
            facesContext.getViewRoot().getLocale()
        );
        
        facesContext.addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            courseMessages.getString("course.edit.error.optimistic.lock.summary"),
            courseMessages.getString("course.edit.error.optimistic.lock.detail")
        ));
        
        facesContext.addMessage("editForm:name", new FacesMessage(
            FacesMessage.SEVERITY_WARN,
            MessageFormat.format(courseMessages.getString("course.edit.error.optimistic.lock.yourChange"), userEnteredName),
            courseMessages.getString("course.edit.error.optimistic.lock.valueOverwritten")
        ));
        
        if (userEnteredDescription != null) {
            facesContext.addMessage("editForm:description", new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                MessageFormat.format(courseMessages.getString("course.edit.error.optimistic.lock.yourChange"), userEnteredDescription),
                courseMessages.getString("course.edit.error.optimistic.lock.valueOverwritten")
            ));
        }
        
        if (userEnteredPassingThreshold != null) {
            facesContext.addMessage("editForm:passingThreshold", new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                MessageFormat.format(courseMessages.getString("course.edit.error.optimistic.lock.yourChange"), userEnteredPassingThreshold),
                courseMessages.getString("course.edit.error.optimistic.lock.valueOverwritten")
            ));
        }
    }
}
