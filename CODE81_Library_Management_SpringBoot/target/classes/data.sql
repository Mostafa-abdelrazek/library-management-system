INSERT INTO authors (id, name, bio) VALUES
(1, 'George Orwell', 'English novelist and essayist'),
(2, 'J.K. Rowling', 'British author');

INSERT INTO publishers (id, name, address, website) VALUES
(1, 'Penguin Random House', '80 Strand, London', 'https://www.penguinrandomhouse.com'),
(2, 'Bloomsbury', '50 Bedford Square, London', 'https://www.bloomsbury.com');

INSERT INTO categories (id, name, description) VALUES
(1, 'Fiction', 'Fiction root category'),
(2, 'Dystopian', 'Subcategory of Fiction'),
(3, 'Fantasy', 'Fantasy genre');

UPDATE categories SET parent_id = 1 WHERE id IN (2,3);

INSERT INTO books (id, title, isbn, edition, language, publication_year, summary, cover_image_url, total_copies, available_copies, publisher_id) VALUES
(1, '1984', '9780451524935', '1st', 'EN', 1949, 'A totalitarian regime ...', NULL, 5, 5, 1),
(2, 'Harry Potter and the Philosopher''s Stone', '9780747532699', '1st', 'EN', 1997, 'A young wizard ...', NULL, 3, 3, 2);

INSERT INTO book_authors (book_id, author_id) VALUES (1,1),(2,2);
INSERT INTO book_categories (book_id, category_id) VALUES (1,2),(2,3);

INSERT INTO members (id, first_name, last_name, email, phone, address, membership_date, active) VALUES
(1, 'Ahmed', 'Ali', 'ahmed@example.com', '0100000000', 'Cairo', CURRENT_DATE, true),
(2, 'Sara', 'Hassan', 'sara@example.com', '0100000001', 'Giza', CURRENT_DATE, true);
