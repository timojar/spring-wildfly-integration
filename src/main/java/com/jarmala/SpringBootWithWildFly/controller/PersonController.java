package com.jarmala.SpringBootWithWildFly.controller;

import com.jarmala.SpringBootWithWildFly.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {


    @RequestMapping(value="/hello")
    @ResponseBody
    public List<Person> hello(){

        List<Person> personList=dummyPersons();

        return personList;
    }

    private List<Person> dummyPersons(){

        List <Person> persons = new ArrayList<Person>();
        persons.add(new Person("inwicnwi", "Esko", "Aho", 70));
        persons.add(new Person("eocmeomce", "Matti", "Meikalaine", 100));
        persons.add(new Person("ineic", "Härski", "Mela", 50));

        return persons;

    }

}
