package com.example.demo.controllers;

import com.example.demo.entities.PersonEntity;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
public class PersonController {

    @Resource
    private PersonService personService;

    @GetMapping({"/index", "/"})
    public String list(Model model){

        List<PersonEntity> personList = personService.getPersonList();
        model.addAttribute("index", personList);
        return "index";
    }

    @GetMapping("/add")
    public String toAddPage(){
        return "add";
    }

    @PostMapping("/add")
    public String addPerson(PersonEntity person){

       log.info("---{}---", person.toString());
        personService.savePerson(person);
        return "redirect:/";
    }

    //修改页面
    @GetMapping("/edit/{id}")
    public String toEditPage(@PathVariable("id") Long id, Model model){

        PersonEntity person = personService.findPersonById(id);
        model.addAttribute("person", person);
        return "/add";
    }

    //修改
    @PutMapping("/add")
    public String updatePerson(PersonEntity person){

        log.info("---{}---", person.toString());
        personService.savePerson(person);
        return "redirect:/";
    }
}
