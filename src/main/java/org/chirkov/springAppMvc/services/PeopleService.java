package org.chirkov.springAppMvc.services;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.chirkov.springAppMvc.models.Mood;
import org.chirkov.springAppMvc.models.Person;
import org.chirkov.springAppMvc.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Getter
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);

    }

//    @Transactional
    public void save(Person person) {
        person.setCreatedAt(new Date());
        person.setMood(Mood.HAPPY);
        peopleRepository.save(person);
    }

//    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

//    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    public void test() {
        System.out.println("Testing here with debug. Inside Hibernate Transaction");
    }
}
