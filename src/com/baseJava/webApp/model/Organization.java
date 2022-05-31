package com.baseJava.webApp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final String title;
    private final String webSite;
    private final List<Period> periods;

    public Organization(String title, String webSite, List<Period> periods) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.webSite = Objects.requireNonNull(webSite, "webSite must not be null");
        this.periods = Objects.requireNonNull(periods, "periods must not be null");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return title.equals(that.title) && webSite.equals(that.webSite) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, webSite, periods);
    }

    @Override
    public String toString() {
        return "Organization" +
                title  +
                webSite + "\n" +
                periods + "\n";
    }
}
