package pl.edu.pg.eti.kask.ucm.configuration.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.service.api.TutorService;
import pl.edu.pg.eti.kask.ucm.tutor.service.impl.TutorServiceImpl;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class InitializedData {

    private final TutorService tutorService;

    private final UniversityService universityService;

    private final RequestContextController requestContextController;

    @Inject
    public InitializedData(TutorService tutorService, UniversityService universityService, RequestContextController requestContextController) {
        this.tutorService = tutorService;
        this.universityService = universityService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    @SneakyThrows
    private void init() {
        requestContextController.activate();

        LocalDateTime now = LocalDateTime.now();
        this.initializeTutors(now);
        this.initializeUniversities(now);

        requestContextController.deactivate();
    }

    private void initializeTutors(LocalDateTime now) {
        Tutor tutor1 = Tutor.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("John")
                .email("johndoe@gmail.com")
                .dateOfBirth(LocalDate.of(1990, 10, 10))
                .tutorRank(TutorRank.LECTURER)
                .avatar("ju2.jpg")
                .build();

        Tutor tutor2 = Tutor.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("Mark")
                .email("markdoe@gmail.com")
                .dateOfBirth(LocalDate.of(1991, 10, 20))
                .tutorRank(TutorRank.ASSISTANT)
                .avatar("klopp-juergen.jpg")
                .build();

        Tutor tutor3 = Tutor.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("Peter")
                .email("peter@pg.edu.pl")
                .dateOfBirth(LocalDate.of(1940, 1, 10))
                .tutorRank(TutorRank.PROFESSOR)
                .avatar("teacher.jpg")
                .build();

        Tutor tutor4 = Tutor.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("George")
                .email("george@gmail.com")
                .dateOfBirth(LocalDate.of(2000, 10, 10))
                .tutorRank(TutorRank.LECTURER)
                .avatar("teacher2.jpg")
                .build();

        tutorService.create(tutor1);
        tutorService.create(tutor2);
        tutorService.create(tutor3);
        tutorService.create(tutor4);
    }

    private void initializeUniversities(LocalDateTime now) {
        University university1 = University.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("Gdansk University of Technology")
                .numberOfEmployees(120)
                .city("Gdansk")
                .dateOfFoundation(LocalDate.of(1892, 10, 10))
                .build();

        University university2 = University.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("Medical University of Gdansk")
                .numberOfEmployees(220)
                .city("Gdansk")
                .dateOfFoundation(LocalDate.of(1920, 1, 19))
                .build();

        University university3 = University.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("Warsaw University of Technology")
                .numberOfEmployees(241)
                .city("Warsaw")
                .dateOfFoundation(LocalDate.of(1901, 12, 21))
                .build();

        this.universityService.create(university1);
        this.universityService.create(university2);
        this.universityService.create(university3);
    }
}
