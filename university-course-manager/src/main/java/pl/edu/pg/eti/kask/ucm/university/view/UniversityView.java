package pl.edu.pg.eti.kask.ucm.university.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.ucm.component.ModelFunctionFactory;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.model.UniversityModel;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Named
@ViewScoped
public class UniversityView implements Serializable {

    private final UniversityService universityService;

    private final CourseService courseService;

    private final ModelFunctionFactory factory;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private UniversityModel university;

    @Inject
    public UniversityView(UniversityService universityService,
                           CourseService courseService,
                           ModelFunctionFactory factory) {
        this.courseService = courseService;
        this.factory = factory;
        this.universityService = universityService;
    }

    public void init() throws IOException {
        Optional<University> university = this.universityService.find(id);
        if (university.isPresent()) {
            this.university = this.factory.universityToModel().apply(university.get());

            this.courseService.findAllByUniversity(id)
                    .ifPresent(courses ->
                            this.university.setCourses(
                                    courses.stream()
                                            .map(this.factory.courseToModel())
                                            .toList()
                            ));
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "University not found");
        }
    }

    public String deleteCourse(UUID courseId) {
        this.courseService.delete(courseId);
        return "university_view?faces-redirect=true&amp;id=" + id;
    }
}
