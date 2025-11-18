package pl.edu.pg.eti.kask.ucm.course.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.edu.pg.eti.kask.ucm.course.dto.request.PatchCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.request.PutCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCourseResponse;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCoursesResponse;

import java.util.UUID;

@Path("")
public interface CourseController {

    @GET
    @Path("/courses")
    @Produces(MediaType.APPLICATION_JSON)
    GetCoursesResponse getCourses();

    @GET
    @Path("/courses/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetCourseResponse getCourseById(@PathParam("id") UUID id);


    @GET
    @Path("/tutors/{id}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    GetCoursesResponse getCoursesByTutor(@PathParam("id") UUID id);

    @GET
    @Path("/universities/{id}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    GetCoursesResponse getCoursesByUniversity(@PathParam("id") UUID id);

    @PUT
    @Path("/universities/{universityId}/courses/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void putCourse(@PathParam("id") UUID id, @PathParam("universityId") UUID universityId, PutCourseRequest request);

    @PATCH
    @Path("/universities/{universityId}/courses/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void patchCourse(@PathParam("id") UUID id, @PathParam("universityId") UUID universityId, PatchCourseRequest request);

    @DELETE
    @Path("/courses/{id}")
    void deleteCourse(@PathParam("id") UUID id);
}
