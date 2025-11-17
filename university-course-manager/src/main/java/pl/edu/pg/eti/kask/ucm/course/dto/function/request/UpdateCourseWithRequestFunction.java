package pl.edu.pg.eti.kask.ucm.course.dto.function.request;

import pl.edu.pg.eti.kask.ucm.course.dto.request.PatchCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class UpdateCourseWithRequestFunction implements BiFunction<Course, PatchCourseRequest, Course> {

    @Override
    public Course apply(Course entity, PatchCourseRequest request) {
        LocalDateTime now = LocalDateTime.now();

        return Course.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(now)
                .name(request.getName())
                .description(request.getDescription())
                .semester(entity.getSemester())
                .studyType(entity.getStudyType())
                .passingThreshold(request.getPassingThreshold())
                .tutor(entity.getTutor())
                .university(entity.getUniversity())
                .build();
    }
}
