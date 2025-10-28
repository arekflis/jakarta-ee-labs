package pl.edu.pg.eti.kask.ucm.course.model;

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
public class CourseDetailsModel {

    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private String description;

    private StudyType studyType;

    private Double passingThreshold;

    private Integer semester;
}
