package vlad.kuchuk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad.kuchuk.models.Book;
import vlad.kuchuk.models.Person;
import vlad.kuchuk.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public List<Book> findBooksByReader(Person reader) {
        return booksRepository.findByReader(reader);
    }

    public Person findReader(int id){
        Optional<Book> book = booksRepository.findById(id);
        return book.map(Book::getReader).orElse(null);
    }

    @Transactional
    public void removeBookReader(int id) {
        Optional<Book> book = booksRepository.findById(id);
        book.ifPresent(value -> value.setReader(null));
    }

    @Transactional
    public void setBookReader(int id, Person person) {
        Optional<Book> book = booksRepository.findById(id);
        book.ifPresent(value -> value.setReader(person));
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book updatedBook, int id) {
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
