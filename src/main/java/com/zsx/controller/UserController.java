package com.zsx.controller;

import com.zsx.entity.Author;
import com.zsx.entity.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setName("赵飞燕");
        return book;
    }

    @GetMapping("/book/author/{name}")
    public Book getBookByAuthorName(@PathVariable("name") String name) {
        Book book = new Book();
        Author author = new Author();
        author.setFirstName(name);
        book.setId(1);
        book.setName("xxx");
        book.setAuthor(author);
        return book;
    }

    @GetMapping("/book")
    public Book getBookByName(String name) {
        Book book = new Book();
        book.setId(1);
        book.setName(name);
        return book;
    }

    @PostMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return book;
    }

    @PostMapping("/author")
    public Author updateAuthor(Author author) {
        return author;
    }
}
