package com.sh.memorydb.book.controller;

import com.sh.memorydb.book.db.entity.BookEntity;
import com.sh.memorydb.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;

    @PostMapping("")
    public BookEntity create(
            @RequestBody BookEntity bookEntity
    ){
        return bookService.create(bookEntity);
    }

    @GetMapping("/all")
    public List<BookEntity> finalAll(){
         return bookService.findAll();
    }
}



