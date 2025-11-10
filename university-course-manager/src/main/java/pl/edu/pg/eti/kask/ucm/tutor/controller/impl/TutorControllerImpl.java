package pl.edu.pg.eti.kask.ucm.tutor.controller.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import pl.edu.pg.eti.kask.ucm.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.ucm.tutor.controller.api.TutorController;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PatchTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorResponse;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorsResponse;
import pl.edu.pg.eti.kask.ucm.tutor.service.api.TutorService;

import java.io.InputStream;
import java.util.UUID;

@RequestScoped
public class TutorControllerImpl implements TutorController {

    private final TutorService service;

    private final DtoFunctionFactory factory;

    @Inject
    public TutorControllerImpl(TutorService service, DtoFunctionFactory factory){
        this.service = service;
        this.factory = factory;
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
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
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
