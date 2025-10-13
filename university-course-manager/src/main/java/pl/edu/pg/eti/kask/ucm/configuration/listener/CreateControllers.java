package pl.edu.pg.eti.kask.ucm.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pl.edu.pg.eti.kask.ucm.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.ucm.tutor.controller.impl.TutorControllerImpl;
import pl.edu.pg.eti.kask.ucm.tutor.service.impl.TutorServiceImpl;

@WebListener
public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        TutorServiceImpl tutorService = (TutorServiceImpl) event.getServletContext().getAttribute("tutorService");

        event.getServletContext().setAttribute("tutorController", new TutorControllerImpl(
                tutorService,
                new DtoFunctionFactory()
        ));
    }
}
