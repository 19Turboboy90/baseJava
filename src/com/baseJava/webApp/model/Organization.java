package com.baseJava.webApp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final String title;
    private final String webSite;
    private final List<Period> periods;

    public Organization(String title, String webSite, List<Period> periods) {
        Objects.requireNonNull(title);
        this.title = title;
        this.webSite = webSite;
        this.periods = periods;
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
