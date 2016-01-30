package nl.flusso.repository;

import nl.flusso.model.Team;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by joost on 30-1-16.
 */
public interface TeamRepository extends GraphRepository<Team> {
}
