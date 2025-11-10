package pl.edu.pg.eti.kask.ucm.datastore.component;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.serialization.component.CloningUtility;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {

    private final Set<University> universities = new HashSet<>();

    private final Set<Tutor> tutors = new HashSet<>();

    private final Set<Course> courses = new HashSet<>();

    private final CloningUtility cloningUtility;

    @Inject
    public DataStore(CloningUtility cloningUtility){
        this.cloningUtility = cloningUtility;
    }

    public synchronized List<Tutor> findAllTutors(){
        return tutors.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createTutor(Tutor entity) throws IllegalArgumentException {
        if (tutors.stream().anyMatch(tutor -> tutor.getId().equals(entity.getId()))) {
            throw new IllegalArgumentException("The tutor id \"%s\" is not unique".formatted(entity.getId()));
        }
        tutors.add(cloningUtility.clone(entity));
    }

    public synchronized void updateTutor(Tutor entity) throws IllegalArgumentException{
        if (tutors.removeIf(tutor -> tutor.getId().equals(entity.getId()))){
            tutors.add(cloningUtility.clone(entity));
        }
        else {
            throw new IllegalArgumentException("The tutor with id \"%s\" does not exist".formatted(entity.getId()));
        }
    }

    public synchronized void deleteTutor(UUID id) throws IllegalArgumentException{
        if (!tutors.removeIf(tutor -> tutor.getId().equals(id))) {
            throw new IllegalArgumentException(("The tutor with id \"%s\" does not exist".formatted(id)));
        }
    }

    public synchronized List<University> findAllUniversities() {
        return universities.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createUniversity(University entity) throws IllegalArgumentException {
        if (universities.stream().anyMatch(university -> university.getId().equals(entity.getId()))) {
            throw new IllegalArgumentException("The university id \"%s\" is not unique".formatted(entity.getId()));
        }
        universities.add(cloningUtility.clone(entity));
    }

    public synchronized void updateUniversity(University entity) throws IllegalArgumentException {
        if (universities.removeIf(university -> university.getId().equals(entity.getId()))) {
            universities.add(cloningUtility.clone(entity));
        }
        else {
            throw new IllegalArgumentException("The university with id \"%s\" does not exist".formatted(entity.getId()));
        }
    }

    public synchronized void deleteUniversity(UUID id) throws IllegalArgumentException {
        if (!universities.removeIf(university -> university.getId().equals(id))) {
            throw new IllegalArgumentException("The university with id \"%s\" does not exist".formatted(id));
        } else {
            courses.removeIf(course -> course.getUniversity().getId().equals(id));
        }
    }

    public synchronized List<Course> findAllCourses() {
        return courses.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createCourse(Course value) throws IllegalArgumentException {
        if (courses.stream().anyMatch(course -> course.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The course id \"%s\" is not unique".formatted(value.getId()));
        }
        Course entity = cloneWithRelationships(value);
        courses.add(entity);
    }

    public synchronized void updateCourse(Course value) throws IllegalArgumentException {
        Course entity = cloneWithRelationships(value);
        if (courses.removeIf(course -> course.getId().equals(value.getId()))) {
            courses.add(entity);
        }
        else {
            throw new IllegalArgumentException("The course with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    public synchronized void deleteCourse(UUID id) throws IllegalArgumentException {
        if (!courses.removeIf(course -> course.getId().equals(id))) {
            throw new IllegalArgumentException("The course with id \"%s\" does not exist".formatted(id));
        }
    }

    private Course cloneWithRelationships(Course value) {
        Course entity = cloningUtility.clone(value);

        if (entity.getTutor() != null) {
            entity.setTutor(tutors.stream()
                    .filter(tutor -> tutor.getId().equals(value.getTutor().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No tutor with id \"%s\".".formatted(value.getTutor().getId()))));
        }

        if (entity.getUniversity() != null) {
            entity.setUniversity(universities.stream()
                    .filter(university -> university.getId().equals(value.getUniversity().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No university with id \"%s\".".formatted(value.getUniversity().getId()))));
        }

        return entity;
    }
}
