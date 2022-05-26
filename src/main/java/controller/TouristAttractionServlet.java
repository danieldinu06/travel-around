package controller;

import config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        String title = "Tourist Attractions";

        //TODO: add attributes to display the HTML Tourist Attraction template
        //Assignee - Razvan


        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        /*
        * Aici vom seta variabilele contextului nostru de web.
        * Adica tourisitAttraction in felul asta:
        *
        *   webContext.setVariable("touristAttractions, ceva);
        *
        *   Acel CEVA de mai sus va fi un obiect de tip TouristAttractionDaoJDBC asupra caruia vom folosi functia getAll.
        */

        templateEngine.process("index.html", webContext);
    }
}
