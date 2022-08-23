package ru.baseJava.webApp.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resume")
public class resumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");
//        req.getRequestDispatcher("table.html").forward(req,resp);
        String html = "<html>\n" +
                "<style>\n" +
                "    table, th, td {\n" +
                "        width: 100px;\n" +
                "        height: 50px;\n" +
                "        border: 1px solid black;\n" +
                "    }\n" +
                "</style>\n" +
                "<body>\n" +
                "<table style=\"width:100%\">\n" +
                "    <tr>\n" +
                "        <th>id</th>\n" +
                "        <th>full_name</th>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>\n";
        resp.getWriter().write(name == null ? html : "Hello " + name + '!');
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
