package zad2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@WebFilter(filterName = "Filter")
public class Filtr implements Filter {
    private FilterConfig filterConfig = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig= filterConfig;

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration e=((HttpServletRequest) servletRequest).getParameterNames();
        int age=0;

        Cookie[] cookies = request.getCookies();

        for(Cookie c : cookies){
            if(c.getName().equals("age")){
                age=Integer.parseInt(c.getValue());
            }
        }

        if(age>18){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println("Too young");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Nie masz uprawnien do przgladania tej strony.</h1>");
            out.println("</body>");
            out.println("</html>");
        }


    }

    @Override
    public void destroy() {

    }
}
