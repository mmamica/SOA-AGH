package zad1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

@WebServlet(name = "Liczby2")
public class Liczby2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");
        PrintWriter out = response.getWriter();
        Enumeration data = request.getParameterNames();
        boolean numeric=true;
        ArrayList<Double> number_list = new ArrayList<Double>();

        while(data.hasMoreElements()){
            Object o =data.nextElement();
            String field_name=(String) o;
            String val=request.getParameter(field_name);
            try {
                Double num = Double.parseDouble(val);
                number_list.add(num);
            } catch (NumberFormatException e) {
                numeric = false;
            }
        }

        out.println("<html>");
        out.println("<head><title>Sprawdź liczby</title></head>");
        out.println("<body>");

        if(numeric){
            Collections.sort(number_list);
            out.println("<h1>Posortowane liczby:</h1>");

            for(double n:number_list){
                out.println("<p>"+n+"</p>");
            }
        }else{
            out.println("<h1>Dane nie są liczbami!</h1>");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
