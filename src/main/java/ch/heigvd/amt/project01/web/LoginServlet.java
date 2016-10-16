package ch.heigvd.amt.project01.web;

import ch.heigvd.amt.project01.services.UsersManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ch.heigvd.amt.project01.util.Utility.PATH;

/**
 * Created by sebbos on 01.10.2016.
 */
public class LoginServlet extends HttpServlet {
    @EJB
    private UsersManagerLocal usersManager;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            usersManager.loginUser(request.getParameter("userName"), request.getParameter("password"));

            System.out.println("USER CONNECTED");

            request.getSession().setAttribute("userName", request.getParameter("userName"));
            // setting session to expiry in 30 mins
            request.getSession().setMaxInactiveInterval(30 * 60);
            //response.sendRedirect(request.getContextPath() + "/protected");
            request.getRequestDispatcher("/protected").forward(request, response);

        }
        catch (Exception e) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);

            String message;

            if (e.getCause().getClass().getSimpleName().equals("IllegalArgumentException")) {
                // message for exceptions with the inputs of the client
                message = e.getCause().getMessage();
            }
            else {
                // generic message for other exception (the client don't need the specific message associated to the exception)
                message = "An error has occurred! Please try again.";
            }

            request.setAttribute("message", message);
            //response.sendRedirect(request.getContextPath() + "/login");
            request.getRequestDispatcher(PATH + "login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PATH + "login.jsp").forward(request, response);
    }
}