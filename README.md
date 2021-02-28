# APITestDemo
This project is implemented with Java TestNG and RestAssured framework.
This framework uses https://jsonplaceholder.typicode.com/ to test user, post and comments APIs

Table of Content
=================
- [Installation](#installation)
- [Usage](#usage)

## Installation

### Assumption:
- [Java SE11 is installed](https://openjdk.java.net/projects/jdk/11/)
- [Maven is installed](https://maven.apache.org/install.html)




## Usage
If you simply want to compile your test sources (but not execute the tests), you can execute the following:          
```
 mvn test-compile
```                       

Test framework can be run using following command:             
```
mvn test
```

JAR file can be create by executing the following command:            
```
mvn package
```

##Report
Logs can be found in the log folder generated in the project directory.                  
Report can be found in the reports folder generated in the project directory.              

- **completeReport** - It will include all test cases results
- **failReport** - It will provide only failed cases report