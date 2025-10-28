package pl.edu.pg.eti.kask.ucm.course.model.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import pl.edu.pg.eti.kask.ucm.enums.course.StudyType;

@FacesConverter(forClass = StudyType.class, managed = true)
public class StudyTypeConverter implements Converter<StudyType> {

    @Override
    public StudyType getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        return StudyType.valueOf(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, StudyType value) {
        return value == null ? "" : value.name();
    }
}
