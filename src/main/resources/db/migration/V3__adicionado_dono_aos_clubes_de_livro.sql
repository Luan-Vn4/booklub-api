ALTER TABLE clubs ADD COLUMN
    owner_id UUID NOT NULL REFERENCES users(id);