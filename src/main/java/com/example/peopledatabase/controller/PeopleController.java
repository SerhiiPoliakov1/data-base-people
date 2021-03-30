package com.example.peopledatabase.controller;


import com.example.peopledatabase.model.Person;
import com.example.peopledatabase.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    PeopleRepository peopleRepository;

    @GetMapping("/people")
    public List<Person> getAllPeople() {
        List<Person> allPerson = new ArrayList<>();
        peopleRepository.findAll().forEach(allPerson::add);

        return allPerson;
    }

    @GetMapping("/people/{id}")
    public Person getPersonByID(@PathVariable("id") long id){
        Optional<Person> personData = peopleRepository.findById(id);
       return personData.get();

    }

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person person){
        Person _person = peopleRepository.save(new Person(person.getName(),person.getRole(),person.isDisable()));
        return _person;
    }

    @PutMapping("/people/{id}")
    public Person updatePerson(@PathVariable("id") long id, @RequestBody Person person){
        Optional<Person>  persondData = peopleRepository.findById(id);
        Person _person = persondData.get();
        _person.setDisable(person.isDisable());
        _person.setName(person.getName());
        _person.setRole(person.getRole());
        peopleRepository.save(_person);
        return _person;
    }
    @DeleteMapping("/people/{id}")
    public Person deletePerson(@PathVariable("id") long id){
        Optional<Person> person = peopleRepository.findById(id);
        peopleRepository.deleteById(id);
        return person.get();
    }

    @DeleteMapping("/people")
    public void deleteAllPersons(){
        peopleRepository.deleteAll();
    }

    @GetMapping("/people/disabled")
    public List<Person> findByDisabled(){
        List<Person> disabledPeople =  peopleRepository.findByDisable(true);
        return  disabledPeople;
    }

}