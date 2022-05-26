package com.baseJava.webApp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final String title;
    private final LocalDate start;
    private final LocalDate end;
    private String description;

    public Period(String title, LocalDate start, LocalDate end, String description) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public Period(LocalDate start, LocalDate end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(title, period.title) && Objects.equals(start, period.start) && Objects.equals(end, period.end) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, start, end, description);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", description='" + description + '\'' +
                '}';
    }
}
