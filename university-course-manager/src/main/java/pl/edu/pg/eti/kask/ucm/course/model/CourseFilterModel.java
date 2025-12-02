package pl.edu.pg.eti.kask.ucm.course.model;

import lombok.*;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class CourseFilterModel implements Serializable {

    private String name;

    private String description;

    private StudyType studyType;

    private Double passingThreshold;

    private Integer semester;
}

