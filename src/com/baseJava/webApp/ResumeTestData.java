package com.baseJava.webApp;

import com.baseJava.webApp.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        System.out.println(createResume("13", FULLNAME));
    }

    private static final String FULLNAME = "Григорий Кислин";
    private static final String PHONE_NUMBER = "8-999-999-99-99";
    private static final String SKYPE = "skype:skype:grigory.kislin";
    private static final String EMAIL = "gkislin@yandex.ru";
    private static final String LINKEDIN = "https://www.linkedin.com/in/gkislin";
    private static final String GITHUB = "https://github.com/gkislin";
    private static final String STACKOVERFLOW = "https://stackoverflow.com/users/548473/grigory-kislin";
    private static final String HOMEPAGE = "http://gkislin.ru/";
    private static final String OBJECTIVE = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
    private static final String PERSONAL = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";

    private static final List<String> ACHIEVEMENT = new ArrayList<>(List.of("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке " +
            "Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ " +
            "на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет" +
            "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " +
            "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
            "Организация онлайн стажировок и ведение проектов. Более 3500 выпускников." +
            "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с " +
            "Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk." +
            "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita " +
            "BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка " +
            "SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера." +
            "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT " +
            "(GXT), Commet, HTML5, Highstock для алгоритмического трейдинга." +
            "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base " +
            "архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему " +
            "мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)." +
            "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, " +
            "Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));

    private static final List<String> QUALIFICATIONS = new ArrayList<>(List.of(
            "Version control: Subversion, Git, Mercury, ClearCase, Perforce\n" +
                    "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB\n" +
                    "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy\n" +
                    "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts\n" +
                    "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).\n" +
                    "Python: Django.\n" +
                    "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n" +
                    "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n" +
                    "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.\n" +
                    "Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                    "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer\n" +
                    "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования\n" +
                    "Родной русский, английский \"upper intermediate\""));

    // EXPERIENCE
    private static final Organization organization_1 = new Organization("Java Online Projects", "https://javaops.ru/",
            new ArrayList<>(List.of(new Period("Автор проекта.", LocalDate.of(2013, Month.NOVEMBER, 1), LocalDate.now(),
                    "Создание, организация и проведение Java онлайн проектов и стажировок."))));

    private static final Organization organization_2 = new Organization("Wrike", "https://www.wrike.com/",
            new ArrayList<>(List.of(new Period("Старший разработчик (backend).", 2014, Month.OCTOBER, 2016, Month.JANUARY,
                    "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                            "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));

    private static final Organization organization_3 = new Organization("RIT Center", " ",
            new ArrayList<>(List.of(new Period("Java архитектор", 2012, Month.APRIL, 2014, Month.OCTOBER,
                    "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция" +
                            " базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка" +
                            " интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco" +
                            " JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring" +
                            " MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))));

    private static final Organization organization_4 = new Organization("Luxoft (Deutsche Bank)", "https://career.luxoft.com/",
            new ArrayList<>(List.of(new Period("Ведущий программист", 2010, Month.OCTOBER, 2012, Month.APRIL,
                    "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и " +
                            "серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического " +
                            "трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."))));

    private static final Organization organization_5 = new Organization("Yota", "https://www.yota.ru/",
            new ArrayList<>(List.of(new Period("Ведущий специалист", 2008, Month.JUNE, 2010, Month.DECEMBER,
                    "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4," +
                            " JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ " +
                            "Jython, Django, ExtJS)"))));

    private static final Organization organization_6 = new Organization("Enkata", "https://www.pega.com/products/platform/robotic-process-automation",
            new ArrayList<>(List.of(new Period("Разработчик ПО", 2007, Month.MARCH, 2008, Month.JUNE,
                    "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения " +
                            "(OLAP, Data mining)."))));

    private static final Organization organization_7 = new Organization("Siemens AG", "https://www.siemens.com/global/en.html",
            new ArrayList<>(List.of(new Period("Инженер по аппаратному и программному тестированию", 2005, Month.JANUARY, 2007, Month.FEBRUARY,
                    "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))));

    private static final Organization organization_8 = new Organization("Alcatel", "http://www.alcatel.ru/",
            new ArrayList<>(List.of(new Period("Разработчик ПО", 1997, Month.SEPTEMBER, 2005, Month.JANUARY,
                    "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))));

    private static final List<Organization> EXPERIENCE = new ArrayList<>(List.of(organization_1, organization_2, organization_3, organization_4, organization_5, organization_6, organization_7, organization_8));

//EDUCATION

    private static final Organization education_1 = new Organization("Coursera", "https://www.coursera.org/",
            new ArrayList<>(List.of(new Period("'Functional Programming Principles in Scala' by Martin Odersky", 2013, Month.MARCH, 2013, Month.MAY, "null"))));


    private static final Organization education_2 = new Organization("Luxoft", "https://ibs-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
            new ArrayList<>(List.of(new Period("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", 2011, Month.SEPTEMBER, 2011, Month.APRIL, "null"))));

    private static final Organization education_3 = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html",
            new ArrayList<>(List.of(new Period("3 месяца обучения мобильным IN сетям (Берлин)", 2005, Month.JANUARY, 2005, Month.APRIL, "null"))));


    private static final Organization education_4 = new Organization("Alcatel", "http://www.alcatel.ru/",
            new ArrayList<>(List.of(new Period("6 месяцев обучения цифровым телефонным сетям (Москва)", 1997, Month.SEPTEMBER, 1998, Month.MARCH, "null"))));

    private static final Organization education_5 = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики " +
            "и оптики", "https://itmo.ru/ru/",
            new ArrayList<>(List.of(new Period("Аспирантура (программист С, С++)", 1993, Month.SEPTEMBER, 1996, Month.JULY, "null"),
                    new Period("Инженер (программист Fortran, C)", 1987, Month.SEPTEMBER, 1993, Month.JULY, "null"))));

    private static final Organization education_6 = new Organization("Заочная физико-техническая школа при МФТИ", "https://school.mipt.ru/",
            new ArrayList<>(List.of(new Period("Закончил с отличием", 1984, Month.SEPTEMBER, 1987, Month.JUNE, "null"))));

    private static final List<Organization> EDUCATION = new ArrayList<>(List.of(education_1, education_2, education_3, education_4, education_5, education_6));

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.saveContacts(ContactType.PHONE, PHONE_NUMBER);
        resume.saveContacts(ContactType.SKYPE, SKYPE);
        resume.saveContacts(ContactType.EMAIL, EMAIL);
        resume.saveContacts(ContactType.LINKEDIN, LINKEDIN);
        resume.saveContacts(ContactType.GITHUB, GITHUB);
        resume.saveContacts(ContactType.STACKOVERFLOW, STACKOVERFLOW);
        resume.saveContacts(ContactType.HOMEPAGE, HOMEPAGE);
//
//        resume.saveSections(SectionType.PERSONAL, new TextSection(PERSONAL));
//        resume.saveSections(SectionType.OBJECTIVE, new TextSection(OBJECTIVE));
//
//        resume.saveSections(SectionType.ACHIEVEMENT, new ListSection(ACHIEVEMENT));
//        resume.saveSections(SectionType.QUALIFICATIONS, new ListSection(QUALIFICATIONS));
//
//        resume.saveSections(SectionType.EXPERIENCE, new OrganizationSection(EXPERIENCE));
//        resume.saveSections(SectionType.EDUCATION, new OrganizationSection(EDUCATION));

        return resume;
    }
}
