package com.sh.memorydb.book.service;

import com.sh.memorydb.book.db.entity.BookEntity;
import com.sh.memorydb.book.db.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //create, update
    public BookEntity create(BookEntity book) {
        return bookRepository.save(book);
    }

    //all
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    //delete
    public void delete(Long id) {
        //bookRepository.delete(id);
    }

    //findone 직접 만들기
    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

}
