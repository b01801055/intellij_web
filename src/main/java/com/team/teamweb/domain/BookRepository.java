package com.team.teamweb.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAll(Pageable pageable);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author,int status);

//    List<Book> findByDescriptionEndingWith(String des);

    List<Book> findByDescriptionContains(String des);

//    @Query("SELECT b FROM Book b WHERE LENGTH(b.name) > ?1 ")
    @Query(value = "SELECT * FROM book WHERE LENGTH(name) > ?1", nativeQuery = true)
    List<Book> findByJPQL(int len);

    @Transactional
    @Modifying
    @Query("UPDATE  Book b SET b.status = ?1 WHERE b.id = ?2")
    int updateByJPQL(int status,long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = ?1")
    int deleteByJPQL(long id);
}
