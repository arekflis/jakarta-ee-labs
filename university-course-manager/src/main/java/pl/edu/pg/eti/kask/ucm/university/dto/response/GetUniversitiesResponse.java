package pl.edu.pg.eti.kask.ucm.university.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
public class GetUniversitiesResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @SuperBuilder
    @EqualsAndHashCode
    @ToString
    public static class University {

        private UUID id;

        private LocalDateTime createdAt;

        private String name;
    }

    @Singular
    List<University> universities;
}
