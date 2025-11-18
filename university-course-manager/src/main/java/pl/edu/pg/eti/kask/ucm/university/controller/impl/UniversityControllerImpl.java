package pl.edu.pg.eti.kask.ucm.university.controller.impl;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import pl.edu.pg.eti.kask.ucm.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.ucm.tutor.entity.TutorRoles;
import pl.edu.pg.eti.kask.ucm.university.controller.api.UniversityController;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PatchUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PutUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversitiesResponse;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversityResponse;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class UniversityControllerImpl implements UniversityController {

    private UniversityService service;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response){
        this.response = response;
    }

    @Inject
    public UniversityControllerImpl(
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setUniversityService(UniversityService universityService) {
        this.service = universityService;
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public GetUniversitiesResponse getUniversities() {
        return this.factory.universitiesToResponse().apply(this.service.findAll());
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public GetUniversityResponse getUniversityById(UUID id) {
        return this.service.find(id)
                .map(this.factory.universityToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public GetUniversitiesResponse getUniversitiesByCity(String city) {
        return Optional.of(this.service.findByCity(city))
                .filter(list -> !list.isEmpty())
                .map(this.factory.universitiesToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @SneakyThrows
    @RolesAllowed(TutorRoles.ADMIN)
    public void putUniversity(UUID id, PutUniversityRequest request) {
        try {
            this.service.create(this.factory.requestToUniversity().apply(id, request));

            throw new WebApplicationException(
                    Response.status(Response.Status.CREATED)
                            .location(uriInfo.getBaseUriBuilder()
                                    .path(UniversityController.class)
                                    .path("universities/{id}")
                                    .build(id))
                            .build()
            );
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
            throw new BadRequestException(ex.getMessage());
        } catch (EJBAccessException ex) {
            throw new ForbiddenException();
        }
    }

    @Override
    @RolesAllowed(TutorRoles.ADMIN)
    public void patchUniversity(UUID id, PatchUniversityRequest request) {
        this.service.find(id).ifPresentOrElse(
                university -> this.service.update(this.factory.updateUniversity().apply(university, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    @RolesAllowed(TutorRoles.ADMIN)
    public void deleteUniversity(UUID id) {
        try {
            this.service.find(id).ifPresentOrElse(
                    university -> this.service.delete(id),
                    () -> {
                        throw new NotFoundException();
                    }
            );
        } catch (EJBAccessException ex) {
            throw new ForbiddenException();
        }
    }
}
