package nl.flusso.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiParam;
import nl.flusso.model.Turn;
import nl.flusso.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by joost on 30-1-16.
 */
@RestController
@RequestMapping("/turn")
public class TurnController {

    @Autowired
    TurnRepository turnRepository;

    @RequestMapping(
            value = {""},
            produces = {"application/json", "text/plain; charset=utf-8"},
            method = {RequestMethod.GET}
    )
    public
    @ResponseBody
    ResponseEntity<List<Turn>> getTurns() {
        Iterable<Turn> turnIterable = turnRepository.findAll();
        List<Turn> turns = Lists.newArrayList(turnIterable);
        return ResponseEntity.ok().body(turns);
    }

    @RequestMapping(
            value = {""},
            produces = {"application/json", "text/plain; charset=utf-8"},
            method = {RequestMethod.POST}
    )
    public
    @ResponseBody
    ResponseEntity<Turn> newTurn(@ApiParam("Turn to add") @RequestBody Turn newTurn) {
        Turn persistedTurn = turnRepository.save(newTurn);
        return ResponseEntity.ok().body(persistedTurn);
    }
}
