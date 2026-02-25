package com.Testapp.Book.Service;

import com.Testapp.Book.Entity.BookDetails;
import com.Testapp.Book.Entity.BookIssue;
import com.Testapp.Book.Entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryService {
    private Map<Integer,User> users = new HashMap<>();
    private Map<Integer,BookDetails> books = new HashMap<>();
    private Map<Integer, List<BookIssue>> issuedBooks = new HashMap<>();
    private static final int MAX_ISSUES_PER_USER = 5;
    private static int bookIssueIdCounter = 0;
    private static int userIdCounter = 0;

    public Boolean addUser(User user) {
        users.put(user.getUserId(), user);
        return true;
    }
    public Boolean addBook(BookDetails book) {
        books.put(book.getBookId(), book);
        return true;
    }

    public List<BookDetails> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    // Method to issue a book to a user
    public BookIssue issueBook(int userId, int bookId) {
        LocalDate today = LocalDate.now();
        if(!users.containsKey(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        if(!books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }
        List<BookIssue> userIssues = issuedBooks.getOrDefault(userId, new ArrayList<>());
        if(userIssues.size() >= MAX_ISSUES_PER_USER) {
            throw new IllegalStateException("User has already issued maximum number of books");
        }
        BookIssue issue = new BookIssue((bookIssueIdCounter+1), bookId,userId,today, today.plusWeeks(2));
        userIssues.add(issue);
        issuedBooks.put(userId, userIssues);
        return issue;
    }

    // Method to get all issued books for a user
    public List<BookIssue> getIssuedBooks(int userId) {
        if(!users.containsKey(userId)) {
            throw new IllegalArgumentException(userId + "User not found");
        }
        List<BookIssue> issues = issuedBooks.getOrDefault(userId, List.of());
        if(issues.isEmpty()){
            throw new IllegalStateException("No books issued for user " + userId);
        }
        return issues;
    }

    //Method to return a book
    public void returnBook(int userId, int bookId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        if (!books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }

        List<BookIssue> userIssues = issuedBooks.getOrDefault(userId, new ArrayList<>());

        BookIssue issue = userIssues.stream()
                .filter(i -> i.getBookId() == bookId)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId + " is not issued to user with id " + userId));

        if(issue.getIssueDate() .plusWeeks(2).isAfter(LocalDate.now())) {
            throw new IllegalStateException("Book return is overdue.Yor have to pay fine for late return");
        }
        Boolean removed = userIssues.removeIf(issues -> issues.getBookId() == bookId);
        if (!removed) {
            throw new IllegalStateException("Book with id " + bookId + " is not issued to user with id " + userId);
        }
        issuedBooks.put(userId, userIssues);
    }
    

}
