package pl.edu.pg.eti.kask.ucm.tutor.dto.request;

import lombok.*;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PutTutorRequest {

    private String login;

    private String password;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private TutorRank tutorRank;
}
