package pl.edu.pg.eti.kask.ucm.tutor.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PutPasswordRequest {

    private String password;
}
