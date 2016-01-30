package nl.flusso.service;

import nl.flusso.model.Person;
import nl.flusso.model.Team;
import nl.flusso.model.Turn;
import nl.flusso.repository.PersonRepository;
import nl.flusso.repository.TeamRepository;
import nl.flusso.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joost on 30-1-16.
 */
@Service
public class Neo4jServiceImpl implements Neo4jService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TurnRepository turnRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Iterable<Person> findAll() {
        Iterable<Person> allPersons = personRepository.findAll();
        return allPersons;
    }

    @Override
    @Transactional
    public Person save(Person newPerson) {
        Person persistedPerson = personRepository.save(newPerson);
        return  persistedPerson;
    }

    @Override
    public Person follow(String id, String personToFollowId) {
        Person personToFollow = personRepository.findOne(Long.parseLong(personToFollowId));
        Person personToEdit = personRepository.findOne(Long.parseLong(id));
        personToEdit.follow(personToFollow);
        return save(personToEdit);
    }

    @Override
    public Person setTeam(String id, String teamId) {
        Team teamToAdd = teamRepository.findOne(Long.parseLong(teamId));
        Person personToEdit = personRepository.findOne(Long.parseLong(id));
        personToEdit.setTeam(teamToAdd);
        return personRepository.save(personToEdit);
    }

    @Override
    public Person turn(String id, String turnId) {
        Turn turnToAdd = turnRepository.findOne(Long.parseLong(turnId));
        Person personToEdit = personRepository.findOne(Long.parseLong(id));
        personToEdit.setTurn(turnToAdd);
        return personRepository.save(personToEdit);
    }

}
