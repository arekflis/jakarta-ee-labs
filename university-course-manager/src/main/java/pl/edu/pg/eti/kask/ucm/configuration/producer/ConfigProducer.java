package pl.edu.pg.eti.kask.ucm.configuration.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

@ApplicationScoped
public class ConfigProducer {

    @Inject
    private ServletContext servletContext;

    @Produces
    @AvatarPath
    public String produceAvatarPath() {
        String value = servletContext.getInitParameter("avatar.resources.path");
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException("Missing 'avatar.resources.path' context param in web.xml");
        }
        return value;
    }
}
