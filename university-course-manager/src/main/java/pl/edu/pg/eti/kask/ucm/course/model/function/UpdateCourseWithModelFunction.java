package pl.edu.pg.eti.kask.ucm.course.model.function;

import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseEditModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class UpdateCourseWithModelFunction implements BiFunction<Course, CourseEditModel, Course>, Serializable {

    @Override
    @SneakyThrows
    public Course apply(Course entity, CourseEditModel model) {
        LocalDateTime now = LocalDateTime.now();

        return Course.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(now)
                .name(model.getName())
                .description(model.getDescription())
                .passingThreshold(model.getPassingThreshold())
                .semester(entity.getSemester())
                .studyType(entity.getStudyType())
                //.tutor(entity.getTutor())
                .university(entity.getUniversity())
                .build();
    }
}
