package ru.olura.dao;

import ru.olura.models.Book;
import ru.olura.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

    int add(Person person);
    int update(Person person);
    int delete(Long id);
    List<Person> index();
    Optional<Person> findById(Long id);
    List<Book> getPersonBooks(Long id);
}
