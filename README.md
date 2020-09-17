# Overview
myRetail is a RESTful service that can retrieve product and price details by ID. 
It also allows to update and persist product information. 

# Assumptions on the problem statement
As could be seen from the problem statement description provided in problem_statement.txt, where to persist the updated pricing data for products is unclear. 
While the problem states that pricing info is available through an external API (mocked for the purpose of this exercise), 
it also at the same time asks to persist the data into a "datastore" which wouldn't ideally be directly exposed if indeed the pricing data is hosted elsewhere and exposed only through an API.
While it is entirely possible to mock another API endpoint that accepts such updates and persists them, much the same way as other endpoints have been mocked,
because the problem statement specifically mentions a datastore, the implementation uses an embedded in-memory databstore as a local cache just to demonstrate the capability of the API.   
  
# How the tech stacks up
1. Java SE 14
2. Spring Boot 2.3.3.RELEASE
3. Apache Camel 3.5.0
4. Embedded MongoDB
5. JUnit 5
6. Mockito
7. Swagger UI
8. Lombok

# Design
This application is built on Java SE 14 with Spring Boot and Apache Camel (both its REST and Java DSLs).
Apache Camel allows us to easily bake well-known EIPs into the design to address integration concerns.
Domain concerns for both Product and Pricing have been subjected to careful thought.

# Modules
1. myretail - contains everything required for the main myRetail API
2. external - mocked up external APIs backed by in-memory hosted data
3. common - common data models and web configurations for use in both the myretail and external modules

# Running myRetail
1. Run application via gradle task `run`
3. Use the Swagger UI to access endpoints or external tools like Postman

# API documentation
Swagger has been used for API documentation.  Endpoints can be accessed through the Swagger UI by loading the page at
`localhost:8080/swagger-ui`

# Unit testing reports
Coverage reports from running unit tests are available under the `reports` folder of this repo.

# Areas of improvement
1. Logging
2. Better error handling and coverage of boundary conditions
3. Integration testing for the Camel routes
4. More unit test coverage; not just more but also meaningful coverage
5. Resiliency can be improved by introducing patterns like circuit breaker and with use of libraries like Hystrix
6. Code comments / self-documenting naming of components
