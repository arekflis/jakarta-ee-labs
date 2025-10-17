package pl.edu.pg.eti.kask.ucm.university.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class PatchUniversityRequest {

    private String name;

    private int numberOfEmployees;
}
