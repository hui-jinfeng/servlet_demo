package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "DashBoardFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "page1",value = "/index.jsp"),@WebInitParam(name = "page2",value = "/login")})
public class DashBoardFilter implements Filter {
    Set<String> excludePaths=new HashSet<>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("doFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        String servletPath = request.getServletPath();//获取当前的请求路径
        if(excludePaths.contains(servletPath)){//如果当前请求路径是被排除的，则放行
            chain.doFilter(req,resp);
            return;
        }
        Object userName = request.getSession().getAttribute("userName");
        if (userName == null) {//未登录但是直接访问的
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            System.out.println("doChain");
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        //把需要排除的路径放入Set
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            String pathKey=initParameterNames.nextElement();
            excludePaths.add(config.getInitParameter(pathKey));
        }
    }
}
