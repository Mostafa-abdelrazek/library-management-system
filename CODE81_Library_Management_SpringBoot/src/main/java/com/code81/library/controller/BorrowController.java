package com.code81.library.controller;

import com.code81.library.dto.BorrowRequest;
import com.code81.library.dto.ReturnRequest;
import com.code81.library.entity.BorrowTransaction;
import com.code81.library.repository.BorrowTransactionRepository;
import com.code81.library.service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/librarian/transactions")
public class BorrowController {
    private final BorrowService service;
    private final BorrowTransactionRepository txRepo;
    public BorrowController(BorrowService service, BorrowTransactionRepository txRepo){
        this.service=service; this.txRepo=txRepo;
    }

    @GetMapping @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public List<BorrowTransaction> all(){ return txRepo.findAll(); }

    @PostMapping("/borrow") @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN','STAFF')")
    public ResponseEntity<BorrowTransaction> borrow(@RequestBody BorrowRequest req, Authentication auth){
        BorrowTransaction tx = service.borrow(req.bookId, req.memberId, auth.getName(), req.days==null?14:req.days);
        return ResponseEntity.created(URI.create("/api/librarian/transactions/"+tx.getId())).body(tx);
    }

    @PostMapping("/return") @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN','STAFF')")
    public ResponseEntity<BorrowTransaction> returnBook(@RequestBody ReturnRequest req, Authentication auth){
        BorrowTransaction tx = service.returnBook(req.transactionId, auth.getName());
        return ResponseEntity.ok(tx);
    }
}
