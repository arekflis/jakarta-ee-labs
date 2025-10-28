package pl.edu.pg.eti.kask.ucm.course.model.function;

import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseDetailsModel;

import java.util.function.Function;

public class CourseDetailsToModelFunction implements Function<Course, CourseDetailsModel> {

    @Override
    public CourseDetailsModel apply(Course entity) {
        return CourseDetailsModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .name(entity.getName())
                .description(entity.getDescription())
                .studyType(entity.getStudyType())
                .passingThreshold(entity.getPassingThreshold())
                .semester(entity.getSemester())
                .build();
    }
}
