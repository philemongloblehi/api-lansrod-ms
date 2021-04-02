# api-lansrod-microservice
This README outlines the details of collaborating on this Lansrod API.

## Prerequisites
* [Docker](https://www.docker.com/), [Git](https://git-scm.com/), [Maven](https://maven.apache.org/), [JDK 7 or 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html), [Eclipse or VSCode](), [Postman](https://www.postman.com/downloads/)

## Get And Run This Project
Run these commands one by one into cmd/terminal/shell.
* Clone this repository: `git clone https://github.com/philemongloblehi/api-lansrod-ms.git`
* Get into cloned folder: `cd lansrod`
* Build project: `mvn install` or `mvn clean install` or  `mvn install -DskipTests`
* Run docker compose: `docker-compose up --build -d`
* Visit app at [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html) from browser, explore endpoints using `Postman`.
* For stop all services: `docker-compose down`

## Example Endpoint Description

| SL 	| Endpoint                                    	| Request Type 	| Data Format in Request Body                                                                                                                                                                                                                        	|
|----	|---------------------------------------------	|--------------	|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| 1  	| localhost:8084/api/v1/rest/companies         	| POST         	|  ``` {"socialReason": "Lansrod", "siren": "0000000", "siret": "0000000", "address": "Paris, France"} ```                                              	|
| 2  	| localhost:8084/api/v1/rest/employees         	| POST         	|  ``` {"lastName": "Philemon", "firstName": "Globlehi", "socialSecurityNumber": "0000000", "hiringDate": "2021-04-02", "typeOfContract": "CDI", "salary": 6000} ```                                              	|
| 3  	| localhost:8084/api/v1/rest/companies         	| GET          	| ``` X ```                                                                                                                                                                                                                                          	|

* And some more endpoints are there, explore these endpoints from `controller` classes.

## Version API Description
[Version 1]()
This is the basic version with standard features.
Base URL => localhost:8084/api/v1/rest/

[Version 2]()
This is the basic version with standard features and authentication with Json Web Token (JWT)
Base URL => localhost:8084/api/v2/rest/


## Short Description
The goal of this API is to develop a spring boot application that exposes the following webs:

- creation of a company with the following attributes:
company name, siren, siret, address

- creation of employees of a company with the following attributes:
last name, first name, social security number, date of hire
type of contract, salary

- Updating company information
- Update of employee information
- Search the employees of a company by date of hire, type of contract and / or salary
- Min, max and average salary paid by a company by type of contract




** By Philémon Globléhi...
