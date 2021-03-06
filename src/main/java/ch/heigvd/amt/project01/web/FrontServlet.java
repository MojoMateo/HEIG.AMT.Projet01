package ch.heigvd.amt.project01.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.project01.util.Utility.PATH;

/**
 * Servlet that serves the main page of the website.
 *
 * @author Mathieu Urstein and Sébastien Boson
 */
public class FrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PATH + "index.jsp").forward(request, response);
    }
}
