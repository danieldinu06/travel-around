package controller;

import config.TemplateEngineUtil;
import model.Hotel;
import model.Restaurant;
import model.TouristAttraction;
import model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/id/*"})
public class TouristAttractionServlet extends HttpServlet {
    HttpSession httpSession;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();
        Integer id = Integer.valueOf(request.getPathInfo().substring(1));
        TouristAttraction touristAttraction = applicationService.touristAttractionDao.get(id);
        List<Hotel> hotels = applicationService.hotelDao.getHotelsByTouristAttraction(touristAttraction.getId());
        List<Restaurant> restaurants = applicationService.restaurantDao.getAllByTouristAttraction(touristAttraction.getId());

        httpSession = request.getSession(true);
        User user = (User) httpSession.getAttribute("user");

        if (user == null)
            response.sendRedirect(request.getContextPath() + "/login");

        webContext.setVariable("touristAttraction", touristAttraction);
        webContext.setVariable("hotels", hotels);
        webContext.setVariable("restaurants", restaurants);
        if (user != null) {
            webContext.setVariable("username", user.getName());
        }
        templateEngine.process("index.html", webContext, response.getWriter());
    }
}
