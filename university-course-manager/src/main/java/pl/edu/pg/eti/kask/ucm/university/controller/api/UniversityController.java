package pl.edu.pg.eti.kask.ucm.university.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PatchUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PutUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversitiesResponse;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversityResponse;

import java.util.UUID;

@Path("")
public interface UniversityController {

    @GET
    @Path("/universities")
    @Produces(MediaType.APPLICATION_JSON)
    GetUniversitiesResponse getUniversities();

    @GET
    @Path("/universities/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetUniversityResponse getUniversityById(@PathParam("id") UUID id);

    @GET
    @Path("/universities/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    GetUniversitiesResponse getUniversitiesByCity(@PathParam("city") String city);

    @PUT
    @Path("/universities/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putUniversity(@PathParam("id") UUID id, PutUniversityRequest request);

    @PATCH
    @Path("/universities/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchUniversity(@PathParam("id") UUID id, PatchUniversityRequest request);

    @DELETE
    @Path("/universities/{id}")
    void deleteUniversity(@PathParam("id") UUID id);
}
