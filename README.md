# Route Manager
 
## Running it locally from Intellij
When you run the Application from Intellij use the 'dev' profile

## Clean build whole project from command line
```
mvnw clean compile
```

## Clean build and run whole project from command line
```
mvnw clean spring-boot:run
```

## Check it is running from swagger
```
http://localhost:8080/swagger-ui/index.html
```

## Check from browser
```
http://localhost:8080/routing/CZE/ITA
```

## Project Structure

### Testing
The Route Manager project is a standard Spring boot web project with JUnit 5 configured. 

## Running unit tests from command line
```
mvnw clean test
```

