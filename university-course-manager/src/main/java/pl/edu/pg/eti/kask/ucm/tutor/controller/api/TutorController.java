package pl.edu.pg.eti.kask.ucm.tutor.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PatchTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorResponse;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorsResponse;

import java.io.InputStream;
import java.util.UUID;

@Path("")
public interface TutorController {

    @GET
    @Path("/tutors")
    @Produces(MediaType.APPLICATION_JSON)
    GetTutorsResponse getTutors();

    @GET
    @Path("/tutors/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetTutorResponse getTutorById(@PathParam("id") UUID id);

    @GET
    @Path("/tutors/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    GetTutorResponse getTutorByEmail(@PathParam("email") String email);

    @PUT
    @Path("/tutors/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putTutor(@PathParam("id") UUID id, PutTutorRequest request);

    @PATCH
    @Path("/tutors/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchTutor(@PathParam("id") UUID id, PatchTutorRequest request);

    @PATCH
    @Path("/tutors/{id}")
    void deleteTutor(@PathParam("id") UUID id);

    @GET
    @Path("/tutors/{id}/avatar")
    @Produces({"images/png", "image/jpeg"})
    @SneakyThrows
    byte[] getAvatar(@PathParam("id") UUID id);

    @PUT
    @Path("/tutors/{id}/avatar")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    void putAvatar(@PathParam("id") UUID id, InputStream is);

    @DELETE
    @Path("/tutors/{id}/avatar")
    void deleteAvatar(UUID id);

    @PATCH
    @Path("/tutors/{id}/avatar")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    void patchAvatar(@PathParam("id") UUID id, InputStream is);
}
