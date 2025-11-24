package pl.edu.pg.eti.kask.ucm.tutor.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;

@RequestScoped
@Named
public class TutorLogout {

    private final HttpServletRequest request;

    @Inject
    public TutorLogout(HttpServletRequest request) {
        this.request = request;
    }

    @SneakyThrows
    public String logoutAction() {
        request.logout();
        return "/index.xhtml?faces-redirect=true";
    }
}

