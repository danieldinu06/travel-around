package config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;


@WebListener
public class Initializer implements ServletContextListener {
    ApplicationService applicationService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationService = new ApplicationService();

    }
}
