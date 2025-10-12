package pl.edu.pg.eti.kask.ucm.course.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.entity.baseEntity.BaseEntity;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Course extends BaseEntity implements Serializable {

    private String name;

    private String description;

    private StudyType studyType;

    private double passingThreshold;

    private int semester;
}
