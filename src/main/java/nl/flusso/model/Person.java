package nl.flusso.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Person {

    @GraphId
    private Long id;

    private String name;

    @RelatedTo(type="follows", direction=Direction.OUTGOING)
    @Fetch
    private Set<Person> following;

    @RelatedTo(type="lastTurn", direction=Direction.OUTGOING)
    @Fetch
    private Turn lastTurn;

    @RelatedTo(type="team", direction=Direction.BOTH)
    @Fetch
    private Team team;

    public Person() {

    }

    public Person(String name) {
        this.setName(name);
    }

    public void follow(Person person) {
        if (getFollowing() == null) {
            setFollowing(new HashSet<Person>());
        }
        getFollowing().add(person);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Person> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Person> following) {
        this.following = following;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    public Turn getTurn() {
        return lastTurn;
    }

    public void setTurn(Turn turn) {
        this.lastTurn = turn;
    }

    public String toString() {
        String results = getName() + "'s follows\n";
        if (getFollowing() != null) {
            for (Person person : getFollowing()) {
                results += "\t- " + person.getName() + "\n";
            }
        }
        return results;
    }
}