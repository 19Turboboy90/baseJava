package com.baseJava.webApp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {

    private final String uuid;

    private final String fullName;

    private Map<ContactType, String> contacts = new HashMap<>();
    private Map<SectionType, Section> sections = new HashMap<>();


    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName, Map<ContactType, String> contacts, Map<SectionType, Section> sections) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.contacts = contacts;
        this.sections = sections;
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

    public void saveLinkedIn(String linkedIn) {
        contacts.put(ContactType.LINKEDIN, linkedIn);
    }

    public void saveStackoverflow(String stackoverflow) {
        contacts.put(ContactType.STACKOVERFLOW, stackoverflow);
    }

    public void homePage(String homePage) {
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
        sections.put(SectionType.EXPERIENCE, new OrganizationsSection(textExperience));
    }

    public void saveInfoEducation(List<Organization> textEducation) {
        sections.put(SectionType.EDUCATION, new OrganizationsSection(textEducation));
    }

//class Builder
//    public static class Builder {
//        private Resume newResume;
//
//        public Builder(Resume newResume) {
//            this.newResume = newResume;
//        }
//
//        public Builder withName(String fullName) {
//            newResume.fullName = fullName;
//            return this;
//        }
//
//        public Builder withUuid(String uuid) {
//            newResume.uuid = uuid;
//            return this;
//        }
//
//        public Builder withContacts(Map<ContactType, String> contacts) {
//            newResume.contacts = contacts;
//            return this;
//        }
//
//        public Builder withSections(Map<SectionType, Section> sections) {
//            newResume.sections = sections;
//            return this;
//        }
//
//        public Resume build() {
//            return newResume;
//        }
//
//        public Builder savePhone(ContactType contactType,String phone) {
//            newResume.savePhone(phone);
//            return this;
//        }
//
//        public Builder saveSkype(ContactType contactType,String skype) {
//            newResume.saveSkype(skype);
//            return this;
//        }
//
//        public Builder saveEmail(ContactType contactType,String email) {
//            newResume.saveEmail( email);
//            return this;
//        }
//
//        public Builder saveLinkedIn(ContactType contactType,String linkedIn) {
//            newResume.saveLinkedIn( linkedIn);
//            return this;
//        }
//
//        public Builder saveStackoverflow(ContactType contactType,String stackoverflow) {
//            newResume.saveStackoverflow(stackoverflow);
//            return this;
//        }
//
//        public Builder saveHomePage(ContactType contactType,String homePage) {
//            newResume.homePage(homePage);
//            return this;
//        }
//    }
}
