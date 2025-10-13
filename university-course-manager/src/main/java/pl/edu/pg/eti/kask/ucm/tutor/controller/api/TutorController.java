package pl.edu.pg.eti.kask.ucm.tutor.controller.api;

import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PatchTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorResponse;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorsResponse;

import java.util.UUID;

public interface TutorController {

    GetTutorsResponse getTutors();
    GetTutorResponse getTutorById(UUID id);
    GetTutorResponse getTutorByEmail(String email);

    void putTutor(UUID id, PutTutorRequest request);
    void patchTutor(UUID id, PatchTutorRequest request);

    void deleteTutor(UUID id);

}
