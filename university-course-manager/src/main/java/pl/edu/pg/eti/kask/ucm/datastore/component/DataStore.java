package pl.edu.pg.eti.kask.ucm.datastore.component;

import lombok.extern.java.Log;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.serialization.component.CloningUtility;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log
public class DataStore {

    private final Set<University> universities = new HashSet<>();

    private final Set<Tutor> tutors = new HashSet<>();

    private final Set<Course> courses = new HashSet<>();

    private final CloningUtility cloningUtility;

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
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(entity.getId()));
        }
        tutors.add(cloningUtility.clone(entity));
    }

    public synchronized void updateTutor(Tutor entity) throws IllegalArgumentException{
        if (tutors.removeIf(tutor -> tutor.getId().equals(entity.getId()))){
            tutors.add(cloningUtility.clone(entity));
        }
        else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(entity.getId()));
        }
    }

}
