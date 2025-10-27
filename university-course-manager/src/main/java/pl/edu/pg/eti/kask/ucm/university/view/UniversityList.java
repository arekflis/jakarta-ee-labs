package pl.edu.pg.eti.kask.ucm.university.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.edu.pg.eti.kask.ucm.component.ModelFunctionFactory;
import pl.edu.pg.eti.kask.ucm.university.model.UniversitiesModel;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

@RequestScoped
@Named
public class UniversityList {

    private final UniversityService service;

    private UniversitiesModel universities;

    private final ModelFunctionFactory factory;

    @Inject
    public UniversityList(UniversityService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public UniversitiesModel getUniversities() {
        if (universities == null) {
            universities = this.factory.universitiesToModel().apply(this.service.findAll());
        }
        return universities;
    }

    public String deleteAction(UniversitiesModel.University university) {
        this.service.delete(university.getId());
        return "university_list?faces-redirect=true";
    }
}
