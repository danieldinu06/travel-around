package com.travel.around.controller;

import com.travel.around.config.TemplateEngineUtil;
import com.travel.around.model.Hotel;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.travel.around.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/hotel/id/*"})
public class HotelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();
        Integer id = Integer.valueOf(req.getPathInfo().substring(1));
        Hotel hotel = applicationService.hotelDao.get(id);


        webContext.setVariable("hotel", hotel);
        templateEngine.process("index.html", webContext, resp.getWriter());
    }
}