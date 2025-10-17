package pl.edu.pg.eti.kask.ucm.university.controller.api;

import pl.edu.pg.eti.kask.ucm.university.dto.request.PatchUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PutUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversitiesResponse;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversityResponse;

import java.util.UUID;

public interface UniversityController {

    GetUniversitiesResponse getUniversities();
    GetUniversityResponse getUniversityById(UUID id);
    GetUniversitiesResponse getUniversitiesByCity(String city);

    void putUniversity(UUID id, PutUniversityRequest request);
    void patchUniversity(UUID id, PatchUniversityRequest request);

    void deleteUniversity(UUID id);
}
