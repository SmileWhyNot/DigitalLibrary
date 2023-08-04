# Library Management System - Spring MVC CRUD App

This project is a web application designed for a local library to switch to digital accounting of books. It allows librarians to manage readers, books, and book assignments efficiently. The application is built using Spring MVC, PostgreSQL, and JdbcTemplate.

## Table of Contents
1. [Introduction](#introduction)
2. [Entities](#entities)
3. [Provided Functionality](#provided-functionality)
4. [Project Structure](#project-structure)
5. [Setup](#setup)
6. [Dependencies](#dependencies)

## Introduction

The aim of this project is to create a digital library management system where librarians can manage readers, books, and book assignments. The application provides very simple UI, the emphasis was on API development for performing CRUD (Create, Read, Update, Delete) operations on both readers and books. It also allows librarians to assign books to readers and release them when returned.

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

## Project Structure

The project follows the standard Spring MVC structure and contains the following main files:

- `MySpringMvcDispatcherServletInitializer.java` - Initializes the Spring Dispatcher Servlet.
- `SpringConfig.java` - Contains the Spring configuration.
- `BooksController.java` - Controller for handling book-related operations.
- `PeopleController.java` - Controller for handling person-related operations.
- `BookDAO.java` - Data Access Object for the Book entity, handles database interactions.
- `PersonDAO.java` - Data Access Object for the Person entity, handles database interactions.
- `Book.java` - Model class representing the Book entity.
- `Person.java` - Model class representing the Person entity.
- `PersonValidator.java` - Validator for validating Person entities.

The HTML templates for rendering the web pages are stored in the `src/main/webapp/WEB-INF/views` directory, with separate packages for books and people.

## Setup

To set up the project and configure communication with PostgreSQL, follow these steps:

1. Clone the project repository to your local machine.
2. Create a PostgreSQL database and update the database properties in `src/main/resources/database.properties` with the appropriate credentials and database name.
3. Run the application on a server (e.g., Tomcat) to access it in your web browser.

## Dependencies

The project relies on the following dependencies:

- `spring-core`, `spring-context`, `spring-web`, `spring-jdbc`, `spring-webmvc` - Spring Framework libraries for MVC and data access.
- `thymeleaf-spring6` - Thymeleaf library for HTML template rendering.
- `jakarta.servlet-api` - Jakarta Servlet API.
- `hibernate-validator` - Library for validation purposes.
- `postgresql` - PostgreSQL JDBC driver for communication with the database.

Make sure to have these dependencies properly configured in your project's build file (e.g., pom.xml if using Maven).

With this overview and the provided setup instructions, you should be able to clone and configure the project successfully. Enjoy using the Library Management System!