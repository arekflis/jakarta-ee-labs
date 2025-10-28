package pl.edu.pg.eti.kask.ucm.university.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.repository.api.CourseRepository;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    private final CourseRepository courseRepository;

    @Inject
    public UniversityServiceImpl(UniversityRepository universityRepository,
                                 CourseRepository courseRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<University> find(UUID id) {
        return this.universityRepository.find(id);
    }

    @Override
    public List<University> findAll() {
        return this.universityRepository.findAll();
    }

    @Override
    public List<University> findByCity(String city) {
        return this.universityRepository.findByCity(city);
    }

    @Override
    public void create(University entity) {
        this.universityRepository.create(entity);
    }

    @Override
    public void update(University entity) {
        this.universityRepository.update(entity);
    }

    @Override
    public void delete(UUID id) {
        Optional<University> universityOpt = this.find(id);

        if (universityOpt.isPresent()) {
            University university = universityOpt.get();

            List<Course> courses = this.courseRepository.findAllByUniversity(university);

            for (Course course : courses) {
                this.courseRepository.delete(course.getId());
            }

            this.universityRepository.delete(id);
        }
    }
}
