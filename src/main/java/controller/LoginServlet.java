package controller;


import model.User;
import model.utils.Encrypt;
import model.utils.UserStatus;
import service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    User user;

    HttpSession httpSession;

    private void setData(HttpServletRequest request, HttpServletResponse response) {
        httpSession = request.getSession();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        user = (User) httpSession.getAttribute("user");

        System.out.println(user.getName());

        PrintWriter writer = response.getWriter();
        writer.println("login.html");

    }

}
