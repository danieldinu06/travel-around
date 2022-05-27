package controller;

import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    User user;
    HttpSession httpSession;

    private void setData(HttpServletRequest request, HttpServletResponse response) {
        httpSession = request.getSession();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        httpSession.setAttribute("user", user);
    }
}
