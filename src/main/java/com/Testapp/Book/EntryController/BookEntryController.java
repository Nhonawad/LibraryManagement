package com.Testapp.Book.EntryController;

import com.Testapp.Book.Entity.BookDetails;
import com.Testapp.Book.Entity.BookIssue;
import com.Testapp.Book.Entity.User;
import com.Testapp.Book.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookEntryController {

    @Autowired
    private LibraryService libraryService;


    @GetMapping("/books")
    public List<BookDetails> getBookDetails(){
        return libraryService.getAllBooks();
    }

    @PostMapping("/books")
    public Boolean addBookDetails(@RequestBody BookDetails bookDetails){
       if(libraryService.addBook(bookDetails)){
           return true;
       }
       else{
           System.out.println("Failed to add book details");
           return false;
       }
    }

    @PostMapping("/user")
    public Boolean addUserDetails(@RequestBody User user){
        if(libraryService.addUser(user)){
            return true;
        }
        else{
            System.out.println("Failed to add user details");
            return false;
        }
     }

     @GetMapping("/user")
     public List<User> getUserDetails() {
         return libraryService.getAllUsers();
     }

     @PostMapping("/issue")
     public Boolean issueBook(@RequestParam int userId, @RequestParam int bookId) {
         try {
             libraryService.issueBook(userId, bookId);
             return true;
         } catch (IllegalArgumentException | IllegalStateException e) {
             System.out.println(e.getMessage());
             return false;
         }
     }

     @PutMapping("getIssuedBooks")
     public List<Map<String, Object>> getIssuedBooks(@RequestParam int userId) {
         List<Map<String, Object>> result = new ArrayList<>();
         try {
             List<BookIssue> issuedBooks = libraryService.getIssuedBooks(userId);
             for (BookIssue book : issuedBooks) {
                 Map<String, Object> bookInfo = new HashMap<>();
                 bookInfo.put("bookId", book.getBookId());
                 bookInfo.put("title", book.getUserId());
                 result.add(bookInfo);
             }
         } catch (IllegalArgumentException e) {
             System.out.println(e.getMessage());
         } catch (IllegalStateException e) {
             System.out.println(e.getMessage());         }
         return result;
     }

     @PutMapping("/return")
        public Boolean returnBook(@RequestParam int userId, @RequestParam int bookId){
            try {
                libraryService.returnBook(userId, bookId);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return false;
            }
     }
}
