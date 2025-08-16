package com.code81.library.service.impl;
import com.code81.library.entity.Book;
import com.code81.library.repository.BookRepository;
import com.code81.library.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List; import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repo;
    public BookServiceImpl(BookRepository repo){ this.repo = repo; }
    public Book save(Book b){ return repo.save(b); }
    public Optional<Book> findById(Long id){ return repo.findById(id); }
    public List<Book> findAll(){ return repo.findAll(); }
    public void delete(Long id){ repo.deleteById(id); }
}