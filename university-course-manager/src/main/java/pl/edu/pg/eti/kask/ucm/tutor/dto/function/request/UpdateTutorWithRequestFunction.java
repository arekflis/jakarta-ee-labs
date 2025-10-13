package pl.edu.pg.eti.kask.ucm.tutor.dto.function.request;

import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PatchTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class UpdateTutorWithRequestFunction implements BiFunction<Tutor, PatchTutorRequest, Tutor> {

    @Override
    public Tutor apply(Tutor entity, PatchTutorRequest request){
        LocalDateTime now = LocalDateTime.now();

        return Tutor.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(now)
                .name(entity.getName())
                .email(request.getEmail())
                .tutorRank(request.getTutorRank())
                .dateOfBirth(entity.getDateOfBirth())
                .avatar(entity.getAvatar())
                .build();
    }
}
