### Akamai Demo

This project is a simple social network post management system built with Java and Spring Boot.

#### Installation

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE (e.g. Eclipse, IntelliJ IDEA).
3. Build the project using Maven.
4. Run the project using mvn spring-boot:run.

#### Usage

This project provides REST API endpoints for managing social network posts. The available endpoints are:

* GET api/posts: Get all posts
* GET api/posts/{id}: Get post by ID
* GET api/posts/top10: Get Top 10 Viewed Content
* POST api/posts: Create a new post
* PUT api/posts/{id}: Update an existing post by ID
* DELETE api/posts/{id}: Delete an existing post by ID

#### Database

This project uses the H2 in-memory database to store social network posts. The database is automatically created and populated with some dummy data when the application starts.

#### Conclusion

This project provides a simple example of how to build a social network post management system with Java and Spring Boot. It also demonstrates how to use the H2 in-memory database and how to write unit tests with JUnit and Mockito.