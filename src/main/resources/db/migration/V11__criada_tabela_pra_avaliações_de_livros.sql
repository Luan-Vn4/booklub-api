CREATE TABLE IF NOT EXISTS book_ratings (
    user_id VARCHAR(36) NOT NULL REFERENCES user_entity(id),
    book_id UUID NOT NULL,
    rating SMALLINT,
    dificulty SMALLINT,
    review VARCHAR(400),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    
    PRIMARY KEY (user_id, book_id),

    CONSTRAINT book_user_id FOREIGN KEY (user_id, book_id)
        REFERENCES book_user(user_id, book_id)
);
