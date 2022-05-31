package controller;

import config.TemplateEngineUtil;
import model.Hotel;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/hotels"})
public class HotelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();
        Hotel hotel = applicationService.hotelDao.get(1);
        webContext.setVariable("hotel", hotel);
        templateEngine.process("hotels.html", webContext, resp.getWriter());
    }
}