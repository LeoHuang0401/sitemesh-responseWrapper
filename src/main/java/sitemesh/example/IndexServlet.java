package sitemesh.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("index#start");
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
    
}