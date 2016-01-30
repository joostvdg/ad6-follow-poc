package nl.flusso.service;

import nl.flusso.model.Person;

/**
 * Created by joost on 30-1-16.
 */
public interface Neo4jService {

    Iterable<Person> findAll();

    Person save(Person newPerson);

    Person follow(String id, String personToFollowId);

    Person setTeam(String id, String teamId);

    Person turn(String id, String turnId);
}

