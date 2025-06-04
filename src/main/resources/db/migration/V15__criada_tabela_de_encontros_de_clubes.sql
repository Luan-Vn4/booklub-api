CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE meetings (
    id              UUID NOT NULL,
    reading_goal_id UUID NOT NULL,
    address         VARCHAR(255),
    latlng          GEOMETRY(POINT, 4326),
    CONSTRAINT pk_meetings PRIMARY KEY (id)
);

ALTER TABLE meetings
    ADD CONSTRAINT uc_meetings_reading_goal UNIQUE (reading_goal_id);

ALTER TABLE meetings
    ADD CONSTRAINT FK_MEETINGS_ON_READING_GOAL FOREIGN KEY (reading_goal_id)
        REFERENCES reading_goals (id) ON DELETE CASCADE;