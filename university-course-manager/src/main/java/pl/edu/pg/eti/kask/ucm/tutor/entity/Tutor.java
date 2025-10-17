package pl.edu.pg.eti.kask.ucm.tutor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Tutor implements Serializable {

    @Id
    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private TutorRank tutorRank;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String avatar;
}
