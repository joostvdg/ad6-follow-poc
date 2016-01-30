package nl.flusso;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class Ad6FollowPocApplication {

    public static final String NEO4J_URL = System.getenv("NEO4J_URL") != null ? System.getenv("NEO4J_URL") : "http://localhost:7474/db/data/";


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Ad6FollowPocApplication.class, args);
    }

    @Configuration
    @EnableNeo4jRepositories(basePackages = "nl.flusso.repository")
    static class ApplicationConfig extends Neo4jConfiguration {

        public ApplicationConfig() {
            setBasePackage("nl.flusso.model");
        }

        @Bean
        public GraphDatabaseService graphDatabaseService() {
            return new SpringCypherRestGraphDatabase(NEO4J_URL, "neo4j", "neo4j2");
        }
    }

}
