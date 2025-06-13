ALTER TABLE reading_goals DROP CONSTRAINT IF EXISTS fk_reading_goals_on_club;

ALTER TABLE reading_goals
    ADD CONSTRAINT fk_reading_goals_on_club
        FOREIGN KEY (club_id)
            REFERENCES clubs(id)
            ON DELETE CASCADE;
