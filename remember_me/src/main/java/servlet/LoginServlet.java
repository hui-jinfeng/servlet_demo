package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie userNameCookie = getCookie(cookies, "userName");
        if (userNameCookie != null) {//存在这个cookie
            request.setAttribute("userName", userNameCookie.getValue());
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
        } else {//不存在这个cookie
            if (request.getParameter("userName")==null){//没有填写表单，则跳转到登录表单填写页面
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        if(userName!=null){
            request.setAttribute("userName",userName);
            if (userName != null && userName.equals("是") && pwd.equals("123")) {
                Cookie cookie = new Cookie("userName", userName);
                response.addCookie(cookie);
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            } else if (userName != null && userName.equals("否") && pwd.equals("123")) {
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    private Cookie getCookie(Cookie[] cookies, String key) {
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie;
            }
        }
        return null;
    }
}
