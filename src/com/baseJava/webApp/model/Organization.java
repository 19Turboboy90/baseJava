package com.baseJava.webApp.model;

import java.util.List;

public class Organization {

    private String title;

    private String webSite;

    private List<Period> periods;

    public Organization() {
    }

    public Organization(String title, String webSite, List<Period> periods) {
        this.title = title;
        this.webSite = webSite;
        this.periods = periods;
    }

    public Organization(String title, String webSite) {
        this.title = title;
        this.webSite = webSite;
    }

    public String getTitle() {
        return title;
    }

    public String getWebSite() {
        return webSite;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", webSite='" + webSite + '\'' +
                ", periods=" + periods +
                '}';
    }
}
