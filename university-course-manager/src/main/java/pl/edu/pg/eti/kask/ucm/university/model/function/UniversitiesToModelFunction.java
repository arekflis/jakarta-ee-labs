package pl.edu.pg.eti.kask.ucm.university.model.function;

import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.model.UniversitiesModel;

import java.util.List;
import java.util.function.Function;

public class UniversitiesToModelFunction implements Function<List<University>, UniversitiesModel> {

    @Override
    public UniversitiesModel apply(List<University> entity) {
        return UniversitiesModel.builder()
                .universities(entity.stream()
                        .map(university -> UniversitiesModel.University.builder()
                                .id(university.getId())
                                .name(university.getName())
                                .build())
                        .toList())
                .build();
    }
}
