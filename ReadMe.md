# energy-crud-app

Recrutiment task, REST Api in Java and Spring boot.

## Requirements

For building and running the application you need:

- [JDK 21](https://www.oracle.com/pl/java/technologies/downloads/)
- [Maven 3](https://maven.apache.org)

## Running the application locally


```shell
mvn spring-boot:run
```

## Running in docker container


```shell
docker compose up -d --build
```

## Initial data
| Device ID | Creation Date        | Modification Date    | Startup Date         | Shutdown Date        | Name     | Street    | Building Number | Apartment Number | City     | Postal Code | Country   |
| --------- | -------------------- | -------------------- | -------------------- | -------------------- | -------- | --------- | --------------- | ---------------- | -------- | ----------- | --------- |
| TestDevice| 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | Test Name| Test Street| 123             | 456              | Test City| 12345       | Test Country |
| device2   | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | Name2    | Street2    | 2               | NULL             | City2    | 23456       | Country2   |
| device3   | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | 2024-10-10T10:00:00 | Name3    | Street3    | 3               | NULL             | City3    | 34567       | Country3   |


## H2 Database url
http://localhost:8080/h2-console

## Code coverage

> score>83%
![image](https://github.com/JacekKaczmarek10/recruitment-task/assets/58121352/6e2a7b6b-b10c-499e-a8a3-603d58149c33)


## Copyright

No copyright allowed. 



