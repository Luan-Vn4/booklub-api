-- 1. User Specific Activities
-- 1.1. User Completed Reading Activities
CREATE TABLE user_completed_reading_activities
(
    id      UUID NOT NULL,
    book_id UUID,
    user_id VARCHAR(36),
    CONSTRAINT pk_user_completed_reading_activities PRIMARY KEY (id)
);

ALTER TABLE user_completed_reading_activities
    ADD CONSTRAINT FK_USER_COMPLETED_READING_ACTIVITIES_ON_BOOK_USER_ID FOREIGN KEY (book_id, user_id)
        REFERENCES book_user (book_id, user_id) ON DELETE CASCADE;

ALTER TABLE user_completed_reading_activities
    ADD CONSTRAINT FK_USER_COMPLETED_READING_ACTIVITIES_ON_ID FOREIGN KEY (id)
        REFERENCES user_activities (id) ON DELETE CASCADE;

-- 2. Club Specific Activities
-- 2.1. Reading Goal Defined Activity
CREATE TABLE reading_goal_defined_activities
(
    id              UUID NOT NULL,
    reading_goal_id UUID NOT NULL UNIQUE,
    CONSTRAINT pk_reading_goal_defined_activities PRIMARY KEY (id)
);

ALTER TABLE reading_goal_defined_activities
    ADD CONSTRAINT FK_READING_GOAL_DEFINED_ACTIVITIES_ON_ID FOREIGN KEY (id)
        REFERENCES club_activities (id) ON DELETE CASCADE;

ALTER TABLE reading_goal_defined_activities
    ADD CONSTRAINT FK_READING_GOAL_DEFINED_ACTIVITIES_ON_READING_GOAL FOREIGN KEY (reading_goal_id)
        REFERENCES reading_goals (id) ON DELETE CASCADE;

-- 2.2. Meeting Defined Activity
CREATE TABLE meeting_defined_activities
(
    id         UUID NOT NULL,
    meeting_id UUID NOT NULL UNIQUE,
    CONSTRAINT pk_meeting_defined_activities PRIMARY KEY (id)
);

ALTER TABLE meeting_defined_activities
    ADD CONSTRAINT FK_MEETING_DEFINED_ACTIVITIES_ON_ID FOREIGN KEY (id)
        REFERENCES club_activities (id) ON DELETE CASCADE;

ALTER TABLE meeting_defined_activities
    ADD CONSTRAINT FK_MEETING_DEFINED_ACTIVITIES_ON_MEETING FOREIGN KEY (meeting_id)
        REFERENCES meetings (id) ON DELETE CASCADE;

-- 2.3. Member Completed Reading Activity
CREATE TABLE member_completed_reading_activities
(
    id      UUID NOT NULL,
    book_id UUID,
    user_id VARCHAR(36),
    CONSTRAINT pk_member_completed_reading_activities PRIMARY KEY (id)
);

ALTER TABLE member_completed_reading_activities
    ADD CONSTRAINT FK_MEMBER_COMPLETED_READING_ACTIVITIES_ON_BOOK_USER_ID FOREIGN KEY (book_id, user_id)
        REFERENCES book_user (book_id, user_id) ON DELETE CASCADE;

ALTER TABLE member_completed_reading_activities
    ADD CONSTRAINT UNIQUE_BOOK_USER_ID UNIQUE (book_id, user_id);

ALTER TABLE member_completed_reading_activities
    ADD CONSTRAINT FK_MEMBER_COMPLETED_READING_ACTIVITIES_ON_ID FOREIGN KEY (id)
        REFERENCES club_activities (id) ON DELETE CASCADE;