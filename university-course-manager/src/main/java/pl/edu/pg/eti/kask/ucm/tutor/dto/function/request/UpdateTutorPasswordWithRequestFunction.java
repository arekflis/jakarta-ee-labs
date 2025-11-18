package pl.edu.pg.eti.kask.ucm.tutor.dto.function.request;

import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutPasswordRequest;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.util.function.BiFunction;

public class UpdateTutorPasswordWithRequestFunction implements BiFunction<Tutor, PutPasswordRequest, Tutor> {

    @Override
    public Tutor apply(Tutor entity, PutPasswordRequest request){
        return Tutor.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .login(entity.getLogin())
                .password(request.getPassword())
                .name(entity.getName())
                .email(entity.getEmail())
                .tutorRank(entity.getTutorRank())
                .dateOfBirth(entity.getDateOfBirth())
                .avatar(entity.getAvatar())
                .roles(entity.getRoles())
                .build();
    }
}
