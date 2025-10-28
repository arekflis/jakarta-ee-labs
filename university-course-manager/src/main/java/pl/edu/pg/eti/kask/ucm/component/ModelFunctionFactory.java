package pl.edu.pg.eti.kask.ucm.component;

import jakarta.enterprise.context.ApplicationScoped;
import pl.edu.pg.eti.kask.ucm.course.model.function.*;
import pl.edu.pg.eti.kask.ucm.university.model.function.UniversitiesToModelFunction;
import pl.edu.pg.eti.kask.ucm.university.model.function.UniversityToModelFunction;

@ApplicationScoped
public class ModelFunctionFactory {

    public UniversitiesToModelFunction universitiesToModel() {
        return new UniversitiesToModelFunction();
    }

    public UniversityToModelFunction universityToModel() {
        return new UniversityToModelFunction(courseToModel());
    }

    public CourseToModelFunction courseToModel() {
        return new CourseToModelFunction();
    }

    public CourseDetailsToModelFunction courseDetailsToModel() {
        return new CourseDetailsToModelFunction();
    }

    public ModelToCourseFunction modelToCourse() {
        return new ModelToCourseFunction();
    }

    public CourseToEditModelFunction courseToEditModel() {
        return new CourseToEditModelFunction();
    }

    public UpdateCourseWithModelFunction updateCourseWithModel() {
        return new UpdateCourseWithModelFunction();
    }
}
