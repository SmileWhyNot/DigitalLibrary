package vlad.kuchuk.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    @NotNull
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters")
    private String name;
    @NotNull
    @Size(min = 2, max = 100, message = "The author must be between 2 and 100 characters")
    private String auther;
    @NotNull
    private int year;

    public Book(int id, String name, String auther, int year) {
        this.id = id;
        this.name = name;
        this.auther = auther;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
