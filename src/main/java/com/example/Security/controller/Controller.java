package com.example.Security.controller;


import com.example.Security.entity.Person;
import com.example.Security.exception.PersonNotFoundExcpetion;
import com.example.Security.repository.PersonRepository;
import com.example.Security.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonService personService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/post")
    public ResponseEntity<?> person(@RequestBody Person person) {
        Person personfind = personRepository.findByUsername(person.getUsername());
        if (personfind != null) {
            throw new PersonNotFoundExcpetion("Personi excist " + personfind.getUsername());
        }
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        System.out.println(encodedPassword);
        person.setPassword(encodedPassword);
        personRepository.save(person);
        return new ResponseEntity<>("done", HttpStatus.OK);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?>personupdate(@PathVariable("id") Long id, @RequestBody Person person){
        return ResponseEntity.ok(personService.personUpdate(id,person));
    }

    @GetMapping("/superadmin")
    public String sadmin() {
        return "superadmin";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}