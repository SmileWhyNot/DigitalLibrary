package vlad.kuchuk.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vlad.kuchuk.models.Book;
import vlad.kuchuk.models.Person;
import vlad.kuchuk.services.BooksService;
import vlad.kuchuk.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear) {

        if(page == null || booksPerPage == null) {
            model.addAttribute("books", booksService.findAll(sortByYear));
        } else {
            model.addAttribute("books", booksService.findAllPageable(page, booksPerPage, sortByYear));
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id,
                           Model model,
                           @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("bookReader", booksService.findReader(id));
        model.addAttribute("people", peopleService.findAll());
        return "books/showBook";
    }

    @PatchMapping("/{id}/updateReader")
    public String updateReader(@PathVariable("id") int id,
                               @ModelAttribute("person") Person person) {
        if (booksService.findReader(id) != null) {
            booksService.removeBookReader(id);
        } else if (booksService.findReader(id) == null) {
            booksService.setBookReader(id, person);
        }
        return "redirect:/books/{id}";
    }

    @GetMapping("/newBook")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "/books/newBook";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/editBook")
    public String editBook(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("book", booksService.findOne(id));
        return "/books/editBook";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/books/editBook";

        booksService.update(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

}
