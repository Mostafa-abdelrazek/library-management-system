package com.code81.library.controller;

import com.code81.library.entity.Book;
import com.code81.library.repository.AuthorRepository;
import com.code81.library.repository.BookRepository;
import com.code81.library.repository.CategoryRepository;
import com.code81.library.repository.PublisherRepository;
import com.code81.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    private final AuthorRepository authorRepo;
    private final CategoryRepository categoryRepo;
    private final PublisherRepository publisherRepo;
    private final BookRepository bookRepo;

    public BookController(BookService s, AuthorRepository a, CategoryRepository c, PublisherRepository p, BookRepository b){
        this.service=s; this.authorRepo=a; this.categoryRepo=c; this.publisherRepo=p; this.bookRepo=b;
    }

    @GetMapping public List<Book> all(){ return service.findAll(); }

    @GetMapping("/{id}") public ResponseEntity<Book> one(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    @PostMapping public ResponseEntity<Book> create(@RequestBody Book book){
        Book saved = service.save(book);
        return ResponseEntity.created(URI.create("/api/books/"+saved.getId())).body(saved);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    @PutMapping("/{id}") public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book){
        return service.findById(id).map(existing -> {
            book.setId(id);
            return ResponseEntity.ok(service.save(book));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
