package ru.baseJava.webApp.web;

import ru.baseJava.webApp.Config;
import ru.baseJava.webApp.model.*;
import ru.baseJava.webApp.storage.Storage;
import ru.baseJava.webApp.util.DateUtil;
import ru.baseJava.webApp.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/resume")
public class resumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = storage.get(uuid);
        resume.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.isEmpty(value)) {
                resume.getContacts().remove(type);
            } else {
                resume.saveContacts(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name().toLowerCase());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                resume.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.saveSections(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.saveSections(type, new ListSection(value.split("\\n")));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Organization> organization = new ArrayList<>();
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!HtmlUtil.isEmpty(name)) {
                                List<Period> periods = new ArrayList<>();
                                String prefix = type.name() + i;
                                String[] titles = request.getParameterValues(prefix + "title");
                                String[] websites = request.getParameterValues(prefix + "Website");
                                String[] startDates = request.getParameterValues(prefix + "startDate");
                                String[] positions = request.getParameterValues(prefix + "Position");
                                String[] endDates = request.getParameterValues(prefix + "endDate");
                                String[] descriptions = request.getParameterValues(prefix + "description");
                                for (int j = 0; j < titles.length; j++) {
                                    if (!HtmlUtil.isEmpty(titles[j])) {
                                        periods.add(new Period(positions[j], DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), descriptions[j]));
                                    }
                                }
                                organization.add(new Organization(titles[i], websites[i], periods));
                            }
                        }
                        resume.saveSections(type, new OrganizationSection(organization));
                        break;
                }
            }
        }
        storage.update(resume);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }

        Resume resume;
        switch (action) {
            case "add":
                resume = new Resume(UUID.randomUUID().toString(), "");
                request.setAttribute("resume", resume);
                request.setAttribute("storageAction", "add");
                request.getRequestDispatcher("WEB-INF/jsp/edit.jsp").forward(request, response);
                return;
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                request.setAttribute("resume", resume);
                request.setAttribute("storageAction", "edit");
                request.getRequestDispatcher(
                        ("view".equals(action)
                                ? "/WEB-INF/jsp/view.jsp"
                                : "WEB-INF/jsp/edit.jsp")
                ).forward(request, response);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
    }
}