package com.code81.library.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_transactions")
public class BorrowTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Book book;

    @ManyToOne(optional = false)
    private Member member;

    @ManyToOne(optional = false)
    private SystemUser handledBy;

    @ManyToOne
    private SystemUser returnHandledBy;

    private LocalDateTime borrowedAt = LocalDateTime.now();
    private LocalDateTime dueAt;
    private LocalDateTime returnedAt;

    @Enumerated(EnumType.STRING)
    private Status status = Status.BORROWED;

    public enum Status { BORROWED, RETURNED, OVERDUE }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public SystemUser getHandledBy() { return handledBy; }
    public void setHandledBy(SystemUser handledBy) { this.handledBy = handledBy; }
    public SystemUser getReturnHandledBy() { return returnHandledBy; }
    public void setReturnHandledBy(SystemUser returnHandledBy) { this.returnHandledBy = returnHandledBy; }
    public LocalDateTime getBorrowedAt() { return borrowedAt; }
    public void setBorrowedAt(LocalDateTime borrowedAt) { this.borrowedAt = borrowedAt; }
    public LocalDateTime getDueAt() { return dueAt; }
    public void setDueAt(LocalDateTime dueAt) { this.dueAt = dueAt; }
    public LocalDateTime getReturnedAt() { return returnedAt; }
    public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
