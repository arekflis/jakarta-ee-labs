package pl.edu.pg.eti.kask.ucm.course.model.function;


import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseCreateModel;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

public class ModelToCourseFunction implements Function<CourseCreateModel, Course> {

    @Override
    public Course apply(CourseCreateModel model) {
        LocalDateTime now = LocalDateTime.now();

        return Course.builder()
                .id(model.getId())
                .createdAt(now)
                .updatedAt(now)
                .name(model.getName())
                .description(model.getDescription())
                .passingThreshold(model.getPassingThreshold())
                .studyType(model.getStudyType())
                .semester(model.getSemester())
                .university(University.builder()
                        .id(model.getUniversity().getId())
                        .build())
                .build();
    }
}
