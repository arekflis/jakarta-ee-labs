package pl.edu.pg.eti.kask.ucm.course.service.impl;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.repository.api.CourseRepository;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TutorRepository tutorRepository;

    private final UniversityRepository universityRepository;

    @Inject
    public CourseServiceImpl(CourseRepository courseRepository, TutorRepository tutorRepository,
                                UniversityRepository universityRepository) {
        this.courseRepository = courseRepository;
        this.tutorRepository = tutorRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<Course> find(UUID id) {
        return this.courseRepository.find(id);
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public void create(Course entity) {
        if (this.courseRepository.find(entity.getId()).isPresent()) {
            throw new IllegalArgumentException("Course already exists");
        }

        if (this.universityRepository.find(entity.getUniversity().getId()).isEmpty()) {
            throw new IllegalArgumentException("University does not exists");
        }

        if (this.tutorRepository.find(entity.getTutor().getId()).isEmpty()) {
            throw new IllegalArgumentException("Tutor does not exists");
        }

        this.courseRepository.create(entity);
    }

    @Override
    public void update(Course entity) {
        this.courseRepository.update(entity);
    }

    @Override
    public void delete(UUID id) {
        this.courseRepository.delete(id);
    }

    @Override
    public Optional<List<Course>> findAllByUniversity(UUID id) {
        if (this.universityRepository.find(id).isEmpty()) {
            throw new IllegalArgumentException("University does not exists");
        }

        return this.universityRepository.find(id)
                .map(courseRepository::findAllByUniversity);
    }


    @Override
    public Optional<List<Course>> findAllByTutor(UUID id) {
        if (this.tutorRepository.find(id).isEmpty()) {
            throw new IllegalArgumentException("Tutor does not exists");
        }

        return this.tutorRepository.find(id)
                .map(courseRepository::findAllByTutor);
    }
}
