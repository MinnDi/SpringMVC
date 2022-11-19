package ru.example.mindi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.example.mindi.dao.PersonDao;
import ru.example.mindi.model.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static PersonDao personDao;

    @Autowired
    public AdminController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person){
        model.addAttribute("people", personDao.getPeople());
        return "admin/admin";
    }

    @PatchMapping("/add")
    public String addAdmin(@ModelAttribute("person")Person person){
        System.out.println(personDao.getPerson(person.getId()).getName()+" is admin now!");
        return "redirect:/people";
    }
}
