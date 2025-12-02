package pl.edu.pg.eti.kask.ucm.course.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

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

    private String name;

    private String description;

    private Double passingThreshold;

    private Long version;
}
