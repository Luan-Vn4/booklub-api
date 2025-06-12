UPDATE book_ratings SET rating = 0 WHERE rating IS NULL;
ALTER TABLE book_ratings ALTER COLUMN rating SET NOT NULL;

UPDATE book_ratings SET dificulty = 0 WHERE dificulty IS NULL;
ALTER TABLE book_ratings ALTER COLUMN dificulty SET NOT NULL;

ALTER TABLE book_ratings RENAME COLUMN dificulty TO difficulty;
