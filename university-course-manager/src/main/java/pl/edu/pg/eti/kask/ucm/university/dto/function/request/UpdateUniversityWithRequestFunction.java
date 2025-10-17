package pl.edu.pg.eti.kask.ucm.university.dto.function.request;

import pl.edu.pg.eti.kask.ucm.university.dto.request.PatchUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class UpdateUniversityWithRequestFunction implements BiFunction<University, PatchUniversityRequest, University> {

    @Override
    public University apply(University university, PatchUniversityRequest request) {
        LocalDateTime now = LocalDateTime.now();

        return University.builder()
                .id(university.getId())
                .createdAt(university.getCreatedAt())
                .updatedAt(now)
                .name(request.getName())
                .city(university.getCity())
                .numberOfEmployees(request.getNumberOfEmployees())
                .dateOfFoundation(university.getDateOfFoundation())
                .build();
    }

}
