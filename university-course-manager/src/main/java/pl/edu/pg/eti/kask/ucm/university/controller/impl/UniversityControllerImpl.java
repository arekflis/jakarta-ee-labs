package pl.edu.pg.eti.kask.ucm.university.controller.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.ucm.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.ucm.controller.servlet.exception.BadRequestException;
import pl.edu.pg.eti.kask.ucm.controller.servlet.exception.NotFoundException;
import pl.edu.pg.eti.kask.ucm.university.controller.api.UniversityController;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PatchUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PutUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversitiesResponse;
import pl.edu.pg.eti.kask.ucm.university.dto.response.GetUniversityResponse;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class UniversityControllerImpl implements UniversityController {

    private final UniversityService service;

    private final DtoFunctionFactory factory;

    @Inject
    public UniversityControllerImpl(UniversityService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetUniversitiesResponse getUniversities() {
        return this.factory.universitiesToResponse().apply(this.service.findAll());
    }

    @Override
    public GetUniversityResponse getUniversityById(UUID id) {
        return this.service.find(id)
                .map(this.factory.universityToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetUniversitiesResponse getUniversitiesByCity(String city) {
        return Optional.of(this.service.findByCity(city))
                .filter(list -> !list.isEmpty())
                .map(this.factory.universitiesToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putUniversity(UUID id, PutUniversityRequest request) {
        try {
            this.service.create(this.factory.requestToUniversity().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchUniversity(UUID id, PatchUniversityRequest request) {
        this.service.find(id).ifPresentOrElse(
                university -> this.service.update(this.factory.updateUniversity().apply(university, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteUniversity(UUID id) {
        this.service.find(id).ifPresentOrElse(
                university -> this.service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
