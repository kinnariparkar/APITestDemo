# APITestDemo
This project is implemented with Java TestNG and RestAssured framework.
This framework uses https://jsonplaceholder.typicode.com/ to test user, post and comments APIs

Table of Content
=================
- [Without Docker](#without-docker)
- [With Docker](#with-docker)
- [Report](#report)

## Without Docker
**Assumption:**
- [Java SE11 is installed](https://openjdk.java.net/projects/jdk/11/)
- [Maven is installed](https://maven.apache.org/install.html)


**Usage:**           
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

## With docker
**Build** docker image using following command:             
```
docker build -t <docker image name> . 
```

**Run** the docker container with below command:                
```
docker run -v <result directory>:/build/Reports <docker image name>
```                
                     
You can check report in your provided result directory

## Report
Logs can be found in the log folder generated in the project directory.                  
Report can be found in the reports folder generated in the project directory.              

- **completeReport** - It will include all test cases results
- **failReport** - It will provide only failed cases report