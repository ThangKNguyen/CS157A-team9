-- Authors
INSERT INTO Authors (Name) VALUES
('George Orwell'),
('J.K. Rowling'),
('J.R.R. Tolkien'),
('Agatha Christie'),
('Isaac Asimov'),
('Stephen King'),
('Mark Twain'),
('Jane Austen'),
('F. Scott Fitzgerald'),
('Toni Morrison');

-- Categories
INSERT INTO Categories (CategoryName) VALUES
('Fiction'),
('Non-Fiction'),
('Science Fiction'),
('Fantasy'),
('Mystery'),
('Biography'),
('Romance'),
('Historical'),
('Thriller'),
('Self-Help');

-- Genres
INSERT INTO Genres (GenreName) VALUES
('Dystopian'),
('Adventure'),
('Classic'),
('Horror'),
('Young Adult'),
('Literary Fiction'),
('Contemporary'),
('Sci-Fi Fantasy'),
('Detective'),
('Memoir');

-- Books
INSERT INTO Books (Title, ISBN, AuthorID, CategoryID, GenreID, Availability) VALUES
('1984', '9780451524935', 1, 1, 1, 'Available'),
('Harry Potter and the Philosopher\'s Stone', '9780747532699', 2, 4, 5, 'Borrowed'),
('The Hobbit', '9780345339683', 3, 4, 2, 'Available'),
('Murder on the Orient Express', '9780062693662', 4, 5, 5, 'Reserved'),
('Foundation', '9780553293357', 5, 3, 8, 'Available'),
('The Shining', '9780385121675', 6, 1, 4, 'Available'),
('Adventures of Huckleberry Finn', '9780486280615', 7, 1, 1, 'Borrowed'),
('Pride and Prejudice', '9780141439518', 8, 6, 7, 'Available'),
('The Great Gatsby', '9780743273565', 9, 1, 6, 'Available'),
('Beloved', '9781400033416', 10, 1, 1, 'Borrowed');

-- Members
INSERT INTO Members (Name, Email, PhoneNumber, Address, JoinDate, OutstandingFines, Password) VALUES
('Alice Johnson', 'alice@example.com', '123-456-7890', '123 Maple St', '2022-01-15', 0.00, 'alicePass123'),
('Bob Smith', 'bob@example.com', '234-567-8901', '234 Oak St', '2021-07-22', 5.50, 'bobSecure789'),
('Charlie Brown', 'charlie@example.com', '345-678-9012', '345 Pine St', '2020-05-10', 0.00, 'charlieBest456'),
('Diana Prince', 'diana@example.com', '456-789-0123', '456 Elm St', '2023-03-11', 2.25, 'dianaHero987'),
('Edward Elric', 'edward@example.com', '567-890-1234', '567 Birch St', '2022-02-14', 0.00, 'edwardAlchemy123'),
('Felicity Smoak', 'felicity@example.com', '678-901-2345', '678 Cedar St', '2020-09-05', 10.00, 'felicityIT456'),
('George Clooney', 'george@example.com', '789-012-3456', '789 Walnut St', '2021-11-01', 3.75, 'georgeMovie789'),
('Hannah Baker', 'hannah@example.com', '890-123-4567', '890 Cherry St', '2023-08-20', 0.00, 'hannahSecret123'),
('Isaac Newton', 'isaac@example.com', '901-234-5678', '901 Willow St', '2021-12-30', 1.50, 'isaacGravity789'),
('Jessica Jones', 'jessica@example.com', '012-345-6789', '012 Palm St', '2023-04-10', 0.00, 'jessicaDetective456');

-- Reviews
INSERT INTO Reviews (BookID, MemberID, Rating, ReviewText, ReviewDate) VALUES
(1, 1, 5, 'A thought-provoking masterpiece.', '2023-09-01'),
(2, 2, 4, 'An enchanting beginning to a great series.', '2023-09-02'),
(3, 3, 5, 'A classic adventure that never gets old.', '2023-09-03'),
(4, 4, 5, 'A thrilling murder mystery.', '2023-09-04'),
(5, 5, 5, 'Innovative and groundbreaking.', '2023-09-05'),
(6, 6, 4, 'Creepy and captivating.', '2023-09-06'),
(7, 7, 3, 'An interesting take on friendship and freedom.', '2023-09-07'),
(8, 8, 5, 'A beautiful love story.', '2023-09-08'),
(9, 9, 4, 'A great reflection on the American dream.', '2023-09-09'),
(10, 10, 5, 'Powerful and haunting.', '2023-09-10');

