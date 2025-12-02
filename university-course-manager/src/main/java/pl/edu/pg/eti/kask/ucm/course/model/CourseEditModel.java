package pl.edu.pg.eti.kask.ucm.course.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.ucm.course.validation.binding.ValidCourseName;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class CourseEditModel {

    private UUID id;

    @ValidCourseName
    @NotNull(message = "{course.validation.name.required}")
    private String name;

    @Size(min = 10, max = 1000, message = "{course.validation.description.size}")
    private String description;

    @Min(value = 0, message = "{course.validation.passingThreshold.min}")
    @Max(value = 100, message = "{course.validation.passingThreshold.max}")
    private double passingThreshold;

    private Long version;
}
