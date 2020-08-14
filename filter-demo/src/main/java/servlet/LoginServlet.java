package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        if (userName == null) {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            //request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/dashboard.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
