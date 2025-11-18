package pl.edu.pg.eti.kask.ucm.course.service.api;

import jakarta.ejb.Local;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.service.api.Service;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Local
public interface CourseService extends Service<Course, UUID> {

    Optional<List<Course>> findAllByTutor(UUID id);

    Optional<List<Course>> findAllByUniversity(UUID id);
}
