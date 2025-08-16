package com.code81.library.controller;

import com.code81.library.entity.SystemUser;
import com.code81.library.repository.SystemUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final SystemUserRepository repo;
    private final PasswordEncoder encoder;
    public UserController(SystemUserRepository repo, PasswordEncoder encoder){ this.repo=repo; this.encoder=encoder; }

    @GetMapping @PreAuthorize("hasRole('ADMIN')")
    public List<SystemUser> all(){ return repo.findAll(); }

    @GetMapping("/{id}") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SystemUser> one(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SystemUser> create(@RequestBody SystemUser u){
        u.setPassword(encoder.encode(u.getPassword()));
        SystemUser saved = repo.save(u);
        return ResponseEntity.created(URI.create("/api/admin/users/"+saved.getId())).body(saved);
    }

    @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SystemUser> update(@PathVariable Long id, @RequestBody SystemUser u){
        return repo.findById(id).map(existing -> {
            if(u.getPassword()!=null && !u.getPassword().isBlank()){
                u.setPassword(encoder.encode(u.getPassword()));
            } else {
                u.setPassword(existing.getPassword());
            }
            u.setId(id);
            return ResponseEntity.ok(repo.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
