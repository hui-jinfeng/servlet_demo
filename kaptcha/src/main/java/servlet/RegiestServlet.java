package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "RegiestServlet", value = "/regiest")
public class RegiestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        //在地址栏输入地址访问时会生成一个 验证码
        String sessionKey = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = request.getParameter("code");

        PrintWriter writer = response.getWriter();
        if (sessionKey != null && sessionKey.equalsIgnoreCase(code)) {
            System.out.println("do something");
            response.sendRedirect(request.getContextPath()+"/dashboard.jsp");
        }else{
            writer.write("请勿重复提交表单");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
