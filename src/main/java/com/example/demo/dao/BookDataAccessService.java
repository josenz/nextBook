package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class BookDataAccessService implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBook(UUID id, Book book) {
        jdbcTemplate.update("INSERT INTO book(id, title, isbn, price) VALUES(?, ?, ?, ?)",
                id, book.getTitle(), book.getIsbn(), book.getPrice()
        );
        return 1;
    }

//    @Override
//    public List<Book> selectAllBooks() {
//        return List.of(new Book(UUID.randomUUID(),
//                "Postgres Advanced",
//                41235,
//                34.50
//        ));
//    }

    @Override
    public List<Book> selectAllBooks() {
        final String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            int isbn = resultSet.getInt("isbn");
            double price = resultSet.getDouble("price");
            return new Book(id, title, isbn, price);
        });
    }

    @Override
    public Optional<Book> selectBookById(UUID id) {
        final String sql = "SELECT * FROM book WHERE id = ?";
        Book book = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID bookId = UUID.fromString(resultSet.getString("id"));
                    String title = resultSet.getString("title");
                    int isbn = resultSet.getInt("isbn");
                    double price = resultSet.getDouble("price");
                    return new Book(bookId, title, isbn, price);
                });
        return Optional.ofNullable(book);
    }

    @Override
    public int deleteBookById(UUID id) {
        final String sql = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int updateBookById(UUID id, Book book) {
        final String sql = "UPDATE book SET title = ?, isbn = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                book.getTitle(),
                book.getIsbn(),
                book.getPrice(),
                id);
        return 1;
    }
}
