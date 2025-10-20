package pl.edu.pg.eti.kask.ucm.course.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@SuperBuilder
public class GetCoursesResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @EqualsAndHashCode
    @ToString
    @SuperBuilder
    public static class Course {
        private UUID id;

        private LocalDateTime createdAt;

        private String name;
    }

    @Singular
    List<Course> courses;
}
