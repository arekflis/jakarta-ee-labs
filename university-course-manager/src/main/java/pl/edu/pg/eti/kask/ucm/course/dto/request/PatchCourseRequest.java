package pl.edu.pg.eti.kask.ucm.course.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@ToString
@EqualsAndHashCode
public class PatchCourseRequest {

    private String name;

    private String description;

    private Double passingThreshold;
}
