package pl.edu.pg.eti.kask.ucm.tutor.dto.response;

import lombok.*;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class GetTutorResponse {

    private UUID id;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private TutorRank tutorRank;
}
