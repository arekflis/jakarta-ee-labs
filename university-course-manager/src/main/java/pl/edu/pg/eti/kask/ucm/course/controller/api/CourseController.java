package pl.edu.pg.eti.kask.ucm.course.controller.api;

import pl.edu.pg.eti.kask.ucm.course.dto.request.PatchCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.request.PutCourseRequest;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCourseResponse;
import pl.edu.pg.eti.kask.ucm.course.dto.response.GetCoursesResponse;

import java.util.UUID;

public interface CourseController {

    GetCourseResponse getCourseById(UUID id);
    GetCoursesResponse getCoursesByTutor(UUID id);
    GetCoursesResponse getCoursesByUniversity(UUID id);
    GetCoursesResponse getCourses();

    void putCourse(UUID id, PutCourseRequest request);
    void patchCourse(UUID id, PatchCourseRequest request);

    void deleteCourse(UUID id);
}
