package pl.edu.pg.eti.kask.ucm.configuration.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;
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

    private final CourseService courseService;

    private final RequestContextController requestContextController;

    @Inject
    public InitializedData(TutorService tutorService, UniversityService universityService,
                           RequestContextController requestContextController, CourseService courseService) {
        this.tutorService = tutorService;
        this.universityService = universityService;
        this.courseService = courseService;
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
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .createdAt(now)
                .updatedAt(now)
                .name("John")
                .email("johndoe@gmail.com")
                .dateOfBirth(LocalDate.of(1990, 10, 10))
                .tutorRank(TutorRank.LECTURER)
                .avatar("ju2.jpg")
                .build();

        Tutor tutor2 = Tutor.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .createdAt(now)
                .updatedAt(now)
                .name("Mark")
                .email("markdoe@gmail.com")
                .dateOfBirth(LocalDate.of(1991, 10, 20))
                .tutorRank(TutorRank.ASSISTANT)
                .avatar("klopp-juergen.jpg")
                .build();

        Tutor tutor3 = Tutor.builder()
                .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                .createdAt(now)
                .updatedAt(now)
                .name("Peter")
                .email("peter@pg.edu.pl")
                .dateOfBirth(LocalDate.of(1940, 1, 10))
                .tutorRank(TutorRank.PROFESSOR)
                .avatar("teacher.jpg")
                .build();

        Tutor tutor4 = Tutor.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
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

        University university1 = University.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                .createdAt(now)
                .updatedAt(now)
                .name("Gdansk University of Technology")
                .numberOfEmployees(120)
                .city("Gdansk")
                .dateOfFoundation(LocalDate.of(1892, 10, 10))
                .build();

        University university2 = University.builder()
                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
                .createdAt(now)
                .updatedAt(now)
                .name("Medical University of Gdansk")
                .numberOfEmployees(220)
                .city("Gdansk")
                .dateOfFoundation(LocalDate.of(1920, 1, 19))
                .build();

        University university3 = University.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"))
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

        Course course1 = Course.builder()
                .id(UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"))
                .createdAt(now)
                .updatedAt(now)
                .name("Object-Oriented Programming")
                .description("Object-Oriented Programming")
                .studyType(StudyType.ENGINEERING)
                .passingThreshold(3.0)
                .semester(2)
                .university(university1)
                .tutor(tutor1)
                .build();

        Course course2 = Course.builder()
                .id(UUID.fromString("f08ef7e3-7f2a-4378-b1fb-2922d730c70d"))
                .createdAt(now)
                .updatedAt(now)
                .name("Algorithms and Data Structure")
                .description("Algorithms and Data Structure")
                .studyType(StudyType.ENGINEERING)
                .passingThreshold(3.5)
                .semester(2)
                .university(university1)
                .tutor(tutor1)
                .build();

        Course course3 = Course.builder()
                .id(UUID.fromString("ff327e8a-77c0-4f9b-90a2-89e16895d1e1"))
                .createdAt(now)
                .updatedAt(now)
                .name("Economy")
                .description("Economy")
                .studyType(StudyType.MASTER)
                .passingThreshold(4.0)
                .semester(2)
                .university(university2)
                .tutor(tutor2)
                .build();

        this.courseService.create(course1);
        this.courseService.create(course2);
        this.courseService.create(course3);

        requestContextController.deactivate();
    }
}
