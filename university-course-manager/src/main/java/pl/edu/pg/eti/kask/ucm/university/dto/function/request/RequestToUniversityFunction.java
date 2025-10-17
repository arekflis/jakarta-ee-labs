package pl.edu.pg.eti.kask.ucm.university.dto.function.request;

import pl.edu.pg.eti.kask.ucm.university.dto.request.PutUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToUniversityFunction implements BiFunction<UUID, PutUniversityRequest, University> {

    @Override
    public University apply(UUID id, PutUniversityRequest request) {
        LocalDateTime now = LocalDateTime.now();

        return University.builder()
                .id(id)
                .createdAt(now)
                .updatedAt(now)
                .name(request.getName())
                .city(request.getCity())
                .dateOfFoundation(request.getDateOfFoundation())
                .numberOfEmployees(request.getNumberOfEmployees())
                .build();
    }
}
