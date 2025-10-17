package pl.edu.pg.eti.kask.ucm.course.entity;

import jakarta.persistence.Entity;
import lombok.*;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Course implements Serializable {

    private String name;

    private String description;

    private StudyType studyType;

    private double passingThreshold;

    private int semester;

    private Tutor tutor;

    private University university;
}
