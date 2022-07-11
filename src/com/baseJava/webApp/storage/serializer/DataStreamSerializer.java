package com.baseJava.webApp.storage.serializer;

import com.baseJava.webApp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements ObjectStreamStorageInterface {
    @Override
// Write Resume
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(file)) {
            Map<ContactType, String> contacts = resume.getContacts();
            Map<SectionType, Section> sections = resume.getSections();

            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            writeSectionsResume(dos, contacts.entrySet(), partition -> {
                dos.writeUTF(partition.getKey().name());
                dos.writeUTF(partition.getValue());
            });

            writeSectionsResume(dos, sections.entrySet(), partition -> {
                SectionType type = partition.getKey();
                Section section = partition.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        String text = ((TextSection) section).getContent();
                        dos.writeUTF(text);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> contentList = ((ListSection) section).getContent();
                        writeSectionsResume(dos, contentList, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationsList = ((OrganizationSection) section).getOrganization();
                        writeSectionsResume(dos, organizationsList, organization -> {
                            List<Period> periods = organization.getPeriods();
                            dos.writeUTF(organization.getTitle());
                            dos.writeUTF(organization.getWebSite());
                            writeSectionsResume(dos, periods, element -> {
                                dos.writeUTF(element.getTitle());
                                dos.writeUTF(String.valueOf(element.getStartDate()));
                                dos.writeUTF(String.valueOf(element.getEndDate()));
                                dos.writeUTF(element.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (DataInputStream dis = new DataInputStream(file)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readSectionsResume(dis, () -> {
                ContactType type = ContactType.valueOf(dis.readUTF());
                String value = dis.readUTF();
                resume.saveContacts(type, value);
            });

            readSectionsResume(dis, () -> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                Section section = readSection(dis, type);
                resume.saveSections(type, section);
            });
            return resume;
        }
    }

    private <T> void writeSectionsResume(DataOutputStream dos, Collection<T> collection, Writable<T> partition) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            partition.write(element);
        }
    }

    private interface Writable<T> {
        void write(T partition) throws IOException;
    }

//Read Resume

    private Section readSection(DataInputStream dis, SectionType type) throws IOException {
        Section section;
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(dis.readUTF());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                List<String> contentList = new ArrayList<>();
                readSectionsResume(dis, () -> contentList.add(dis.readUTF()));
                section = new ListSection(contentList);
                break;
            case EXPERIENCE:
            case EDUCATION:
                List<Organization> organizationsList = new ArrayList<>();
                readSectionsResume(dis, () -> {
                    String title = dis.readUTF();
                    String webSite = dis.readUTF();
                    List<Period> periods = new ArrayList<>();

                    readSectionsResume(dis, () -> {
                        String titlePeriods = dis.readUTF();
                        LocalDate startDate = LocalDate.parse(dis.readUTF());
                        LocalDate endDate = LocalDate.parse(dis.readUTF());
                        String description = dis.readUTF();
                        periods.add(new Period(titlePeriods, startDate, endDate, description));
                    });
                    organizationsList.add(new Organization(title, webSite, periods));
                });
                section = new OrganizationSection(organizationsList);
                break;
            default:
                throw new IllegalStateException("Wrong section type: " + type.name());
        }
        return section;
    }

    private void readSectionsResume(DataInputStream dis, Readable partition) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            partition.read();
        }
    }

    private interface Readable {
        void read() throws IOException;
    }
}