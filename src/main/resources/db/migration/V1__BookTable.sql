CREATE TABLE book (
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    isbn INT NOT NULL,
    price FLOAT  NOT NULL
);