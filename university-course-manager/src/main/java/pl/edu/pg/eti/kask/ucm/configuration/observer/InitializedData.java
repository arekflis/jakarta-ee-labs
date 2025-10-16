package pl.edu.pg.eti.kask.ucm.configuration.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.service.impl.TutorServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class InitializedData {

    private final TutorServiceImpl tutorService;

    private final RequestContextController requestContextController;

    @Inject
    public InitializedData(TutorServiceImpl tutorService, RequestContextController requestContextController) {
        this.tutorService = tutorService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    @SneakyThrows
    private void init() {
        requestContextController.activate();

        LocalDateTime now = LocalDateTime.now();

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

        now = LocalDateTime.now();

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

        now = LocalDateTime.now();

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

        now = LocalDateTime.now();

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

        requestContextController.deactivate();
    }
}
