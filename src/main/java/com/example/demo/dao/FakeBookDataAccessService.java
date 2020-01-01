package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeBookDataAccessService implements BookDao {

    private static List<Book> DB = new ArrayList<>();

    @Override
    public int insertBook(UUID id, Book book) {
        DB.add(new Book(id, book.getTitle(), book.getIsbn(), book.getPrice()));
        return 1;
    }

    @Override
    public List<Book> selectAllBooks() {
        return DB;
    }

    @Override
    public Optional<Book> selectBookById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteBookById(UUID id) {
        Optional<Book> bookToDelete = selectBookById(id);
        if(bookToDelete.isEmpty()) {
            return 0;
        }
        DB.remove(bookToDelete.get());
        return 1;
    }

    public int updateBookById(UUID id, Book book) {
        return selectBookById(id)
                .map(b -> {
                  int indexBookTobeUpdate = DB.indexOf(b);
                  if(indexBookTobeUpdate >= 0) {
                      DB.set(indexBookTobeUpdate, new Book(id, book.getTitle(), book.getIsbn(), book.getPrice()));
                      return 1;
                  }
                  return 0;
                })
                .orElse(0);
    }

}
