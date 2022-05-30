package controller;

import config.TemplateEngineUtil;
import model.TouristAttraction;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "touristAttractionServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class TouristAttractionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        String title = "Tourist Attractions";
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();
        TouristAttraction touristAttraction = applicationService.touristAttractionDao.get(1);
        webContext.setVariable("touristAttraction", touristAttraction);
        templateEngine.process("index.html", webContext);


    }
}
