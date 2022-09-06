package ru.baseJava.webApp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Organization EMPTY = new Organization("", "", Period.EMPTY);

    private String name;
    private String webSite;
    private List<Period> periods;

    public Organization() {
    }

    public Organization(String title, String webSite, Period... positions) {
        this.name = title;
        this.webSite = webSite;
        this.periods = Arrays.asList(positions);
    }

    public Organization(String title, String webSite, List<Period> periods) {
        this.name = Objects.requireNonNull(title, "name must not be null");
        this.webSite = Objects.requireNonNull(webSite, "webSite must not be null");
        this.periods = Objects.requireNonNull(periods, "periods must not be null");
    }

    public String getName() {
        return name;
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
        return name.equals(that.name) && webSite.equals(that.webSite) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, webSite, periods);
    }

    @Override
    public String toString() {
        return
                name + "\n" +
                        webSite + "\n" +
                        periods + "\n";
    }
}
