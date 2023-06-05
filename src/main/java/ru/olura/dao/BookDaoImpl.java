package ru.olura.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.olura.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int add(Book book) {
        final String SQL = "INSERT INTO book(name, author, year, person_id) VALUES (?,?,?,?)";
        return jdbcTemplate.update(SQL, book.getName(), book.getAuthor(), book.getYear(), book.getPersonId());
    }

    @Override
    public int update(Book book) {
        final String SQL = "UPDATE book SET name=?, author=?, year=?, person_id=? WHERE id=?";
        return jdbcTemplate.update(SQL, book.getName(), book.getAuthor(), book.getYear(), book.getPersonId(), book.getId());
    }

    @Override
    public int delete(Long id) {
        final String SQL = "DELETE FROM book WHERE id=?";
        return jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<Book> index() {
        final String SQL = "SELECT * FROM book";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Optional<Book> findById(Long id) {
        final String SQL = "SELECT * FROM book WHERE id=?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    @Override
    public List<Book> findBookPerson(Long id) {
        final String SQL = "SELECT * FROM book WHERE person_id=?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
