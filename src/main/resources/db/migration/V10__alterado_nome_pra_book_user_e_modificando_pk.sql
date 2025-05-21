ALTER TABLE book_user_progress RENAME TO book_user;

ALTER TABLE book_user DROP CONSTRAINT book_user_progress_pkey;

ALTER TABLE book_user DROP COLUMN id;

ALTER TABLE book_user ADD PRIMARY KEY (user_id, book_id);
