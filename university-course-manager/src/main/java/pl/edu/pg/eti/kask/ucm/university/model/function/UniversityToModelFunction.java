package pl.edu.pg.eti.kask.ucm.university.model.function;

import pl.edu.pg.eti.kask.ucm.course.model.function.CourseToModelFunction;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.model.UniversityModel;

import java.io.Serializable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniversityToModelFunction implements Function<University, UniversityModel>, Serializable {

    private final CourseToModelFunction courseToModelFunction;

    public UniversityToModelFunction(CourseToModelFunction courseToModelFunction) {
        this.courseToModelFunction = courseToModelFunction;
    }

    @Override
    public UniversityModel apply(University entity){
        return UniversityModel.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .name(entity.getName())
                .city(entity.getCity())
                .dateOfFoundation(entity.getDateOfFoundation())
                .numberOfEmployees(entity.getNumberOfEmployees())
                .courses(entity.getCourses() == null ?
                        java.util.List.of() :
                        entity.getCourses().stream()
                                .map(courseToModelFunction)
                                .collect(Collectors.toList()))
                .build();
    }


}
