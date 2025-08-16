package com.code81.library.controller;

import com.code81.library.entity.Member;
import com.code81.library.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberRepository repo;
    public MemberController(MemberRepository repo){ this.repo=repo; }

    @GetMapping public List<Member> all(){ return repo.findAll(); }

    @GetMapping("/{id}") public ResponseEntity<Member> one(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN','STAFF')")
    @PostMapping public ResponseEntity<Member> create(@RequestBody Member m){
        Member saved = repo.save(m);
        return ResponseEntity.created(URI.create("/api/members/"+saved.getId())).body(saved);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN','STAFF')")
    @PutMapping("/{id}") public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody Member m){
        return repo.findById(id).map(existing -> {
            m.setId(id);
            return ResponseEntity.ok(repo.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
