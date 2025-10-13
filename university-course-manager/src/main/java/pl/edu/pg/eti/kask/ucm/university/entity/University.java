package pl.edu.pg.eti.kask.ucm.university.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class University implements Serializable {

    @Id
    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private LocalDate dateOfFoundation;

    private String city;

    private int numberOfEmployees;

    @ToString.Exclude
    private List<Course> courses;
}
