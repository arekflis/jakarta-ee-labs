package pl.edu.pg.eti.kask.ucm.course.controller.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.ucm.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.ucm.controller.servlet.exception.BadRequestException;
import pl.edu.pg.eti.kask.ucm.controller.servlet.exception.NotFoundException;
import pl.edu.pg.eti.kask.ucm.course.controller.api.CourseController;
import pl.edu.pg.eti.kask.ucm.course.dto.request.PatchCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.request.PutCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCourseResponse;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCoursesResponse;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;

import java.util.UUID;

@RequestScoped
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    private final DtoFunctionFactory factory;

    @Inject
    public CourseControllerImpl(CourseService courseService, DtoFunctionFactory factory) {
        this.courseService = courseService;
        this.factory = factory;
    }

    @Override
    public GetCourseResponse getCourseById(UUID id) {
        return this.courseService.find(id)
                .map(this.factory.courseToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetCoursesResponse getCourses() {
        return this.factory.coursesToResponse().apply(this.courseService.findAll());
    }

    @Override
    public GetCoursesResponse getCoursesByTutor(UUID id) {
        return this.courseService.findAllByTutor(id)
                .map(this.factory.coursesToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetCoursesResponse getCoursesByUniversity(UUID id) {
        return this.courseService.findAllByUniversity(id)
                .map(this.factory.coursesToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putCourse(UUID id, PutCourseRequest request) {
        try {
            this.courseService.create(this.factory.requestToCourse().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchCourse(UUID id, PatchCourseRequest request) {
        this.courseService.find(id).ifPresentOrElse(
                course -> this.courseService.update(this.factory.updateCourse().apply(course, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteCourse(UUID id) {
        this.courseService.find(id).ifPresentOrElse(
                course -> this.courseService.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
