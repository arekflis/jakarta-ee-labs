package pl.edu.pg.eti.kask.ucm.tutor.dto.function.request;

import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.entity.TutorRoles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToTutorFunction implements BiFunction<UUID, PutTutorRequest, Tutor> {

    @Override
    public Tutor apply(UUID id, PutTutorRequest request){
        LocalDateTime now = LocalDateTime.now();

        return Tutor.builder()
                .id(id)
                .createdAt(now)
                .updatedAt(now)
                .login(request.getLogin())
                .password(request.getPassword())
                .name(request.getName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .tutorRank(request.getTutorRank())
                .roles(List.of(TutorRoles.USER))
                .build();
    }
}
