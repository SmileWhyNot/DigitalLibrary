package vlad.kuchuk.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    private int bookId;
    @NotNull
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters")
    private String name;
    @NotNull
    @Size(min = 2, max = 100, message = "The author must be between 2 and 100 characters")
    private String auther;
    @NotNull
    @Min(value = 1500, message = "Year must be higher than 1500")
    private int year;

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
}
