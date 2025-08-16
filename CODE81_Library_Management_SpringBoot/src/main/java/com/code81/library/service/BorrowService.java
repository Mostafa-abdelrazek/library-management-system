package com.code81.library.service;
import com.code81.library.entity.BorrowTransaction;
public interface BorrowService {
    BorrowTransaction borrow(Long bookId, Long memberId, String username, int days);
    BorrowTransaction returnBook(Long transactionId, String username);
}