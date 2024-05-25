CREATE TABLE application (
    id UUID PRIMARY KEY NOT NULL,
    club VARCHAR(255),
    race_id UUID NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (race_id) REFERENCES race (id),
    FOREIGN KEY (user_id) REFERENCES _user (id)
);