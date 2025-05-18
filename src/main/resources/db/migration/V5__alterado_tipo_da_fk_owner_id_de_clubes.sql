ALTER TABLE clubs
    DROP CONSTRAINT clubs_owner_id_fkey;

ALTER TABLE clubs
    ALTER COLUMN owner_id
    TYPE VARCHAR(36)
    USING owner_id::text;

ALTER TABLE clubs
    ADD CONSTRAINT clubs_owner_id_fkey
    FOREIGN KEY (owner_id)
    REFERENCES user_entity(id);