package pl.edu.pg.eti.kask.ucm.university.entity;

import jakarta.persistence.*;
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
@Table(name = "universities")
public class University implements Serializable {

    @Id
    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private LocalDate dateOfFoundation;

    private String city;

    private Integer numberOfEmployees;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Course> courses;
}
