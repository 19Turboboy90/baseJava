package com.baseJava.webApp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private List<String> contentList;

    public ListSection(List<String> contentList) {
        this.contentList = contentList;
    }

    public ListSection() {
    }

    public List<String> getContentList() {
        return contentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(contentList, that.contentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentList);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "contentList=" + contentList +
                '}';
    }
}
