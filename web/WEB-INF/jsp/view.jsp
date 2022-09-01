<%@ page import="ru.baseJava.webApp.model.TextSection" %>
<%@ page import="ru.baseJava.webApp.model.ListSection" %>
<%@ page import="ru.baseJava.webApp.model.OrganizationSection" %>
<%@ page import="ru.baseJava.webApp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%--    <link href="../../css/style.css">--%>
    <link rel="stylesheet" href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="resume" type="ru.baseJava.webApp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="../../img/pencil.png"></a>
    </h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.baseJava.webApp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <hr>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.baseJava.webApp.model.ContactType, ru.baseJava.webApp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="ru.baseJava.webApp.model.AbstractSection"/>
            <tr>
            <td><h3><a name="type.name">${type.title}</a></h3></td>
            <c:if test="${type=='OBJECTIVE'}">
                <td>
                    <h3><%=((TextSection) section).getContent()%>
                    </h3>
                </td>
            </c:if>
            <c:if test="${type!='OBJECTIVE'}">
                <c:choose>
                    <c:when
                            test="${type=='PERSONAL'}">
                        <tr>
                            <td>
                                <%=((TextSection) sectionEntry.getValue()).getContent()%>
                            </td>
                        </tr>
                    </c:when>
                    <c:when
                            test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                        <tr>
                            <td>
                                <ul>
                                    <c:forEach var="item"
                                               items="<%=((ListSection) sectionEntry.getValue()).getContent()%>">
                                        <li>${item}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="organization"
                                   items="<%=((OrganizationSection) sectionEntry.getValue()).getOrganization()%>">
                            <tr>
                                <td colspan="2">
                                    <c:choose>
                                        <c:when test="${empty organization.webSite}">
                                            <h3>${organization.webSite}</h3>
                                        </c:when>
                                        <c:otherwise>
                                            <h3><a href="${organization.webSite}">${organization.webSite}</a></h3>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:forEach var="position" items="${organization.periods}">
                                <jsp:useBean id="position" type="ru.baseJava.webApp.model.Period"/>
                                <td>
                                    <%=HtmlUtil.formatDates(position)%>
                                </td>
                                <td>
                                    <b>${position.title}</b><br>${position.description}
                                </td>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>
        </c:forEach>
    </table>
    <button onclick="window.history.back()">OK</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

