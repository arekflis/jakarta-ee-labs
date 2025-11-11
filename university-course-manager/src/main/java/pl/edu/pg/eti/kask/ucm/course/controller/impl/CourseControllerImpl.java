package pl.edu.pg.eti.kask.ucm.course.controller.impl;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.java.Log;
import pl.edu.pg.eti.kask.ucm.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.ucm.course.controller.api.CourseController;
import pl.edu.pg.eti.kask.ucm.course.dto.request.PatchCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.request.PutCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCourseResponse;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCoursesResponse;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response){
        this.response = response;
    }

    @Inject
    public CourseControllerImpl(
            CourseService courseService,
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.courseService = courseService;
        this.factory = factory;
        this.uriInfo = uriInfo;
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

    /*
    @Override
    public GetCoursesResponse getCoursesByTutor(UUID id) {
        return this.courseService.findAllByTutor(id)
                .map(this.factory.coursesToResponse())
                .orElseThrow(NotFoundException::new);
    }
    */

    @Override
    public GetCoursesResponse getCoursesByUniversity(UUID id) {
        return this.courseService.findAllByUniversity(id)
                .map(this.factory.coursesToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putCourse(UUID id, UUID universityId, PutCourseRequest request) {
        try {
            this.courseService.create(this.factory.requestToCourse().apply(id, universityId, request));

            throw new WebApplicationException(
                    Response.status(Response.Status.CREATED)
                            .location(uriInfo.getBaseUriBuilder()
                                    .path(CourseController.class)
                                    .path("courses/{id}")
                                    .build(id))
                            .build()
            );
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        } catch (TransactionalException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void patchCourse(UUID id, UUID universityId, PatchCourseRequest request) {
        try {
            this.courseService.find(id).ifPresentOrElse(
                    course -> this.courseService.update(this.factory.updateCourse().apply(course, universityId, request)),
                    () -> {
                        throw new NotFoundException();
                    }
            );
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
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
