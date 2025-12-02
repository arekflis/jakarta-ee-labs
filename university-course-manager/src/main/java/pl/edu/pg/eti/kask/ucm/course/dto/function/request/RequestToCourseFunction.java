package pl.edu.pg.eti.kask.ucm.course.dto.function.request;

import pl.edu.pg.eti.kask.ucm.course.dto.request.PutCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.interfaces.TriFunction;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.UUID;

public class RequestToCourseFunction implements TriFunction<UUID, UUID, PutCourseRequest, Course> {

    @Override
    public Course apply(UUID id, UUID universityId, PutCourseRequest request) {
        return Course.builder()
                .id(id)
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
