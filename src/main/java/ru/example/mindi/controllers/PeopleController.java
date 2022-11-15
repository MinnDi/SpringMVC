package ru.example.mindi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.example.mindi.dao.PersonDao;
import ru.example.mindi.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String getPeople(Model model){
        model.addAttribute("people", personDao.getPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.getPerson(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PatchMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDao.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDao.getPerson(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDao.update(id, person);
        return "redirect:/people";
    }
}
