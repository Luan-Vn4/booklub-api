ALTER TABLE book_ratings DROP CONSTRAINT IF EXISTS book_ratings_user_id_fkey;

ALTER TABLE book_user DROP CONSTRAINT IF EXISTS book_user_progress_user_id_fkey;

ALTER TABLE book_ratings ADD CONSTRAINT book_ratings_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES user_entity(id) ON DELETE CASCADE;

ALTER TABLE book_user ADD CONSTRAINT book_user_progress_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES user_entity(id) ON DELETE CASCADE;