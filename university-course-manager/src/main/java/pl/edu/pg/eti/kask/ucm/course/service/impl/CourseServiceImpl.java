package pl.edu.pg.eti.kask.ucm.course.service.impl;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.repository.api.CourseRepository;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.entity.TutorRoles;
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

    private final SecurityContext securityContext;

    @Inject
    public CourseServiceImpl(CourseRepository courseRepository,
                             TutorRepository tutorRepository,
                             UniversityRepository universityRepository,
                             SecurityContext securityContext) {
        this.courseRepository = courseRepository;
        this.tutorRepository = tutorRepository;
        this.universityRepository = universityRepository;
        this.securityContext = securityContext;
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public Optional<Course> find(UUID id) {
        if (this.isAdmin()){
            return this.courseRepository.find(id);
        }

        if (this.isOwner(id)) {
            return this.courseRepository.find(id);
        }

        throw new NotFoundException();
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public List<Course> findAll() {
        if (this.isAdmin()) {
            return this.courseRepository.findAll();
        }

        String login = this.getCurrentLogin();

        if (this.tutorRepository.findByLogin(login).isEmpty()) {
            throw new IllegalArgumentException("Tutor does not exists");
        }

        return this.courseRepository.findAllByTutor(this.tutorRepository.findByLogin(login).get());
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public void create(Course entity) {
        if (this.courseRepository.find(entity.getId()).isPresent()) {
            throw new IllegalArgumentException("Course already exists");
        }

        if (this.universityRepository.find(entity.getUniversity().getId()).isEmpty()) {
            throw new IllegalArgumentException("University does not exists");
        }

        Optional<Tutor> tutor = this.tutorRepository.findByLogin(this.getCurrentLogin());
        if (tutor.isEmpty()) {
            throw new IllegalArgumentException("Tutor does not exists");
        }

        entity.setTutor(tutor.get());
        this.courseRepository.create(entity);

    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public void update(Course entity) {
        if (this.universityRepository.find(entity.getUniversity().getId()).isEmpty()) {
            throw new IllegalArgumentException("University does not exists");
        }

        this.courseRepository.update(entity);
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
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

    private boolean isAdmin() {
        return this.securityContext.isCallerInRole(TutorRoles.ADMIN);
    }

    private String getCurrentLogin() {
        return this.securityContext.getCallerPrincipal().getName();
    }

    private boolean isOwner(UUID id) {
        String login = this.getCurrentLogin();

        Optional<Tutor> tutor = this.tutorRepository.findByLogin(login);
        if (tutor.isEmpty()) {
            throw new IllegalArgumentException("Tutor does not exists");
        }

        Optional<Course> course = this.courseRepository.findByIdAndTutor(id, tutor.get());
        return course.isPresent();
    }
}
