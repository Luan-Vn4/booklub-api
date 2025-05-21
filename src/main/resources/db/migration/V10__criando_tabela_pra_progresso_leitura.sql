CREATE TABLE IF NOT EXISTS book_user_progress (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id VARCHAR(36) NOT NULL REFERENCES user_entity(id),
    book_id UUID NOT NULL,
    progress FLOAT
);
