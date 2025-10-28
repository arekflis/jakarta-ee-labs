package pl.edu.pg.eti.kask.ucm.university.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.course.model.CourseModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class UniversityModel implements Serializable {

    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private LocalDate dateOfFoundation;

    private String city;

    private Integer numberOfEmployees;

    @Singular
    List<CourseModel> courses;
}
