package pl.edu.pg.eti.kask.ucm.course.dto.function.response;

import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCoursesResponse;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;

import java.util.List;
import java.util.function.Function;

public class CoursesToResponseFunction implements Function<List<Course>, GetCoursesResponse> {

    @Override
    public GetCoursesResponse apply(List<Course> courses) {
        return GetCoursesResponse.builder()
                .courses(courses.stream()
                        .map(course -> GetCoursesResponse.Course.builder()
                            .id(course.getId())
                            .createdAt(course.getCreatedAt())
                            .name(course.getName())
                            .build())
                        .toList())
                .build();
    }
}
