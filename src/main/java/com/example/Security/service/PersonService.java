package com.example.Security.service;

import com.example.Security.entity.Person;
import com.example.Security.exception.PersonNotFoundExcpetion;
import com.example.Security.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {


    @Autowired
    PersonRepository personRepository;


    public Person personUpdate(Long id,Person personNew) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            Person personexcist=personRepository.findByUsername(personNew.getUsername());
            if (personexcist !=null){
                throw new PersonNotFoundExcpetion("ky emer exixt "+personexcist.getUsername());
            }
            person.setUsername(personNew.getUsername());
            return personRepository.save(person);
        } else {
            throw new PersonNotFoundExcpetion("Person not found with id " + id);
        }
    }

}
