package pl.edu.pg.eti.kask.ucm.tutor.controller.impl;

import pl.edu.pg.eti.kask.ucm.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.ucm.controller.servlet.exception.BadRequestException;
import pl.edu.pg.eti.kask.ucm.controller.servlet.exception.NotFoundException;
import pl.edu.pg.eti.kask.ucm.tutor.controller.api.TutorController;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PatchTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorResponse;
import pl.edu.pg.eti.kask.ucm.tutor.dto.response.GetTutorsResponse;
import pl.edu.pg.eti.kask.ucm.tutor.service.impl.TutorServiceImpl;

import java.util.UUID;

public class TutorControllerImpl implements TutorController {

    private final TutorServiceImpl service;

    private final DtoFunctionFactory factory;

    public TutorControllerImpl(TutorServiceImpl service, DtoFunctionFactory factory){
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
}
