package config;

import service.ApplicationService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class Initializer implements ServletContextListener {
    ApplicationService applicationService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationService = ApplicationService.getInstance();
    }
}
