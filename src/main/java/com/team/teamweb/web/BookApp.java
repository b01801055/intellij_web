package com.team.teamweb.web;

import com.team.teamweb.domain.Book;
import com.team.teamweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }
//    @RequestParam String name,
//    @RequestParam String author,
//    @RequestParam String description,
//    @RequestParam int status
    @PostMapping("/books")
    public Book post(Book book) {
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        book.setDescription(description);
//        book.setStatus(status);
        return bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id) {
        return bookService.findOne(id);
    }

    @PutMapping("/books")
    public Book post(@RequestParam long id,
                     @RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam int status) {

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id) {
        bookService.delete(id);
    }
}
