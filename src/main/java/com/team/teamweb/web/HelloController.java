package com.team.teamweb.web;

import com.team.teamweb.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/api/v1")
public class HelloController {

    //    @RequestMapping(value = "/say")
    @PostMapping("/say")
    public String hello() {
        return "hello spring";
    }

    @GetMapping("/books")
//    @ResponseBody
    public Object getall(@RequestParam("page") int page,@RequestParam(value = "size",defaultValue = "10") int size) {

        Map<String, Object> book = new HashMap<>();
        book.put("name", "互聯網世界觀");
        book.put("isbn", "9845631");
        book.put("author", "李道東");
        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "台灣");
        book2.put("isbn", "536987");
        book2.put("author", "auhi");

        List<Map> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);

        Map<String,Object> pagemap= new HashMap<>();
        pagemap.put("page", page);
        pagemap.put("size", size);
        pagemap.put("content", contents);

        return pagemap;
    }

    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id) {

        return null;
    }

    @PostMapping("/books")
    public Object post(@RequestParam("name") String name,
                       @RequestParam("author") String author,
                       @RequestParam("isbn") String isbn){
        Map<String, Object> book = new HashMap<String, Object>();
        book.put("name", name);
        book.put("author",author);
        book.put("isbn", isbn);
        return book;
    }
}
