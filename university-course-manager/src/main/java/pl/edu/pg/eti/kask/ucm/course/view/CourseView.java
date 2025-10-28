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
import pl.edu.pg.eti.kask.ucm.course.model.CourseDetailsModel;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Named
@ViewScoped
public class CourseView implements Serializable {

    private final CourseService courseService;

    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private CourseDetailsModel course;

    @Getter
    private UUID universityId;

    @Inject
    public CourseView(CourseService courseService,
                      ModelFunctionFactory factory) {
        this.courseService = courseService;
        this.factory = factory;
    }

    public void init() throws IOException {
        Optional<Course> entity = this.courseService.find(id);
        if (entity.isPresent()) {
            this.course = this.factory.courseDetailsToModel().apply(entity.get());
            this.universityId = entity.get().getUniversity().getId();
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
        }
    }
}
