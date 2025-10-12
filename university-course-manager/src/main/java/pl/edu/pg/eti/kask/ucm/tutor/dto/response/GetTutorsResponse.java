package pl.edu.pg.eti.kask.ucm.tutor.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GetTutorsResponse {

    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @Builder
    @ToString
    @EqualsAndHashCode
    public static class Tutor {
        private UUID id;

        private LocalDate createdAt;

        private String name;
    }

    @Singular
    List<Tutor> tutors;
}
