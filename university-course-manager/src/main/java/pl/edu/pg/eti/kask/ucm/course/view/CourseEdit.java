package pl.edu.pg.eti.kask.ucm.course.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.ucm.component.ModelFunctionFactory;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseEditModel;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class CourseEdit implements Serializable {

    private final CourseService service;

    private final ModelFunctionFactory factory;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private CourseEditModel course;

    @Inject
    public CourseEdit(CourseService service,
                      ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
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

    public String saveAction() {
        this.service.update(this.factory.updateCourseWithModel().apply(this.service.find(id).orElseThrow(), course));
        return "/course/course_view.xhtml?faces-redirect=true&amp;id=" + id;
    }
}
