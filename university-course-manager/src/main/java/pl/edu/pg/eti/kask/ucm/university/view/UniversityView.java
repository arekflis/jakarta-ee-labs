package pl.edu.pg.eti.kask.ucm.university.view;

import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.ucm.component.ModelFunctionFactory;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseFilterModel;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.model.UniversityModel;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Named
@ViewScoped
public class UniversityView implements Serializable {

    private UniversityService universityService;

    private CourseService courseService;

    private final ModelFunctionFactory factory;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private UniversityModel university;

    @Getter
    private CourseFilterModel filter;

    @Getter
    private StudyType[] studyTypes;

    @Inject
    public UniversityView(ModelFunctionFactory factory) {
        this.factory = factory;
        this.filter = CourseFilterModel.builder().build();
        this.studyTypes = StudyType.values();
    }

    @EJB
    public void setUniversityService(UniversityService universityService) {
        this.universityService = universityService;
    }

    @EJB
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void init() throws IOException {
        Optional<University> university = this.universityService.find(id);
        if (university.isPresent()) {
            this.university = this.factory.universityToModel().apply(university.get());

            applyFilter();
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "University not found");
        }
    }

    public void deleteCourse(UUID courseId) {
        this.courseService.delete(courseId);
        applyFilter();
    }

    public void applyFilter() {
        Optional<List<Course>> coursesOpt =
            this.courseService.findAllByUniversityWithFilter(id, filter);
        if (coursesOpt.isPresent()) {
            this.university.setCourses(
                coursesOpt.get().stream()
                    .map(this.factory.courseToModel())
                    .toList()
            );
        } else {
            this.university.setCourses(java.util.Collections.emptyList());
        }
    }

    public void clearFilter() {
        this.filter = CourseFilterModel.builder().build();
        applyFilter();
    }
}
