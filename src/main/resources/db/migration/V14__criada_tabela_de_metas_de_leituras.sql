CREATE TABLE reading_goals
(
    id         UUID        NOT NULL,
    book_id    VARCHAR(32) NOT NULL,
    club_id    UUID        NOT NULL,
    start_date date        NOT NULL,
    end_date   date        NOT NULL,
    CONSTRAINT pk_reading_goals PRIMARY KEY (id)
);

ALTER TABLE reading_goals
    ADD CONSTRAINT FK_READING_GOALS_ON_CLUB FOREIGN KEY (club_id) REFERENCES clubs (id);