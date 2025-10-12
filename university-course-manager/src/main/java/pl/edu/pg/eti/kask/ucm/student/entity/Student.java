package pl.edu.pg.eti.kask.ucm.student.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.entity.baseEntity.BaseEntity;
import pl.edu.pg.eti.kask.ucm.enums.student.StudyType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Student extends BaseEntity implements Serializable {

    private String name;

    private LocalDate dateOfBirth;

    private String index;

    private StudyType studyType;

    @ToString.Exclude
    private List<Course> courses;
}
