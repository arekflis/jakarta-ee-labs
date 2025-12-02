package pl.edu.pg.eti.kask.ucm.course.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class CourseModel implements Serializable {

    private UUID id;

    private String name;

    private Long version;
}
