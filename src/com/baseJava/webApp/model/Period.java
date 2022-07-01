package com.baseJava.webApp.model;

import com.baseJava.webApp.util.DateUtil;
import com.baseJava.webApp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String description;

    public Period() {
    }

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
        return Objects.equals(title, period.title) && Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate) && Objects.equals(description, period.description);
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
