# ad6-follow-poc
proof of concept for having followers with the AD6 using neo4j

## Stack
* Spring
  * Spring Boot 1.4.0
  * Spring Data
  * Spring MVC
  * Spring Neo4J
* Swagger 2
* Neo4j
* Gradle

## Requirements
* Java 7+
* Neo4J instantie
  * localhost on default port (http://localhost:7474/)
  * username: neo4j
  * password: neo4j2

## How to run
* Make sure the Neo4J instance is running
  * if under linux, and docker is installed, you can run neo4j-docker.sh
    * this will start a neo4j instance at http://localhost:7474, can be configured @http://localhost:7474/browser/
* Gradlew bootRun
  * Will execute the Spring Boot tomcat @ port 18082

## How to add data
* The applicatione contains some Rest services, which can be used by any client (/neo4j, /team, /turn)
* Or you can use the Swagger2 UI, @http://localhost:18082/swagger-ui.html#/
