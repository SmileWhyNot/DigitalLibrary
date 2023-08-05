package vlad.kuchuk.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;
    @NotNull
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters")
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(min = 2, max = 100, message = "The author must be between 2 and 100 characters")
    @Column(name = "auther")
    private String auther;
    @NotNull
    @Min(value = 1500, message = "Year must be higher than 1500")
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person reader;

    public Book(int bookId, String name, String auther, int year) {
        this.bookId = bookId;
        this.name = name;
        this.auther = auther;
        this.year = year;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }
}
