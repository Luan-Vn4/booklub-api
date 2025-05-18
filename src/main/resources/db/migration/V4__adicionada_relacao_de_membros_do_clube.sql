CREATE TABLE clubs_users (
    club_id UUID NOT NULL REFERENCES clubs(id),
    user_id UUID NOT NULL REFERENCES users(id),
    CONSTRAINT pk_clubs_users PRIMARY KEY (club_id, user_id)
);