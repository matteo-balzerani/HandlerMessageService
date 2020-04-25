# Handler Service

the service run on port 8088.
you can see the swagger ui at : http://localhost:8088/swagger-ui.html

in order to use this service via docker-compose (take a look on the main project)
- mvn install
- docker build -t handler .
 
 so, now the image can be used by the compose file.
 
 
 TODO:
 1. configurations for different environments
 2. tests
 3. improve swagger