package pl.edu.pg.eti.kask.ucm.controller.servlet;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.edu.pg.eti.kask.ucm.tutor.controller.api.TutorController;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PatchTutorRequest;
import pl.edu.pg.eti.kask.ucm.tutor.dto.request.PutTutorRequest;
import pl.edu.pg.eti.kask.ucm.university.controller.api.UniversityController;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PatchUniversityRequest;
import pl.edu.pg.eti.kask.ucm.university.dto.request.PutUniversityRequest;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ApiServlet extends HttpServlet {

    private final TutorController tutorController;

    private final UniversityController universityController;

    public static final class Paths {
        public static final String API = "/api";
    }

    public static final class Patterns {
        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        public static final Pattern TUTORS = Pattern.compile("/tutors/?");

        public static final Pattern TUTOR = Pattern.compile("/tutor/(%s)".formatted(UUID.pattern()));

        public static final Pattern TUTOR_AVATAR = Pattern.compile("/tutor/(%s)/avatar".formatted(UUID.pattern()));

        public static final Pattern UNIVERSITY = Pattern.compile("/university/(%s)".formatted(UUID.pattern()));

        public static final Pattern UNIVERSITIES = Pattern.compile("/universities/?");
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public ApiServlet(TutorController tutorController, UniversityController universityController) {
        this.tutorController = tutorController;
        this.universityController = universityController;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")){
            doPatch(request, response);
        }
        else {
            super.service(request, response);
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)){
            if (path.matches(Patterns.TUTOR.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUUID(Patterns.TUTOR, path);
                response.getWriter().write(jsonb.toJson(this.tutorController.getTutorById(uuid)));
                return;
            } else if (path.matches(Patterns.TUTORS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(this.tutorController.getTutors()));
                return;
            } else if (path.matches(Patterns.TUTOR_AVATAR.pattern())) {
                response.setContentType("image/png");
                UUID uuid = extractUUID(Patterns.TUTOR_AVATAR, path);
                byte[] avatar = this.tutorController.getAvatar(uuid);
                response.setContentLength(avatar.length);
                response.getOutputStream().write(avatar);
                return;
            } else if (path.matches(Patterns.UNIVERSITY.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUUID(Patterns.UNIVERSITY, path);
                response.getWriter().write(jsonb.toJson(this.universityController.getUniversityById(uuid)));
                return;
            } else if (path.matches(Patterns.UNIVERSITIES.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(this.universityController.getUniversities()));
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)){
            if (path.matches(Patterns.TUTOR.pattern())) {
                UUID uuid = extractUUID(Patterns.TUTOR, path);
                this.tutorController.putTutor(uuid, jsonb.fromJson(request.getReader(), PutTutorRequest.class));
                response.addHeader("Location", createUrl(request, Paths.API, "tutors", uuid.toString()));
                return;
            } else if (path.matches(Patterns.TUTOR_AVATAR.pattern())) {
                UUID uuid = extractUUID(Patterns.TUTOR_AVATAR, path);
                this.tutorController.putAvatar(uuid, request.getPart("avatar").getInputStream());
                return;
            } else if (path.matches(Patterns.UNIVERSITY.pattern())) {
                UUID uuid = extractUUID(Patterns.UNIVERSITY, path);
                this.universityController.putUniversity(uuid, jsonb.fromJson(request.getReader(), PutUniversityRequest.class));
                response.addHeader("Location", createUrl(request, Paths.API, "universities", uuid.toString()));
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)){
            if (path.matches(Patterns.TUTOR.pattern())) {
                UUID uuid = extractUUID(Patterns.TUTOR, path);
                this.tutorController.deleteTutor(uuid);
                return;
            } else if (path.matches(Patterns.TUTOR_AVATAR.pattern())) {
                UUID uuid = extractUUID(Patterns.TUTOR_AVATAR, path);
                this.tutorController.deleteAvatar(uuid);
                return;
            } else if (path.matches(Patterns.UNIVERSITY.pattern())) {
                UUID uuid = extractUUID(Patterns.UNIVERSITY, path);
                this.universityController.deleteUniversity(uuid);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @SuppressWarnings("RedundantThrows")
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)){
            if (path.matches(Patterns.TUTOR.pattern())) {
                UUID uuid = extractUUID(Patterns.TUTOR, path);
                this.tutorController.patchTutor(uuid, jsonb.fromJson(request.getReader(), PatchTutorRequest.class));
                return;
            } else if (path.matches(Patterns.TUTOR_AVATAR.pattern())) {
                UUID uuid = extractUUID(Patterns.TUTOR_AVATAR, path);
                this.tutorController.patchAvatar(uuid, request.getPart("avatar").getInputStream());
                return;
            } else if (path.matches(Patterns.UNIVERSITY.pattern())) {
                UUID uuid = extractUUID(Patterns.UNIVERSITY, path);
                this.universityController.patchUniversity(uuid, jsonb.fromJson(request.getReader(), PatchUniversityRequest.class));
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }

    private static UUID extractUUID(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path");
    }

    public static String createUrl(HttpServletRequest request, String... paths) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath());
        for (String path: paths) {
            builder.append("/")
                    .append(path, path.startsWith("/") ? 1 : 0, path.endsWith("/") ? path.length() - 1 : path.length());
        }
        return builder.toString();
    }
}
