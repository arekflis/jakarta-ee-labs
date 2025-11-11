package pl.edu.pg.eti.kask.ucm.course.dto.function.request;

import pl.edu.pg.eti.kask.ucm.course.dto.request.PutCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.interfaces.TriFunction;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToCourseFunction implements TriFunction<UUID, UUID, PutCourseRequest, Course> {

    @Override
    public Course apply(UUID id, UUID universityId, PutCourseRequest request) {
        LocalDateTime now = LocalDateTime.now();

        return Course.builder()
                .id(id)
                .createdAt(now)
                .updatedAt(now)
                .name(request.getName())
                .description(request.getDescription())
                .passingThreshold(request.getPassingThreshold())
                .studyType(request.getStudyType())
                .semester(request.getSemester())
                .university(University.builder()
                        .id(universityId)
                        .build())
                .build();
    }
}
