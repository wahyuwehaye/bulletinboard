-- Create table for posts
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(10) NOT NULL,
    password VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    view_count INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);


INSERT INTO posts (title, author, password, content) VALUES
('Welcome to the Bulletin Board', 'Admin', 'adminpass', 'This is the first post.');
