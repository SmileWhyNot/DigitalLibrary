# Library Management System - Spring MVC CRUD App

This project is a web application designed for a local library to switch to digital accounting of books. It allows librarians to manage readers, books, and book assignments efficiently. The application is built using Spring MVC, Hibernate, Spring Data JPA, and PostgreSQL.

## Table of Contents
1. [Introduction](#introduction)
2. [Entities](#entities)
3. [Provided Functionality](#provided-functionality)
    - [Request Types Handled by Controllers](#request-types-handled-by-controllers)
4. [Project Structure](#project-structure)
5. [Setup](#setup)
6. [Dependencies](#dependencies)
7. [Release v1.0.0](#release-v100)
8. [Release v2.0.0](#release-v200)

## Introduction

The aim of this project is to create a digital library management system where librarians can manage readers, books, and book assignments. The application provides a user-friendly UI with API development for performing CRUD (Create, Read, Update, Delete) operations on both readers and books. It also allows librarians to assign books to readers and release them when returned.

## Entities

The application deals with two main entities: `Person` and `Book`.

### Person Entity

The `Person` entity represents a reader and contains the following fields:
- Full Name (UNIQUE)
- Year of Birth

### Book Entity

The `Book` entity represents a book and contains the following fields:
- Title
- Author
- Year of Publication
- Date of taking for reading

The relationship between entities is "One to Many":
- A person can have many books.
- A book can only belong to one person.

## Provided Functionality

The application provides the following functionality:

1. Pages for adding, changing, and deleting a person.
2. Pages for adding, changing, and deleting a book.
3. A page listing all people, where each person's name is clickable, leading to the person's page.
4. A page listing all books, where each book is clickable, leading to the book's page.
5. A person's page showing their details and a list of books they have taken. If a person hasn't taken any books, the list displays the text "The person has not taken any books yet."
6. A book's page showing its details and the name of the person who has taken it. If the book is not assigned to anyone, it displays the text "This book is free."
7. On the book's page, if the book is taken by a person, there is a "Release the book" button next to the person's name. Clicking this button will mark the book as free and remove it from the person's list of books.
8. On the book's page, if the book is free, there is a dropdown list of all people and an "Assign a book" button. Clicking this button allows the librarian to assign the book to the selected person, and it will appear in their list of books.
9. All fields are validated using @Valid and Spring Validator, as required.

### Request Types Handled by Controllers

#### BooksController
1. **GET /books**:
    - Request Parameters:
        - `page` (optional): The page number for pagination.
        - `books_per_page` (optional): The number of books to display per page for pagination.
        - `sort_by_year` (optional): Sorts the books by year of publication if set to `true`.

2. **GET /books/{id}**:
    - Path Variable: `id` - The ID of the book to display.

3. **PATCH /books/{id}/updateReader**:
    - Path Variable: `id` - The ID of the book to update.
    - Request Body: `person` - The person entity representing the reader to assign or release the book to.

4. **GET /books/newBook**:
    - Description: Displays the form to add a new book.

5. **POST /books**:
    - Description: Creates a new book based on the form data submitted.
    - Request Body: `book` - The book entity containing the details of the new book.

6. **GET /books/search**:
    - Description: Displays the search page to search for books.

7. **POST /books/search**:
    - Description: Searches for books whose titles start with the provided search string.
    - Request Parameters: `searchStr` (optional) - The search string to look for in book titles.
    - Response: Displays the search results on the search page.

8. **GET /books/{id}/editBook**:
    - Description: Displays the form to edit the details of a specific book.
    - Path Variable: `id` - The ID of the book to edit.

9. **PATCH /books/{id}**:
    - Description: Updates the details of a specific book based on the form data submitted.
    - Path Variable: `id` - The ID of the book to update.
    - Request Body: `book` - The book entity containing the updated details of the book.

10. **DELETE /books/{id}**:
    - Description: Deletes a specific book.
    - Path Variable: `id` - The ID of the book to delete.

#### PeopleController
1. **GET /people**:
    - Description: Fetches all people (readers) and displays them in the index page.

2. **GET /people/{id}**:
    - Description: Fetches details of a specific person (reader) and displays it on the person's page.
    - Path Variable: `id` - The ID of the person to display.

3. **GET /people/newPerson**:
    - Description: Displays the form to add a new person (reader).

4. **POST /people**:
    - Description: Creates a new person (reader) based on the form data submitted.
    - Request Body: `person` - The person entity containing the details of the new person.

5. **GET /people/{id}/editPerson**:
    - Description: Displays the form to edit the details of a specific person (reader).
    - Path Variable: `id` - The ID of the person to edit.

6. **PATCH /people/{id}**:
    - Description: Updates the details of a specific person (reader) based on the form data submitted.
    - Path Variable: `id` - The ID of the person to update.
    - Request Body: `person` - The person entity containing the updated details of the person.

7. **DELETE /people/{id}**:
    - Description: Deletes a specific person (reader).
    - Path Variable: `id` - The ID of the person to delete.


## Project Structure

The project follows the standard Spring MVC structure and contains the following main files:

- `MySpringMvcDispatcherServletInitializer.java` - Initializes the Spring Dispatcher Servlet.
- `SpringConfig.java` - Contains the Spring configuration.
- `BooksController.java` - Controller for handling book-related operations.
- `PeopleController.java` - Controller for handling person-related operations.
- `Book.java` - Model class representing the Book entity.
- `Person.java` - Model class representing the Person entity.
- `PersonValidator.java` - Validator for validating Person entities.
- `BookRepository.java` - Spring Data JPA repository for the Book entity, handling database interactions.
- `PersonRepository.java` - Spring Data JPA repository for the Person entity, handling database interactions.
- `BookService.java` - Service class for business logic related to books.
- `PersonService.java` - Service class for business logic related to people.
- HTML templates for rendering the web pages are stored in the `src/main/webapp/WEB-INF/views` directory, with separate packages for books and people.

## Setup

To set up the project and configure communication with PostgreSQL, follow these steps:

1. Clone the project repository to your local machine.
2. Create a PostgreSQL database with tables, generated example is in `src/main/resources/dbTablesGeneration.sql`
3. Create `hibernate.properties` in `src/main/resources/` with the appropriate credentials and database name. Structure is here: `src/main/resources/hibernate.properties.origin`
4. Run the application on a server (e.g., Tomcat) to access it in your web browser.

## Dependencies

The project relies on the following dependencies:

- `spring-core`, `spring-context`, `spring-web`, `spring-jdbc`, `spring-webmvc` - Spring Framework libraries for MVC and data access.
- `thymeleaf-spring6` - Thymeleaf library for HTML template rendering.
- `jakarta.servlet-api` - Jakarta Servlet API.
- `hibernate-validator` - Library for validation purposes.
- `hibernate-core`, `hibernate-entitymanager` - Hibernate libraries for JPA support.
- `spring-data-jpa` - Spring Data JPA for simplified data access.
- `postgresql` - PostgreSQL JDBC driver for communication with the database.

Make sure to have these dependencies properly configured in your project's build file (e.g., pom.xml if using Maven).

## Release v1.0.0

In release v1.0.0, the Library Management System was initially developed using Spring MVC, PostgreSQL, and JdbcTemplate. CRUD functionality for managing readers and books was implemented along with the assignment and release of books to and from readers.

## Release v2.0.0

In release v2.0.0, significant updates and improvements were made to the Library Management System:

- The codebase was rewritten using Hibernate and Spring Data JPA.
- The models `Book` and `Person` were turned into `@Entity` classes, and DAO classes were replaced with Spring Data JPA repositories.
- Pagination functionality was added for showing books, improving the performance of listing large book collections.
- The capability to sort books by year of publication was introduced, providing better organization for the librarian.
- A search page was implemented, allowing users to search for books by their titles and displaying the book and reader details if available.
- An automatic check for overdue books was introduced, marking books as overdue if they haven't been returned within ten days.

With these updates, the application's database interaction and performance have been enhanced, and new features have been added to improve the user experience and efficiency of managing the library's collection.

Enjoy using the enhanced Library Management System!




#### PeopleController



With these request mappings, your Library Management System provides various endpoints to perform CRUD operations on books and people, enabling efficient management of the library's collection and reader assignments.