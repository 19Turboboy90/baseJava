package com.baseJava.webApp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private final List<String> content;

    public ListSection(List<String> content) {
        this.content = Objects.requireNonNull(content, "content must not be null");
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "content=" + content +
                '}';
    }
}