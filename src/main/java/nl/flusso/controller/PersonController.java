package nl.flusso.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiParam;
import nl.flusso.model.Person;
import nl.flusso.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/neo4j")
public class PersonController {

    @Autowired
    Neo4jService neo4jService;

    @RequestMapping(
            value = {""},
            produces = {"application/json", "text/plain; charset=utf-8"},
            method = {RequestMethod.GET}
    )
    public
    @ResponseBody
    ResponseEntity<List<Person>> getPersons() {
        Iterable<Person> personsIterable = neo4jService.findAll();
        List<Person> persons = Lists.newArrayList(personsIterable);
        return ResponseEntity.ok().body(persons);
    }

    @RequestMapping(
            value = {""},
            produces = {"application/json", "text/plain; charset=utf-8"},
            method = {RequestMethod.POST}
    )
    public
    @ResponseBody
    ResponseEntity<Person> newPerson(@ApiParam("Person to add") @RequestBody Person newPerson) {
        Person persistedPerson = neo4jService.save(newPerson);
        return ResponseEntity.ok().body(persistedPerson);
    }


    @RequestMapping(
            value = {"/{id}/follow/{personToFollowId}"},
            produces = {"application/json", "text/plain; charset=utf-8"},
            consumes = {"application/json"},
            method = {RequestMethod.PUT}
    )
    public
    @ResponseBody
    ResponseEntity<Person> newPerson(@PathVariable("id") String id, @PathVariable("personToFollowId") String personToFollowId) {
        Person persistedPerson = neo4jService.follow(id, personToFollowId);
        return ResponseEntity.ok().body(persistedPerson);
    }

    @RequestMapping(
            value = {"/{id}/team/{teamId}"},
            produces = {"application/json", "text/plain; charset=utf-8"},
            consumes = {"application/json"},
            method = {RequestMethod.PUT}
    )
    public
    @ResponseBody
    ResponseEntity<Person> setTeam(@PathVariable("id") String id, @PathVariable("teamId") String teamId) {
        Person persistedPerson = neo4jService.setTeam(id, teamId);
        return ResponseEntity.ok().body(persistedPerson);
    }

    @RequestMapping(
            value = {"/{id}/turn/{turnId}"},
            produces = {"application/json", "text/plain; charset=utf-8"},
            consumes = {"application/json"},
            method = {RequestMethod.PUT}
    )
    public
    @ResponseBody
    ResponseEntity<Person> newTurn(@PathVariable("id") String id, @PathVariable("turnId") String turnId) {
        Person persistedPerson = neo4jService.turn(id, turnId);
        return ResponseEntity.ok().body(persistedPerson);
    }
}