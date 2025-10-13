package pl.edu.pg.eti.kask.ucm.tutor.dto.function.request;

import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToTutorFunction implements BiFunction<UUID, PutTutorRequest, Tutor> {

    @Override
    public Tutor apply(UUID id, PutTutorRequest request){
        LocalDate now = LocalDate.now();

        return Tutor.builder()
                .id(id)
                .createdAt(now)
                .updatedAt(now)
                .name(request.getName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .tutorRank(request.getTutorRank())
                .build();
    }
}
