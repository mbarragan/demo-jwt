# Read Me
Demo to provide an authentication system based on JWT and working with a HS256 HEX key-secret

* Based on amigoscode[ Spring Boot 3 + Spring Security 6 - JWT Authentication and Authorisation](https://www.youtube.com/watch?v=KxqlJblhzfI)
youtube video.
* Key created on the web[ https://www.grc.com/passwords.htm](https://www.grc.com/passwords.htm)
* Detected an issue with "deprecated" methods on Spring version over 3.0.5
* Added a postman collection.

Demo to provide messaging system based on Rabbit MQ

config gotten from[ amigos code tuto](https://www.youtube.com/watch?v=nFxjaVmFj5E)

to execute, first get the jwt token using next endpoints:
* /api/v1/auth/register
* /api/v1/auth/authenticate (get the token to use as Bearer on next endpoint)
* /api/v1/rabbit/send-message to send a message to queue.A



