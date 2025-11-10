package pl.edu.pg.eti.kask.ucm.course.dto.function.request;

import pl.edu.pg.eti.kask.ucm.course.dto.request.PatchCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.interfaces.TriFunction;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.BiFunction;

public class UpdateCourseWithRequestFunction implements TriFunction<Course, UUID, PatchCourseRequest, Course> {

    @Override
    public Course apply(Course entity, UUID universityId, PatchCourseRequest request) {
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
                .university(University.builder()
                        .id(universityId)
                        .build())
                .build();
    }
}
