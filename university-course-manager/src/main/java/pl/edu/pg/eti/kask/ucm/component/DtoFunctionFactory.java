package pl.edu.pg.eti.kask.ucm.component;

import jakarta.enterprise.context.ApplicationScoped;
import pl.edu.pg.eti.kask.ucm.course.dto.function.request.RequestToCourseFunction;
import pl.edu.pg.eti.kask.ucm.course.dto.function.request.UpdateCourseWithRequestFunction;
import pl.edu.pg.eti.kask.ucm.course.dto.function.response.CourseToResponseFunction;
import pl.edu.pg.eti.kask.ucm.course.dto.function.response.CoursesToResponseFunction;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.request.RequestToTutorFunction;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.request.UpdateTutorWithRequestFunction;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.response.TutorToResponseFunction;
import pl.edu.pg.eti.kask.ucm.tutor.dto.function.response.TutorsToResponseFunction;
import pl.edu.pg.eti.kask.ucm.university.dto.function.request.RequestToUniversityFunction;
import pl.edu.pg.eti.kask.ucm.university.dto.function.request.UpdateUniversityWithRequestFunction;
import pl.edu.pg.eti.kask.ucm.university.dto.function.response.UniversitiesToResponseFunction;
import pl.edu.pg.eti.kask.ucm.university.dto.function.response.UniversityToResponseFunction;

@ApplicationScoped
public class DtoFunctionFactory {

    /* Tutor functions */
    public TutorToResponseFunction tutorToResponse(){
        return new TutorToResponseFunction();
    }

    public TutorsToResponseFunction tutorsToResponse(){
        return new TutorsToResponseFunction();
    }

    public UpdateTutorWithRequestFunction updateTutor(){
        return new UpdateTutorWithRequestFunction();
    }

    public RequestToTutorFunction requestToTutor(){
        return new RequestToTutorFunction();
    }

    /* University functions */
    public UniversityToResponseFunction universityToResponse(){
        return new UniversityToResponseFunction();
    }

    public UniversitiesToResponseFunction universitiesToResponse(){
        return new UniversitiesToResponseFunction();
    }

    public UpdateUniversityWithRequestFunction updateUniversity(){
        return new UpdateUniversityWithRequestFunction();
    }

    public RequestToUniversityFunction requestToUniversity(){
        return new RequestToUniversityFunction();
    }

    /* Course functions */
    public CourseToResponseFunction courseToResponse(){
        return new CourseToResponseFunction();
    }

    public CoursesToResponseFunction coursesToResponse(){
        return new CoursesToResponseFunction();
    }

    public UpdateCourseWithRequestFunction updateCourse(){
        return new UpdateCourseWithRequestFunction();
    }

    public RequestToCourseFunction requestToCourse(){
        return new RequestToCourseFunction();
    }
}
