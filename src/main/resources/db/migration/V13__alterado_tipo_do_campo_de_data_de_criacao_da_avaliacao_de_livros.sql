ALTER TABLE book_ratings
    ALTER COLUMN created_at TYPE date
    USING created_at::date;