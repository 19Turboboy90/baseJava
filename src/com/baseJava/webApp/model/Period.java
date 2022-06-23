package com.baseJava.webApp.model;

import com.baseJava.webApp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;


public class Period {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public Period(String title, LocalDate startDate, LocalDate endDate, String description) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
        this.endDate = Objects.requireNonNull(endDate, "endDate must not be null");
        this.description = description;
    }

    public Period(String title, int startYear, Month startMonth, String description) {
        this(title, DateUtil.of(startYear, startMonth), DateUtil.NOW, description);
    }

    public Period(String title, int startYear, Month startMonth, int endYear, Month endMonth, String description) {
        this(title, DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), description);
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return title.equals(period.title) && startDate.equals(period.startDate) && endDate.equals(period.endDate) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, endDate, description);
    }

    @Override
    public String toString() {
        return "Position(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
    }
}
