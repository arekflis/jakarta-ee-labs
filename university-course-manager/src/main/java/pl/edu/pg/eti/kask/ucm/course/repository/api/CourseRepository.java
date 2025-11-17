package pl.edu.pg.eti.kask.ucm.course.repository.api;

import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.repository.api.Repository;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends Repository<Course, UUID> {

    List<Course> findAllByTutor(Tutor tutor);

    List<Course> findAllByUniversity(University university);
}
