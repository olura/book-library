package ru.olura.dao;

import ru.olura.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    int add(Book book);

    int update(Book book);

    int delete(Long id);

    List<Book> index();

    Optional<Book> findById(Long id);

    List<Book> findBookPerson(Long id);

}
