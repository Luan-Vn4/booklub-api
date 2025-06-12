-- Dropping Constraints
ALTER TABLE book_ratings DROP CONSTRAINT book_user_id;

ALTER TABLE user_completed_reading_activities DROP CONSTRAINT fk_user_completed_reading_activities_on_book_user_id;

ALTER TABLE member_completed_reading_activities DROP CONSTRAINT fk_member_completed_reading_activities_on_book_user_id;

-- Altering table book_id column type
ALTER TABLE user_completed_reading_activities ALTER COLUMN book_id TYPE VARCHAR(31);

ALTER TABLE member_completed_reading_activities ALTER COLUMN book_id TYPE VARCHAR(31);

ALTER TABLE book_ratings ALTER COLUMN book_id TYPE VARCHAR(31);

ALTER TABLE book_user ALTER COLUMN book_id TYPE VARCHAR(31);

-- Recreating Constraints
ALTER TABLE book_ratings ADD CONSTRAINT
    FK_KEYBOOK_RATINGS_ON_BOOK_USER_ID
        FOREIGN KEY (book_id, user_id) REFERENCES book_user (book_id, user_id);

ALTER TABLE user_completed_reading_activities ADD CONSTRAINT
    FK_USER_COMPLETED_READING_ACTIVITIES_ON_BOOK_USER_ID
        FOREIGN KEY (book_id, user_id) REFERENCES book_user (book_id, user_id);

ALTER TABLE member_completed_reading_activities ADD CONSTRAINT
    FK_MEMBER_COMPLETED_READING_ACTIVITIES_ON_BOOK_USER_ID
        FOREIGN KEY (book_id, user_id) REFERENCES book_user (book_id, user_id);


