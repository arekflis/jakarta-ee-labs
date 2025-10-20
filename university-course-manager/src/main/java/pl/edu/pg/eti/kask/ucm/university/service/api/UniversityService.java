package pl.edu.pg.eti.kask.ucm.university.service.api;

import pl.edu.pg.eti.kask.ucm.service.api.Service;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.List;
import java.util.UUID;

public interface UniversityService extends Service<University, UUID> {

    List<University> findByCity(String city);
}
