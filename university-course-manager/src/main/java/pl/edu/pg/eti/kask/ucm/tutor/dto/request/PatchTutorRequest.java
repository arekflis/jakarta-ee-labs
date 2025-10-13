package pl.edu.pg.eti.kask.ucm.tutor.dto.request;

import lombok.*;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PatchTutorRequest {

    private String email;

    private TutorRank tutorRank;
}
