package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "good", urlPatterns = {"/hello", "/helloworld"})
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        ServletConfig servletConfig = getServletConfig();
        ServletContext servletContext = servletConfig.getServletContext();
        String initParameter = servletContext.getInitParameter("123");
        String servletName = servletConfig.getServletName();
        writer.print(servletName + "    ");
        writer.write(initParameter + "    ");
        writer.write(servletContext.getContextPath() + "    ");
        writer.write(servletContext.getRealPath("/servlet")+"    ");
        writer.write(request.getServletPath());//是/hello
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
