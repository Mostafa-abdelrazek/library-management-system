package com.code81.library.service.impl;
import com.code81.library.entity.*;
import com.code81.library.repository.*;
import com.code81.library.service.BorrowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
@Service
public class BorrowServiceImpl implements BorrowService {
    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;
    private final SystemUserRepository userRepo;
    private final BorrowTransactionRepository txRepo;

    public BorrowServiceImpl(BookRepository b, MemberRepository m, SystemUserRepository u, BorrowTransactionRepository t){
        this.bookRepo=b; this.memberRepo=m; this.userRepo=u; this.txRepo=t;
    }

    @Override @Transactional
    public BorrowTransaction borrow(Long bookId, Long memberId, String username, int days){
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if(book.getAvailableCopies()<=0) throw new IllegalStateException("No available copies");
        Member member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        SystemUser user = userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        BorrowTransaction tx = new BorrowTransaction();
        tx.setBook(book); tx.setMember(member); tx.setHandledBy(user);
        tx.setDueAt(LocalDateTime.now().plusDays(days<=0?14:days));
        tx.setStatus(BorrowTransaction.Status.BORROWED);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepo.save(book);
        return txRepo.save(tx);
    }

    @Override @Transactional
    public BorrowTransaction returnBook(Long transactionId, String username){
        BorrowTransaction tx = txRepo.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        if(tx.getStatus()== BorrowTransaction.Status.RETURNED) return tx;
        SystemUser user = userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        tx.setReturnedAt(LocalDateTime.now());
        tx.setReturnHandledBy(user);
        if(tx.getDueAt()!=null && tx.getReturnedAt().isAfter(tx.getDueAt())) tx.setStatus(BorrowTransaction.Status.OVERDUE);
        else tx.setStatus(BorrowTransaction.Status.RETURNED);
        Book book = tx.getBook();
        book.setAvailableCopies(book.getAvailableCopies()+1);
        return txRepo.save(tx);
    }
}