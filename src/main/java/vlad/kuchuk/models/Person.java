package vlad.kuchuk.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @NotNull
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters")
    // TODO: Unique in Spring Validator
    private String fullName;
    @NotNull
    private int yearOfBirth;

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
