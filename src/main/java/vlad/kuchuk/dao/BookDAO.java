package vlad.kuchuk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import vlad.kuchuk.models.Book;
import vlad.kuchuk.models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book"
                , new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?"
                , new Object[]{id}
                , new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, auther, year) VALUES (?, ?, ?)",
                book.getName(), book.getAuther(), book.getYear());
    }


    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, auther=?, year=? WHERE book_id=?",
                book.getName(), book.getAuther(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public Person getReader(int id) {
        return jdbcTemplate.query("SELECT person.full_name, person.year_of_birth FROM person INNER JOIN book on person.person_id = book.person_id WHERE book.book_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void removeBookReader(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", null, id);
    }

    public void setBookReader(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person.getPersonId(), id);
    }
}
