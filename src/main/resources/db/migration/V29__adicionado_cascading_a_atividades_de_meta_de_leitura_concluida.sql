ALTER TABLE user_completed_reading_activities
    DROP CONSTRAINT IF EXISTS fk_user_completed_reading_activities_on_book_user_id;

ALTER TABLE member_completed_reading_activities
    DROP CONSTRAINT IF EXISTS fk_member_completed_reading_activities_on_book_user_id;

ALTER TABLE user_completed_reading_activities
    ADD CONSTRAINT fk_user_completed_reading_activities_on_book_user_id
        FOREIGN KEY (book_id, user_id)
            REFERENCES book_user(book_id, user_id)
            ON DELETE CASCADE;

ALTER TABLE member_completed_reading_activities
    ADD CONSTRAINT fk_member_completed_reading_activities_on_book_user_id
        FOREIGN KEY (book_id, user_id)
            REFERENCES book_user(book_id, user_id)
            ON DELETE CASCADE;