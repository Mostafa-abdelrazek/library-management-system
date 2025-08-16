package com.code81.library.service;
import com.code81.library.entity.Book;
import java.util.List; import java.util.Optional;
public interface BookService {
    Book save(Book b);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void delete(Long id);
}