package pl.edu.pg.eti.kask.ucm.course.model.converter;

import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.ucm.component.ModelFunctionFactory;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.model.UniversityModel;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.util.Optional;
import java.util.UUID;

@FacesConverter(forClass = UniversityModel.class, managed = true)
public class UniversityModelConverter implements Converter<UniversityModel> {

    private UniversityService service;

    private final ModelFunctionFactory factory;

    @Inject
    public UniversityModelConverter(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setUniversityService(UniversityService universityService) {
        this.service = universityService;
    }

    @Override
    public UniversityModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<University> university = this.service.find(UUID.fromString(value));
        return university.map(this.factory.universityToModel()).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, UniversityModel value) {
        return value == null ? "" : value.getId().toString();
    }
}
