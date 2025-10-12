package pl.edu.pg.eti.kask.ucm.tutor.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.entity.baseEntity.BaseEntity;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;

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
public class Tutor extends BaseEntity implements Serializable {

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private TutorRank tutorRank;

    @ToString.Exclude
    private List<Course> courses;
}
