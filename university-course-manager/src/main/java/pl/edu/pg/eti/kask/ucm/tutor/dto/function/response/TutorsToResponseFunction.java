package pl.edu.pg.eti.kask.ucm.tutor.dto.function.response;

import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorsResponse;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.util.List;
import java.util.function.Function;

public class TutorsToResponseFunction implements Function<List<Tutor>, GetTutorsResponse> {

    @Override
    public GetTutorsResponse apply(List<Tutor> tutors){
        return GetTutorsResponse.builder()
                .tutors(tutors.stream()
                        .map(tutor -> GetTutorsResponse.Tutor.builder()
                                .id(tutor.getId())
                                .createdAt(tutor.getCreatedAt())
                                .name(tutor.getName())
                                .build())
                        .toList())
                .build();
    }
}
