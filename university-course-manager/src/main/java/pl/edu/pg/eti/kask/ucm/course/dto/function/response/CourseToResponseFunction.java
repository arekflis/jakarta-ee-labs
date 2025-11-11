package pl.edu.pg.eti.kask.ucm.course.dto.function.response;

import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCourseResponse;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;

import java.util.function.Function;

public class CourseToResponseFunction implements Function<Course, GetCourseResponse> {

    @Override
    public GetCourseResponse apply(Course course) {
        return GetCourseResponse.builder()
                .id(course.getId())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .name(course.getName())
                .description(course.getDescription())
                .studyType(course.getStudyType())
                .passingThreshold(course.getPassingThreshold())
                .semester(course.getSemester())
                .university(GetCourseResponse.University.builder()
                        .id(course.getUniversity().getId())
                        .name(course.getUniversity().getName())
                        .build())
                .build();

        /*
                        .tutor(GetCourseResponse.Tutor.builder()
                        .id(course.getTutor().getId())
                        .name(course.getTutor().getName())
                        .build())
         */
    }
}
