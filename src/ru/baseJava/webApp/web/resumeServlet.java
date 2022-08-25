package ru.baseJava.webApp.web;

import ru.baseJava.webApp.Config;
import ru.baseJava.webApp.model.Resume;
import ru.baseJava.webApp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resume")
public class resumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");
        resp.getWriter().write("<html>\n" +
                "    <style>\n" +
                "        table, th, td {\n" +
                "        width: 100px;\n" +
                "        height: 50px;\n" +
                "        border: 1px solid black;}\n" +
                "    </style>\n" +
                "<body>\n" +
                "    <table style=width:100%>\n" +
                "        <tr>\n" +
                "            <th>UUID</th>\n" +
                "            <th>Full Name</th>\n" +
                "        </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            resp.getWriter().write("<tr>\n" +
                    "            <th>" + resume.getUuid() + "</th>\n" +
                    "            <th>" + resume.getFullName() + "</th>\n" +
                    "        </tr>\n");
        }
        resp.getWriter().write("</table>\n" +
                "</body>\n" +
                "</html>\n");


//        String html = "<html>" +
//                "<style>" +
//                "    table, th, td {" +
//                "        width: 100px;" +
//                "        height: 50px;" +
//                "        border: 1px solid black;" +
//                "    }" +
//                "</style>" +
//                "<body>" +
//                "<table style=width:100%>" +
//                "    <tr>" +
//                "        <th>UUID</th>" +
//                "        <th>Full Name</th>" +
//                "    </tr>" +
//                "    <tr>" +
//                "        <th> resume.getUuid() </th>" +
//                "        <th> resume.getFullName()</th>" +
//                "    </tr>" +
//                "</table>" +
//                "</body>" +
//                "</html>";
//        resp.getWriter().write(name == null ? html : "Hello " + name + '!');
//        req.getRequestDispatcher("table.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
