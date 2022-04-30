# Lavadero's Management System - Vehicles Washing BACKEND

Lavadero is a web app and the general idea was to create a web app to allow the app's owner to connect vehicle washing establishments 
and its workers, this is like a new business model in Colombia, quite new indeed. The app's owner (admin from now on) was gonna 
receive a small tip or commision for the app's usage. The admin had to configure the platform when new establishments (business owners) 
joined in, this configuration includes everything inside the establishment. The operators (business manager) had to use the app inside 
the establishment, creating new service's orders and pay the workers through a ticket generated for each one. Finally the admin, 
the establishment owner and operators could access historical lists and reports.

It was developed by Luis Espinosa Llanos and for the backend it was used the following technologies and tools: 

<table style="width:100%">
  <tr>
    <td>
  	Core	
    </td>
    <td>
  	Java 1.8, Spring Boot 2 (Spring Framework), Data JPA, Hibernate, Loombok, Jackson Databinding
    </td>
  </tr>
  <tr>
    <td>
  	Server	
    </td>
    <td>
  	Apache Tomcat Embebido (Spring Boot)
    </td>
  </tr>
  <tr>
    <td>
  	Executable	
    </td>
    <td>
  	Jar
    </td>
  </tr>
</table>

It was written using the best practices for instance, a controller, service and repository layer approach, code reusing, dependecy 
injection, design patterns, and more... 

## Where is the FrontEnd?
There is another repository available with the frontend code. You can find it here:
https://github.com/LuisEspinosa7/tienda-frontend

## Videos
Videos exposing the functionality of the proyect in local environment on a Desktop screen.

1. https://youtu.be/cbTc5JXA8AM
2. https://youtu.be/b-_2HQVXgFk
3. https://youtu.be/k7qsPTFOJyk
4. https://youtu.be/tD8ci1G-o1k

## Development Resources
I provide the following resources:

<table style="width:100%">
  <tr>
    <td>
  	Database SQL Backup	
    </td>
    <td>
	In the db folder
    </td>
  </tr>
  <tr>
    <td>
  	Postman Collection	
    </td>
    <td>
	In the postman folder
    </td>
  </tr>
</table>


## Pictures
Some pictures of the project on a local environment respectively:

<table style="width:100%">
  <tr>
    <td>
  		<img width="450" alt="Eclipse" src="https://user-images.githubusercontent.com/56041525/166113191-8271417b-32b3-43d0-b5e1-967e0d6e3e5d.PNG">
	  </td>
    <td>
  	<img width="450" alt="PostMan" src="https://user-images.githubusercontent.com/56041525/166113200-395baa37-cc9d-48f1-ba47-a31014244b10.PNG">
    </td>
  </tr>
</table>

## Installation

This proyect should be installed using the following command:
```bash
mvn clean install
```

## Usage
In the target folder you will find the Jar archive, so please use the following command:

```bash
java -jar NAME_OF_THE_JAR.jar
```

## Contributing
This proyect is quite simple, and is part of my personal portfolio, so it is not intended to receive contributions.


## License
It is free.
