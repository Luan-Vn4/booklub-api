ALTER TABLE clubs_users
    DROP CONSTRAINT pk_clubs_users;

ALTER TABLE clubs_users
    DROP CONSTRAINT clubs_users_user_id_fkey;

ALTER TABLE clubs_users
    ALTER COLUMN user_id
    TYPE VARCHAR(36)
    USING user_id::text;

ALTER TABLE clubs_users
    ADD CONSTRAINT pk_clubs_users
    PRIMARY KEY (user_id, club_id);

ALTER TABLE clubs_users
    ADD CONSTRAINT clubs_users_user_id_fkey
    FOREIGN KEY (user_id)
    REFERENCES user_entity(id);