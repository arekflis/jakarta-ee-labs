package pl.edu.pg.eti.kask.ucm.course.model.function;

import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class CourseToEditModelFunction implements Function<Course, CourseEditModel>, Serializable {

    @Override
    public CourseEditModel apply(Course entity) {
        return CourseEditModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .passingThreshold(entity.getPassingThreshold())
                .version(entity.getVersion())
                .build();
    }
}
