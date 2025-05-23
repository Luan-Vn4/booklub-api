ALTER TABLE book_user_progress RENAME TO book_user;

ALTER TABLE book_user DROP CONSTRAINT book_user_progress_pkey;

ALTER TABLE book_user DROP COLUMN id;

ALTER TABLE book_user ADD PRIMARY KEY (user_id, book_id);

CREATE TABLE IF NOT EXISTS book_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id VARCHAR(36) NOT NULL REFERENCES user_entity(id),
    book_id UUID NOT NULL,
    progress FLOAT
);