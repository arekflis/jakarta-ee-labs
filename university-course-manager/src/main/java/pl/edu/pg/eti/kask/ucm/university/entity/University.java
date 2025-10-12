package pl.edu.pg.eti.kask.ucm.university.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.entity.baseEntity.BaseEntity;
import pl.edu.pg.eti.kask.ucm.enums.university.City;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class University extends BaseEntity implements Serializable {

    private String name;

    private int yearOfFoundation;

    private City city;

    private int numberOfEmployees;

    @ToString.Exclude
    private List<Course> courses;
}
