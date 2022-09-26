package com.travel.around.controller;

import com.travel.around.config.TemplateEngineUtil;
import com.travel.around.model.Restaurant;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.travel.around.service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/restaurant/id/*"})
public class RestaurantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();

        Integer id = Integer.valueOf(request.getPathInfo().substring(1));
        Restaurant restaurant = applicationService.restaurantDao.get(id);

        webContext.setVariable("restaurant", restaurant);
        templateEngine.process("index.html", webContext, response.getWriter());
    }
}
