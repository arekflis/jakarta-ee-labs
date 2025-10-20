package pl.edu.pg.eti.kask.ucm.university.repository.api;

import pl.edu.pg.eti.kask.ucm.repository.api.Repository;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.List;
import java.util.UUID;

public interface UniversityRepository extends Repository<University, UUID> {

    List<University> findByCity(String city);
}
