package vlad.kuchuk.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vlad.kuchuk.dao.BookDAO;
import vlad.kuchuk.models.Book;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("bookReader", bookDAO.getReader(id));

        return "books/showBook";
    }

    @PatchMapping("/{id}/updateReader")
    public String updateReader(@PathVariable("id") int id,
                               @ModelAttribute("book") Book book) {
        if(book.getPersonId() != null) {
            bookDAO.removeBookReader(id);
        }
        return "redirect:/books";
    }

    @GetMapping("/newBook")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        book.setPersonId(null);

        if (bindingResult.hasErrors())
            return "/books/newBook";

        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/editBook")
    public String editBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "/books/editBook";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/books/editBook";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

}
