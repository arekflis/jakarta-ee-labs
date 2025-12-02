package pl.edu.pg.eti.kask.ucm.course.model.function;

import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseModel;

import java.io.Serializable;
import java.util.function.Function;

public class CourseToModelFunction implements Function<Course, CourseModel>, Serializable {

    @Override
    public CourseModel apply(Course entity) {
        return CourseModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .version(entity.getVersion())
                .build();
    }
}
