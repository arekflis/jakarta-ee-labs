package pl.edu.pg.eti.kask.ucm.course.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@SuperBuilder
@ToString
public class PutCourseRequest {

    private String name;

    private String description;

    private StudyType studyType;

    private Double passingThreshold;

    private Integer semester;

    private UUID tutor;

    private UUID university;
}
