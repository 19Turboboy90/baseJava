package com.baseJava.webApp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {
    private final List<Organization> organization;

    public OrganizationSection(List<Organization> organizations) {
        this.organization = Objects.requireNonNull(organizations, "organizations must not be null");
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization);
    }

    @Override
    public String toString() {
        return "OrganizationsSection{" +
                "organization=" + organization +
                '}';
    }
}