package pl.edu.pg.eti.kask.ucm.course.controller.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
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
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class CourseControllerImpl implements CourseController {

    private CourseService courseService;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response){
        this.response = response;
    }

    @Inject
    public CourseControllerImpl(
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public GetCourseResponse getCourseById(UUID id) {
        try {
            return this.courseService.find(id)
                .map(this.factory.courseToResponse())
                .orElseThrow(NotFoundException::new);
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public GetCoursesResponse getCourses() {
        try {
            return this.factory.coursesToResponse().apply(this.courseService.findAll());
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public GetCoursesResponse getCoursesByTutor(UUID id) {
        try {
            return this.courseService.findAllByTutor(id)
                    .map(this.factory.coursesToResponse())
                    .orElseThrow(NotFoundException::new);
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public GetCoursesResponse getCoursesByUniversity(UUID id) {
        try{
            return this.courseService.findAllByUniversity(id)
                    .map(this.factory.coursesToResponse())
                    .orElseThrow(NotFoundException::new);
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        }
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
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex.getCause().getMessage());
            }
            throw ex;
        }
    }

    @Override
    public void patchCourse(UUID id, UUID universityId, PatchCourseRequest request) {
        try {
            Optional<Course> course = this.courseService.find(id);
            if (course.isEmpty()) {
                throw new NotFoundException();
            }

            this.courseService.update(this.factory.updateCourse().apply(course.get(), universityId, request));
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public void deleteCourse(UUID id) {
        try {
            Optional<Course> course = this.courseService.find(id);
            if (course.isEmpty()) {
                throw new NotFoundException();
            }

            this.courseService.delete(id);
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        }
    }
}