-- Borrowing_History
INSERT INTO Borrowing_History (BookID, MemberID, BorrowDate, DueDate, ReturnDate, Status) VALUES
(2, 1, '2023-09-01', '2023-09-15', NULL, 'Borrowed'),
(1, 2, '2023-09-02', '2023-09-16', '2023-09-14', 'Returned'),
(3, 3, '2023-09-03', '2023-09-17', NULL, 'Borrowed'),
(4, 4, '2023-09-04', '2023-09-18', '2023-09-17', 'Returned'),
(5, 5, '2023-09-05', '2023-09-19', NULL, 'Borrowed'),
(6, 6, '2023-09-06', '2023-09-20', NULL, 'Borrowed'),
(7, 7, '2023-09-07', '2023-09-21', '2023-09-20', 'Returned'),
(8, 8, '2023-09-08', '2023-09-22', NULL, 'Borrowed'),
(9, 9, '2023-09-09', '2023-09-23', NULL, 'Borrowed'),
(10, 10, '2023-09-10', '2023-09-24', '2023-09-22', 'Returned');

-- Reservations
INSERT INTO Reservations (BookID, MemberID, ReservationDate, Status) VALUES
(1, 1, '2023-09-11', 'Active'),
(2, 2, '2023-09-12', 'Canceled'),
(3, 3, '2023-09-13', 'Fulfilled'),
(4, 4, '2023-09-14', 'Active'),
(5, 5, '2023-09-15', 'Active'),
(6, 6, '2023-09-16', 'Canceled'),
(7, 7, '2023-09-17', 'Fulfilled'),
(8, 8, '2023-09-18', 'Active'),
(9, 9, '2023-09-19', 'Fulfilled'),
(10, 10, '2023-09-20', 'Active');

-- Fines
INSERT INTO Fines (MemberID, BorrowID, FineAmount, PaidStatus, IssuedDate, FinesDueDate) VALUES
(2, 1, 5.00, 'Unpaid', '2023-09-15', '2023-10-15'),
(4, 4, 2.25, 'Unpaid', '2023-09-18', '2023-10-18'),
(6, 6, 10.00, 'Unpaid', '2023-09-20', '2023-10-20'),
(1, 2, 0.00, 'Paid', '2023-09-14', '2023-10-14'),
(5, 5, 0.00, 'Paid', '2023-09-19', '2023-10-19'),
(7, 7, 3.75, 'Unpaid', '2023-09-20', '2023-10-20'),
(3, 3, 0.00, 'Paid', '2023-09-17', '2023-10-17'),
(8, 8, 0.00, 'Paid', '2023-09-22', '2023-10-22'),
(9, 9, 1.50, 'Unpaid', '2023-09-23', '2023-10-23'),
(10, 10, 0.00, 'Paid', '2023-09-22', '2023-10-22');

-- Staff
INSERT INTO Staff (Name, Role, Email, HireDate, PhoneNumber, Password) VALUES
('Michael Scott', 'Librarian', 'michael@example.com', '2020-01-05', '123-456-7890', 'worldsBestBoss123'),
('Pam Beesly', 'Admin', 'pam@example.com', '2019-03-15', '234-567-8901', 'pamArt789'),
('Jim Halpert', 'Librarian', 'jim@example.com', '2021-05-20', '345-678-9012', 'jimPrankster456'),
('Dwight Schrute', 'Admin', 'dwight@example.com', '2018-11-30', '456-789-0123', 'dwightBeets123'),
('Angela Martin', 'Librarian', 'angela@example.com', '2020-07-22', '567-890-1234', 'angelaCats456'),
('Ryan Howard', 'Admin', 'ryan@example.com', '2021-02-10', '678-901-2345', 'ryanTemp789'),
('Toby Flenderson', 'Librarian', 'toby@example.com', '2019-06-25', '789-012-3456', 'tobyHR987'),
('Kelly Kapoor', 'Admin', 'kelly@example.com', '2020-08-30', '890-123-4567', 'kellyFashion123'),
('Stanley Hudson', 'Librarian', 'stanley@example.com', '2022-04-10', '901-234-5678', 'stanleyPretzels789'),
('Phyllis Vance', 'Admin', 'phyllis@example.com', '2019-10-18', '012-345-6789', 'phyllisYarn456');
