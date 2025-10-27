package pl.edu.pg.eti.kask.ucm.university.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode
@ToString
public class UniversitiesModel implements Serializable {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @SuperBuilder
    @EqualsAndHashCode
    @ToString
    public static class University {

        private UUID id;

        private String name;
    }

    @Singular
    List<University> universities;
}
