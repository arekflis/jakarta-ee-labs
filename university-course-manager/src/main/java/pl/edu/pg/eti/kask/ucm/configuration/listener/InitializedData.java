package pl.edu.pg.eti.kask.ucm.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.ucm.enums.tutor.TutorRank;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.service.api.TutorService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.InputStream;
import java.util.UUID;

@WebListener
public class InitializedData implements ServletContextListener {

    private TutorService tutorService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        tutorService = (TutorService) event.getServletContext().getAttribute("tutorService");
        init();
    }

    @SneakyThrows
    private void init() {
        LocalDateTime now = LocalDateTime.now();

        Tutor tutor1 = Tutor.builder()
                .id(UUID.randomUUID())
                .createdAt(now)
                .updatedAt(now)
                .name("John")
                .email("johndoe@gmail.com")
                .dateOfBirth(LocalDate.of(1990, 10, 10))
                .tutorRank(TutorRank.LECTURER)
                .avatar(getResourceAsByteArray("/avatar/ju2.jpg"))
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
                .avatar(getResourceAsByteArray("/avatar/klopp-juergen.jpg"))
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
                .avatar(getResourceAsByteArray("/avatar/teacher.jpg"))
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
                .avatar(getResourceAsByteArray("/avatar/teacher2.jpg"))
                .build();

        tutorService.create(tutor1);
        tutorService.create(tutor2);
        tutorService.create(tutor3);
        tutorService.create(tutor4);
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            }
            else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }
}
