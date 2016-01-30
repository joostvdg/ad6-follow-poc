package nl.flusso.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiParam;
import nl.flusso.model.Team;
import nl.flusso.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by joost on 30-1-16.
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping(
            value = {""},
            produces = {"application/json", "text/plain; charset=utf-8"},
            method = {RequestMethod.GET}
    )
    public
    @ResponseBody
    ResponseEntity<List<Team>> getTeams() {
        Iterable<Team> turnsIterable = teamRepository.findAll();
        List<Team> teams = Lists.newArrayList(turnsIterable);
        return ResponseEntity.ok().body(teams);
    }

    @RequestMapping(
            value = {""},
            produces = {"application/json", "text/plain; charset=utf-8"},
            method = {RequestMethod.POST}
    )
    public
    @ResponseBody
    ResponseEntity<Team> newTeam(@ApiParam("Team to add") @RequestBody Team newTeam) {
        Team persistedTeam = teamRepository.save(newTeam);
        return ResponseEntity.ok().body(persistedTeam);
    }
}
