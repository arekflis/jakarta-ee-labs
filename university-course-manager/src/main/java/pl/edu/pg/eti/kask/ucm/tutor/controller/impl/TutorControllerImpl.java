package pl.edu.pg.eti.kask.ucm.tutor.controller.impl;

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
import pl.edu.pg.eti.kask.ucm.tutor.controller.api.TutorController;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PatchTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorResponse;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorsResponse;
import pl.edu.pg.eti.kask.ucm.tutor.service.api.TutorService;

import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class TutorControllerImpl implements TutorController {

    private TutorService service;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response){
        this.response = response;
    }

    @Inject
    public TutorControllerImpl(
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo)
    {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setTutorService(TutorService tutorService) {
        this.service = tutorService;
    }

    @Override
    public GetTutorsResponse getTutors() {
        return this.factory.tutorsToResponse().apply(this.service.findAll());
    }

    @Override
    public GetTutorResponse getTutorById(UUID id) {
        return this.service.find(id)
                .map(this.factory.tutorToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetTutorResponse getTutorByEmail(String email) {
        return this.service.findByEmail(email)
                .map(this.factory.tutorToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetTutorResponse getTutorByLogin(String login) {
        return this.service.findByLogin(login)
                .map(this.factory.tutorToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteTutor(UUID id) {
        this.service.find(id).ifPresentOrElse(
                tutor -> this.service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void putTutor(UUID id, PutTutorRequest request) {
        try {
            this.service.create(this.factory.requestToTutor().apply(id, request));

            throw new WebApplicationException(
                    Response.status(Response.Status.CREATED)
                            .location(uriInfo.getBaseUriBuilder()
                                    .path(TutorController.class)
                                    .path("tutors/{id}")
                                    .build(id))
                            .build()
            );
        }  catch (IllegalArgumentException ex) {
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
    public void patchTutor(UUID id, PatchTutorRequest request) {
        this.service.find(id).ifPresentOrElse(
                tutor -> this.service.update(this.factory.updateTutor().apply(tutor, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public byte[] getAvatar(UUID id) {
        service.find(id).orElseThrow(NotFoundException::new);
        return this.service.getAvatar(id);
    }

    @Override
    public void putAvatar(UUID id, InputStream is) {
        this.service.find(id).ifPresentOrElse(
                tutor -> service.putAvatar(id, is),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteAvatar(UUID id) {
        this.service.find(id).ifPresentOrElse(
                tutor -> service.deleteAvatar(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void patchAvatar(UUID id, InputStream is) {
        this.service.find(id).ifPresentOrElse(
                tutor -> service.patchAvatar(id, is),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
