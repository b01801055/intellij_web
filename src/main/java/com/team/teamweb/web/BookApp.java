package com.team.teamweb.web;

import com.team.teamweb.domain.Book;
import com.team.teamweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
//    public Page<Book> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {
//        Sort sort = Sort.by(Sort.Order.desc("id"));
//        return bookService.findAllByPage(PageRequest.of(page, size,sort));
//    }
    public Page<Book> getAll(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable) {
        return bookService.findAllByPage(pageable);
    }
//    public List<Book> getAll() {
//        return bookService.findAll();
//    }
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

    @PostMapping("/books/by")
//    public List<Book> findBy(@RequestParam String author, @RequestParam int status) {
//        return bookService.findByAuthor(author);
//        return bookService.findByAuthorAndStatus(author, status);
//    }
//    public List<Book> findBy(@RequestParam String description) {
//        return bookService.findByDescriptionEndsWith(description);
//    }
//    public List<Book> findBy(@RequestParam int len) {
//        return bookService.findByJPQL(len);
//    }
//    public int findBy(@RequestParam int status, @RequestParam long id) {
//        return bookService.updateByJPQL(status, id);
//    }
//    public int findBy(@RequestParam long id) {
//        return bookService.deleteByJPQL(id);
//    }
    public int findBy(@RequestParam long id,@RequestParam int status,@RequestParam long uid) {
        return bookService.deleteAndUpdate(id, status, uid);
    }
}
