package org.chirkov.springAppMvc.controllers;

import jakarta.validation.Valid;
import lombok.Getter;
import org.chirkov.springAppMvc.DataAccessObject.PersonDAO;
import org.chirkov.springAppMvc.models.Person;
import org.chirkov.springAppMvc.services.ItemService;
import org.chirkov.springAppMvc.services.PeopleService;
import org.chirkov.springAppMvc.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
@Getter
public class PeopleController {
    private final PersonDAO personDAO;
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    private final ItemService itemService;

//    public PeopleController() {
//    }

    @Autowired
    public PeopleController(PersonDAO personDAO, PeopleService peopleService, PersonValidator personValidator, ItemService itemService) {
        this.personDAO = personDAO;
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.itemService = itemService;
    }

    @GetMapping
    public String index(Model model) { //получим всех людей из DAO
        model.addAttribute("people", peopleService.findAll());
//        itemService.findItemsByItemName("Iphone");

//        itemService.findItemsByOwner(peopleService.findAll().get(0));
//        peopleService.test();
        personDAO.testNPlus1();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) { //получаем id из GET запроса и
        // передаем его в сигнатуру метода
        //в теле метода получим 1 человека по его ИД из ДАО и дадим его в представление
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
//        personDAO.save(person);
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
//        personDAO.update(id, person);
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
//        personDAO.delete(id);
        peopleService.delete(id);
        return "redirect:/people";
    }

}
