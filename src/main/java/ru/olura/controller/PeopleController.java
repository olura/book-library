package ru.olura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.olura.dao.PersonDao;
import ru.olura.models.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String getAllPeple(Model model) {
        List<Person> people = personDao.index();
        model.addAttribute("people", people);
        return "people/index";
    }

    @GetMapping ("{id}")
    public String getPerson(@PathVariable("id") long id, Model model) {
        Person person = personDao.findById(id).orElseThrow();
        model.addAttribute("person", person);
        model.addAttribute("books", personDao.getPersonBooks(id));
        return "people/person";
    }

}
