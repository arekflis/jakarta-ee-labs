package pl.edu.pg.eti.kask.ucm.tutor.dto.function.response;

import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorResponse;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.util.function.Function;

public class TutorToResponseFunction implements Function<Tutor, GetTutorResponse> {

    @Override
    public GetTutorResponse apply(Tutor tutor){
        return GetTutorResponse.builder()
                .id(tutor.getId())
                .createdAt(tutor.getCreatedAt())
                .updatedAt(tutor.getUpdatedAt())
                .login(tutor.getLogin())
                .name(tutor.getName())
                .dateOfBirth(tutor.getDateOfBirth())
                .email(tutor.getEmail())
                .tutorRank(tutor.getTutorRank())
                .build();
    }
}
