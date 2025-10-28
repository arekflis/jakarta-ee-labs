package pl.edu.pg.eti.kask.ucm.course.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;
import pl.edu.pg.eti.kask.ucm.university.model.UniversityModel;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class CourseCreateModel {

    private UUID id;

    private String name;

    private String description;

    private StudyType studyType;

    private Double passingThreshold;

    private Integer semester;

    private UniversityModel university;
}
