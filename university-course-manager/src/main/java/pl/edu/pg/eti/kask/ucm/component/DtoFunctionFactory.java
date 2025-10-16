package pl.edu.pg.eti.kask.ucm.component;

import jakarta.enterprise.context.ApplicationScoped;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.request.RequestToTutorFunction;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.request.UpdateTutorWithRequestFunction;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.response.TutorToResponseFunction;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.response.TutorsToResponseFunction;

@ApplicationScoped
public class DtoFunctionFactory {

    public TutorToResponseFunction tutorToResponse(){
        return new TutorToResponseFunction();
    }

    public TutorsToResponseFunction tutorsToResponse(){
        return new TutorsToResponseFunction();
    }

    public UpdateTutorWithRequestFunction updateTutor(){
        return new UpdateTutorWithRequestFunction();
    }

    public RequestToTutorFunction requestToTutor(){
        return new RequestToTutorFunction();
    }
}
