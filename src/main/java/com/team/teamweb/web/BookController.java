package com.team.teamweb.web;

import com.team.teamweb.domain.Book;
import com.team.teamweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
//    public String list(@RequestParam(defaultValue = "0") int page,
//                       @RequestParam(defaultValue = "5") int size,
//                       Model model) {
      public String list(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                         Model model){
//        List<Book> books = bookService.findAll();
//        Sort sort = Sort.by(Sort.Order.desc("id"));
//        Page<Book> page1 = bookService.findAllByPage(PageRequest.of(page, size,sort));
        Page<Book> page1 = bookService.findAllByPage(pageable);
        model.addAttribute("page",page1);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {

        Book book = bookService.findOne(id);
        if (book == null) {
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/books/input")
    public String inputPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }

    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "input";
    }

    @PostMapping("/books")
    public String post(Book book, final RedirectAttributes attributes) {
        Book book1 = bookService.save(book);
        if (book1 != null) {
            attributes.addFlashAttribute("message", "<<" + book1.getName() + ">> 信息提交成功");
        }
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id,final RedirectAttributes attributes) {
        bookService.delete(id);
        attributes.addFlashAttribute("message","刪除成功");
        return "redirect:/books";
    }
}
