package com.code81.library.dto;
public class BorrowRequest {
    public Long bookId;
    public Long memberId;
    public Integer days; // optional, default 14
}