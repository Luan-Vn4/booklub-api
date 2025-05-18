CREATE TYPE ENTRY_TYPE AS ENUM ('INVITATION', 'REQUEST');

CREATE TABLE club_pending_entries
(
    entry_type ENTRY_TYPE NOT NULL,
    club_id    UUID NOT NULL,
    user_id    VARCHAR(36) NOT NULL,
    CONSTRAINT pk_club_pending_entries PRIMARY KEY (club_id, user_id)
);

ALTER TABLE club_pending_entries
    ADD CONSTRAINT FK_CLUB_PENDING_ENTRIES_ON_CLUB FOREIGN KEY (club_id) REFERENCES clubs (id);

ALTER TABLE club_pending_entries
    ADD CONSTRAINT FK_CLUB_PENDING_ENTRIES_ON_USER FOREIGN KEY (user_id) REFERENCES user_entity (id);