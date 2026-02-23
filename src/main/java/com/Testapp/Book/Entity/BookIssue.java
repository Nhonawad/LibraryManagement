package com.Testapp.Book.Entity;

import java.time.LocalDate;

public class BookIssue {
    private int issueId;
    private int bookId;
    private int userId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public int getBookId() {
        return bookId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public int getIssueId() {
        return issueId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getUserId() {
        return userId;
    }

    public BookIssue(int issueId, int bookId, int userId, LocalDate issueDate, LocalDate returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.userId = userId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }
}
