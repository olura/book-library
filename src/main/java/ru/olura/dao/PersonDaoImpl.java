package ru.olura.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.olura.models.Book;
import ru.olura.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDaoImpl implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    private final BookDao bookDao;

    @Autowired
    public PersonDaoImpl(JdbcTemplate jdbcTemplate, BookDao bookDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookDao = bookDao;
    }

    @Override
    public int add(Person person) {
        final String SQL = "INSERT INTO person (name, birthDate) VALUES (?, ?)";
        return jdbcTemplate.update(SQL, person.getName(), person.getBirthDate());
    }

    @Override
    public int update(Person person) {
        final String SQL = "UPDATE person SET name=?, birthdate=? WHERE id = ?";
        return jdbcTemplate.update(SQL, person.getName(), person.getBirthDate(), person.getId());
    }

    @Override
    public int delete(Long id) {
        final String SQL = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<Person> index() {
        final String ALL_PERSON_SQL = "SELECT * FROM person";
        return jdbcTemplate.query(ALL_PERSON_SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Optional<Person> findById(Long id) {
        final String FIND_BY_ID_SQL = "SELECT * FROM person WHERE id=?";
        return jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    @Override
    public List<Book> getPersonBooks(Long id) {
        return bookDao.findBookPerson(id);
    }
}
