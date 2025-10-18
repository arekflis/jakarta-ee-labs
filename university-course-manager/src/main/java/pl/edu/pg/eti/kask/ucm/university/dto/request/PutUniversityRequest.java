package pl.edu.pg.eti.kask.ucm.university.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class PutUniversityRequest {

    private String name;

    private LocalDate dateOfFoundation;

    private String city;

    private Integer numberOfEmployees;
}
