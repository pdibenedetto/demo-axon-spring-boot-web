This project is an updated implementation of the [Implementing Event Sourcing with Axon and Spring Boot](http://progressivecoder.com/implementing-event-sourcing-using-axon-and-spring-boot-part-1/) blog series.
The original code can be found on [github](https://github.com/dashsaurabh/event-sourcing-axon-spring-boot).

This demo is dependent on [docker-compose-axon-se](the https://github.com/ronlievens/docker-compose-axon-se).

# Changelog
- Upgraded to last spring boot version
- Added Spring Actuator with [Prometheus](https://prometheus.io/) support
- Added Undertow metrics to Spring Actuator
- Removed JPA for other client demo
- Updated from Swagger v2 to openapi (Swagger v3)
- Added Spring boot layered docker image
- Upgrade to Java 11 and run with GraalVM
- Build docker image via Spring Boot
- Make compatible with demo-axon-spring-boot-webflux

# Wishlist
- Add GraalVM Native image
- Add JWT login

# Dashboards
- Open [OpenAPI Definition](http://127.0.0.1:8081/v3/swagger-ui.html)
- Open [Spring Actuator](http://127.0.0.1:8081/actuator)
- Open [Spring Actuator Prometheus scraper page](http://127.0.0.1:8081/actuator/prometheus)
