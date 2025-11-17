package pl.edu.pg.eti.kask.ucm.course.view;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pg.eti.kask.ucm.component.ModelFunctionFactory;
import pl.edu.pg.eti.kask.ucm.course.model.CourseCreateModel;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;
import pl.edu.pg.eti.kask.ucm.university.model.UniversityModel;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ViewScoped
@Named
@NoArgsConstructor(force = true)
public class CourseCreate implements Serializable {

    private CourseService courseService;

    private UniversityService universityService;

    private final ModelFunctionFactory factory;

    @Getter
    private CourseCreateModel course;

    @Getter
    private List<UniversityModel> universities;

    @Getter
    private StudyType[] studyTypes;

    @Inject
    public CourseCreate(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setUniversityService(UniversityService universityService) {
        this.universityService = universityService;
    }

    @EJB
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void init() {
        this.universities = this.universityService.findAll().stream()
                .map(this.factory.universityToModel())
                .collect(Collectors.toList());

        this.course = CourseCreateModel.builder()
                .id(UUID.randomUUID())
                .build();

        this.studyTypes = StudyType.values();
    }

    public String saveAction() {
        this.courseService.create(this.factory.modelToCourse().apply(course));
        return "/course/course_view.xhtml?faces-redirect=true&amp;id=" + course.getId();
    }
}
