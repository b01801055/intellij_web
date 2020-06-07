package com.team.teamweb.service;

import com.team.teamweb.domain.Book;
import com.team.teamweb.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findOne(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void delete(long id) {
        bookRepository.deleteById(id);
    }
}
