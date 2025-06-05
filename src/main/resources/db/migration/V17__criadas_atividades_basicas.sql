-- Setting Club Activities
CREATE TABLE activities
(
    id            UUID NOT NULL,
    activity_type VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_activities PRIMARY KEY (id)
);

-- Setting Club Activities
CREATE TABLE club_activities
(
    id      UUID NOT NULL,
    club_activity_type VARCHAR(255) NOT NULL,
    club_id UUID NOT NULL,
    CONSTRAINT pk_club_activities PRIMARY KEY (id)
);

ALTER TABLE club_activities
    ADD CONSTRAINT FK_CLUB_ACTIVITIES_ON_CLUB FOREIGN KEY (club_id)
        REFERENCES clubs (id) ON DELETE CASCADE;

ALTER TABLE club_activities
    ADD CONSTRAINT FK_CLUB_ACTIVITIES_ON_ID FOREIGN KEY (id)
        REFERENCES activities (id) ON DELETE CASCADE;

-- Setting User Activities
CREATE TABLE user_activities
(
    id      UUID NOT NULL,
    user_activity_type VARCHAR(255) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    CONSTRAINT pk_user_activities PRIMARY KEY (id)
);

ALTER TABLE user_activities
    ADD CONSTRAINT FK_USER_ACTIVITIES_ON_ID FOREIGN KEY (id)
        REFERENCES activities (id) ON DELETE CASCADE;

ALTER TABLE user_activities
    ADD CONSTRAINT FK_USER_ACTIVITIES_ON_USER FOREIGN KEY (user_id)
        REFERENCES user_entity (id) ON DELETE CASCADE;