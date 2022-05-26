package controller;

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




        printWriter.println(
                "\"<html>\\n\" +\n" +
                        "                        \"<head>\" +\n" +
                        "                        \"  <title>\" + title + \"</title>\" +\n" +
                        "                        \"  <link rel=\\\"stylesheet\\\" type=\\\"text/css\\\" href='/static/css/site.css' />\" +\n" +
                        "                        \"  <title>Hacker com.codecool.hackernews.models.News</title>\\n\" +\n" +
                        "                        \"<link rel=\\\"stylesheet\\\" href=\\\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\\\">\\n\" +\n" +
                        "                        \"<script src=\\\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\\\"></script>\\n\" +\n" +
                        "                        \"<script src=\\\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\\\"></script>\\n\" +\n" +
                        "                        \"<script src=\\\"static/js/main.js\\\" defer></script>\\n\" +\n" +
                        "                        \"</head>\" +\n" +
                        "                        \"</head>\\n\" +\n" +
                        "                        \"<body>\\n\" +\n" +
                        "                        \"<nav class=\\\"navbar navbar-inverse\\\">\\n\" +\n" +
                        "                        \"    <div class=\\\"container-fluid\\\">\\n\" +\n" +
                        "                        \"        <div class=\\\"navbar-header\\\">\\n\" +\n" +
                        "                        \"            <a class=\\\"navbar-brand\\\" href=\\\"/\\\">\" + title + \"</a>\\n\" +\n" +
                        "                        \"        </div>\\n\" +\n" +
                        "                        \"        <ul class=\\\"nav navbar-nav\\\">\\n\" +\n" +
                        "                        \"            <li><a href=\\\"/\\\">\" + hacksonNews + \"</a></li>\\n\" +\n" +
                        "                        \"            <li><a href=\\\"/topNews\\\">\" + topNews + \"</a></li>\\n\" +\n" +
                        "                        \"            <li><a href=\\\"/newest\\\">\"+ newest +\"</a></li>\\n\" +\n" +
                        "                        \"            <li><a href=\\\"/jobs\\\">\"+ jobs +\"</a></li>\\n\" +\n" +
                        "                        \"        </ul>\\n\" +\n" +
                        "                        \"    </div>\\n\" +\n" +
                        "                        \"</nav>\" +\n" +
                        "                        \"<h1 align = \\\"center\\\">\" + hacksonNews + \"</h1>\\n\" +\n" +
                        "                        \"<nav aria-label=\\\"Page navigation example\\\" class=\\\"pagination\\\">\\n\" +\n" +
                        "                        \"  <ul class=\\\"pagination\\\">\\n\" +\n" +
                        "                        \"    <li class=\\\"page-item\\\"><a class=\\\"page-link\\\" href=\\\"#\\\"> << </a></li>\\n\" +\n" +
                        "                        \"    <li class=\\\"page-item\\\"><a class=\\\"page-link\\\" href=\\\"#\\\">1</a></li>\\n\" +\n" +
                        "                        \"    <li class=\\\"page-item\\\"><a class=\\\"page-link\\\" href=\\\"#\\\">2</a></li>\\n\" +\n" +
                        "                        \"    <li class=\\\"page-item\\\"><a class=\\\"page-link\\\" href=\\\"#\\\">3</a></li>\\n\" +\n" +
                        "                        \"    <li class=\\\"page-item\\\"><a class=\\\"page-link\\\" href=\\\"#\\\">4</a></li>\\n\" +\n" +
                        "                        \"    <li class=\\\"page-item\\\"><a class=\\\"page-link\\\" href=\\\"#\\\">5</a></li>\\n\" +\n" +
                        "                        \"    <li class=\\\"page-item\\\"><a class=\\\"page-link\\\" href=\\\"#\\\"> >> </a></li>\\n\" +\n" +
                        "                        \"  </ul>\\n\" +\n" +
                        "                        \"</nav>\" +\n" +
                        "                        \"<div id=\\\"items\\\">\" +\n" +
                        "                        \"</div>\" +\n" +
                        "                        \"<ul>\\n\" +\n" +
                        "                        \" <footer>\\n\" +\n" +
                        "                        \"  <!-- Copyright -->\\n\" +\n" +
                        "                        \" <div>\\n\" +\n" +
                        "                        \"© 2020 Copyright:\\n\" +\n" +
                        "                        \"<a href=\\\"https://www.linkedin.com/in/razvan-daniel-besleaga-52466910b/\\\" target=\\\"_blank\\\">Razvan-Daniel Besleaga</a>\\n\" +\n" +
                        "                        \"<p id=\\\"email\\\"><a href=\\\"mailto:razvandallas09@gmail.com\\\" target=\\\"_blank\\\">Email Address</a></p>\\n\" +\n" +
                        "                        \"</div>\\n\" +\n" +
                        "                        \"<!-- Copyright -->\\n\" +\n" +
                        "                        \"</footer> \" +\n" +
                        "                        \"</body></html>\""






        );

    }
}
