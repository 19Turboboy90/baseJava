CREATE TABLE resume
(
    uuid      CHAR(36) NOT NULL
        CONSTRAINT resume_pk
            PRIMARY KEY,
    full_name TEXT     NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE
);

CREATE TABLE section
(
    id          SERIAL,
    type     TEXT     NOT NULL,
    content text not null,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE
);

CREATE UNIQUE INDEX contact_uuid_type_index ON contact (resume_uuid, type);
CREATE UNIQUE INDEX section_uuid_type_index ON section (resume_uuid, type);