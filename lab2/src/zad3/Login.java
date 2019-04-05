package zad3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vector<Osoba> dane=new Vector<>();
        dane.add(new Osoba("maria","maslo","maria","mamica"));
        String login=request.getParameter("login");
        String password = request.getParameter("password");

        if(login==null || login==""){
            request.setAttribute("message", "Podaj Login");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

        if(password==null || password==""){
            request.setAttribute("message", "Podaj Hasło");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

        if(password!=null && login!=null){
            for(Osoba o : dane){
                if(o.getLogin().equals(login) && o.getPassword().equals(password)){
                    request.getSession().setAttribute("user", o);
                    response.sendRedirect(request.getContextPath() + "/homePage.jsp");
                }else{
                    request.setAttribute("message", "Błędne dane");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Osoba user = (Osoba) request.getSession().getAttribute("user");
        if(user==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/homePage.jsp");
        }
    }
}
