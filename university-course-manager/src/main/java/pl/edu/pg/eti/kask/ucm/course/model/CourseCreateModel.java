package pl.edu.pg.eti.kask.ucm.course.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.course.validation.binding.ValidCourseName;
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

    @ValidCourseName
    @NotNull(message = "{course.validation.name.required}")
    private String name;

    @Size(min = 10, max = 1000, message = "{course.validation.description.size}")
    private String description;

    @NotNull(message = "{course.validation.studyType.required}")
    private StudyType studyType;

    @Min(value = 0, message = "{course.validation.passingThreshold.min}")
    @Max(value = 100, message = "{course.validation.passingThreshold.max}")
    private double passingThreshold;

    @Min(value = 1, message = "{course.validation.semester.min}")
    @Max(value = 10, message = "{course.validation.semester.max}")
    private int semester;

    @NotNull(message = "{course.validation.university.required}")
    private UniversityModel university;
}
