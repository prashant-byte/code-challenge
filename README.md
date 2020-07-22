# Code Challenge

A program 
which determines if two cities are connected. Two cities are considered
connected if there’s a series of roads that can be traveled from one city
to another

## How to run

Open terminal and make directory containing pom.xml file as your current working directory and run command

```bash
mvn spring-boot:run
```

## Usage
When the application is up and running, open browser and hit url  
```
http://localhost:8080/connected?origin=boston&destination=newark
```

## Technologies
Java 1.8, Spring boot, JUnit and mockito 

## Approach
Used dfs algorithm to find connected components in a graph 

## License
[MIT](https://choosealicense.com/licenses/mit/)
