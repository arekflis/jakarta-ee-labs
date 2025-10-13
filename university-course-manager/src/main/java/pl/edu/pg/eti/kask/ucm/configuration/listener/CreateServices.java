package pl.edu.pg.eti.kask.ucm.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pl.edu.pg.eti.kask.ucm.datastore.component.DataStore;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;
import pl.edu.pg.eti.kask.ucm.tutor.repository.impl.TutorInMemoryRepository;
import pl.edu.pg.eti.kask.ucm.tutor.service.impl.TutorServiceImpl;

@WebListener
public class CreateServices implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource");

        TutorRepository tutorRepository = new TutorInMemoryRepository(dataSource);

        event.getServletContext().setAttribute("tutorService", new TutorServiceImpl(tutorRepository));
    }
}
