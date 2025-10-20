package pl.edu.pg.eti.kask.ucm.course.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class GetCourseResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @SuperBuilder
    @EqualsAndHashCode
    @ToString
    public static class Tutor {

        private UUID id;

        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @SuperBuilder
    @EqualsAndHashCode
    @ToString
    public static class University {

        private UUID id;

        private String name;
    }

    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private String description;

    private StudyType studyType;

    private Double passingThreshold;

    private Integer semester;

    private Tutor tutor;

    private University university;
}
