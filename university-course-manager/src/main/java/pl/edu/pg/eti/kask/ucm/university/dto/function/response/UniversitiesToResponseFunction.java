package pl.edu.pg.eti.kask.ucm.university.dto.function.response;

import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversitiesResponse;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.List;
import java.util.function.Function;

public class UniversitiesToResponseFunction implements Function<List<University>, GetUniversitiesResponse> {

    public GetUniversitiesResponse apply(List<University> universities) {
        return GetUniversitiesResponse.builder()
                .universities(universities.stream()
                        .map(university -> GetUniversitiesResponse.University.builder()
                                .id(university.getId())
                                .createdAt(university.getCreatedAt())
                                .name(university.getName())
                                .build())
                        .toList())
                .build();
    }
}
