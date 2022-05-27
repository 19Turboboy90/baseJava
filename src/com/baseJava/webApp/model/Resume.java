package com.baseJava.webApp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = Objects.requireNonNull(uuid, "uuid must not be null");
        this.fullName = Objects.requireNonNull(fullName, "fullName must not be null");
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    // Methods class ContactType
    public void savePhone(String phone) {
        contacts.put(ContactType.PHONE, phone);
    }

    public void saveSkype(String skype) {
        contacts.put(ContactType.SKYPE, skype);
    }

    public void saveEmail(String email) {
        contacts.put(ContactType.EMAIL, email);
    }

    public void saveGitHub(String gitHub) {
        contacts.put(ContactType.GITHUB, gitHub);
    }

    public void saveLinkedIn(String linkedIn) {
        contacts.put(ContactType.LINKEDIN, linkedIn);
    }

    public void saveStackoverflow(String stackoverflow) {
        contacts.put(ContactType.STACKOVERFLOW, stackoverflow);
    }

    public void saveHomePage(String homePage) {
        contacts.put(ContactType.HOMEPAGE, homePage);
    }

    // Methods class SectionType
    public void saveInfoPersonal(String textPersonal) {
        sections.put(SectionType.PERSONAL, new TextSection(textPersonal));
    }

    public void saveInfoObjective(String textObjective) {
        sections.put(SectionType.OBJECTIVE, new TextSection(textObjective));
    }

    public void saveInfoAchievement(List<String> textAchievement) {
        sections.put(SectionType.ACHIEVEMENT, new ListSection(textAchievement));
    }

    public void saveInfoQualification(List<String> textQualification) {
        sections.put(SectionType.QUALIFICATIONS, new ListSection(textQualification));
    }

    public void saveInfoExperience(List<Organization> textExperience) {
        sections.put(SectionType.EXPERIENCE, new OrganizationSection(textExperience));
    }

    public void saveInfoEducation(List<Organization> textEducation) {
        sections.put(SectionType.EDUCATION, new OrganizationSection(textEducation));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) && Objects.equals(contacts, resume.contacts) && Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }
}
