package com.team.teamweb.service;

import com.team.teamweb.domain.Book;
import com.team.teamweb.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    public List<Book> findByDescriptionEndsWith(String des) {
//        return bookRepository.findByDescriptionEndingWith(des);
        return bookRepository.findByDescriptionContains(des);
    }

    public List<Book> findByJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }

    @Transactional
    public int updateByJPQL(int status,long id) {
        return bookRepository.updateByJPQL(status, id);
    }

    @Transactional
    public int deleteByJPQL(long id) {
        return bookRepository.deleteByJPQL(id);
    }

    @Transactional
    public int deleteAndUpdate(long id,int status,long uid) {

        int dcount = bookRepository.deleteByJPQL(id);
        int ucount = bookRepository.updateByJPQL(status, uid);
        return dcount+ucount;
    }
}
