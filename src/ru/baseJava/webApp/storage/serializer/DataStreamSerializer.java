package ru.baseJava.webApp.storage.serializer;

import ru.baseJava.webApp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements ObjectStreamStorageInterface {
    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(file)) {
            Map<ContactType, String> contacts = resume.getContacts();
            Map<SectionType, AbstractSection> sections = resume.getSections();

            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            writeSectionsResume(dos, contacts.entrySet(), partition -> {
                dos.writeUTF(partition.getKey().name());
                dos.writeUTF(partition.getValue());
            });

            writeSectionsResume(dos, sections.entrySet(), partition -> {
                SectionType type = partition.getKey();
                AbstractSection section = partition.getValue();
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
                            if (organization.getWebSite() != null) {
                                dos.writeUTF(organization.getWebSite());
                            } else {
                                dos.writeUTF("null");
                            }
                            writeSectionsResume(dos, periods, element -> {
                                dos.writeUTF(element.getTitle());
                                dos.writeUTF(String.valueOf(element.getStartDate()));
                                dos.writeUTF(String.valueOf(element.getEndDate()));
                                if (element.getDescription() != null) {
                                    dos.writeUTF(element.getDescription());
                                } else {
                                    dos.writeUTF("null");
                                }
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
                AbstractSection section;
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        section = new ListSection(readListResume(dis, dis::readUTF));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        section = new OrganizationSection(readListResume(dis, () -> new Organization(dis.readUTF(), dis.readUTF(), readListResume(dis,
                                () -> new Period(dis.readUTF(), LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF())))));
                        break;
                    default:
                        throw new IllegalStateException("Wrong section type: " + type.name());
                }
                resume.saveSections(type, section);
            });
            return resume;
        }
    }

// Write Resume
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
    private void readSectionsResume(DataInputStream dis, Readable partition) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            partition.read();
        }
    }

    private interface Readable {
        void read() throws IOException;
    }

    interface ListReader<T> {
        T readList() throws IOException;
    }

    private <T> List<T> readListResume(DataInputStream dis, ListReader<T> list) throws IOException {
        List<T> readerList = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            readerList.add(list.readList());
        }
        return readerList;
    }
}