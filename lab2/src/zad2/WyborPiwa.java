package zad2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WyborPiwa")
public class WyborPiwa extends HttpServlet {
    private EkspertPiwny ekspert=new EkspertPiwny();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");
        String color = request.getParameter("color");
        String beer = ekspert.wyszukaj(color);
        request.setAttribute("beer", beer);

        RequestDispatcher rd = request.getRequestDispatcher("/wynik.jsp");
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
