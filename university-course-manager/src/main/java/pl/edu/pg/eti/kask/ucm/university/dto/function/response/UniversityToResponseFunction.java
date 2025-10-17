package pl.edu.pg.eti.kask.ucm.university.dto.function.response;

import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversityResponse;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.function.Function;

public class UniversityToResponseFunction implements Function<University, GetUniversityResponse> {

    @Override
    public GetUniversityResponse apply(University university) {
        return GetUniversityResponse.builder()
                .id(university.getId())
                .createdAt(university.getCreatedAt())
                .updatedAt(university.getUpdatedAt())
                .name(university.getName())
                .dateOfFoundation(university.getDateOfFoundation())
                .city(university.getCity())
                .numberOfEmployees(university.getNumberOfEmployees())
                .build();
    }
}
