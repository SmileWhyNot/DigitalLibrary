package vlad.kuchuk.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vlad.kuchuk.models.Book;
import vlad.kuchuk.models.Person;

//@Controller
//@RequestMapping("/books")
//public class BooksController {
//    private final BookDAO bookDAO;
//    private final PersonDAO personDAO;
//
//    @Autowired
//    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
//        this.bookDAO = bookDAO;
//        this.personDAO = personDAO;
//    }
//
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("books", bookDAO.index());
//        return "books/index";
//    }
//
//    @GetMapping("/{id}")
//    public String showBook(@PathVariable("id") int id,
//                           Model model,
//                           @ModelAttribute("person") Person person) {
//        model.addAttribute("book", bookDAO.show(id));
//        model.addAttribute("bookReader", bookDAO.getReader(id));
//        model.addAttribute("people", personDAO.index());
//        return "books/showBook";
//    }
//
//    @PatchMapping("/{id}/updateReader")
//    public String updateReader(@PathVariable("id") int id,
//                               @ModelAttribute("person") Person person) {
//        if (bookDAO.getReader(id) != null) {
//            bookDAO.removeBookReader(id);
//        } else if (bookDAO.getReader(id) == null) {
//            bookDAO.setBookReader(id, person);
//        }
//        return "redirect:/books/{id}";
//    }
//
//    @GetMapping("/newBook")
//    public String newBook(@ModelAttribute("book") Book book) {
//        return "books/newBook";
//    }
//
//    @PostMapping
//    public String createBook(@ModelAttribute("book") @Valid Book book,
//                             BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors())
//            return "/books/newBook";
//
//        bookDAO.create(book);
//        return "redirect:/books";
//    }
//
//    @GetMapping("/{id}/editBook")
//    public String editBook(@PathVariable("id") int id,
//                           Model model) {
//        model.addAttribute("book", bookDAO.show(id));
//        return "/books/editBook";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@PathVariable("id") int id,
//                         @ModelAttribute("book") Book book,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "/books/editBook";
//
//        bookDAO.update(id, book);
//        return "redirect:/books";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        bookDAO.delete(id);
//        return "redirect:/books";
//    }
//
//}
