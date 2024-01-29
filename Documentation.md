# Transportation Management System (*TMS*)

## Introduction

TMS is a project that aims to manage:

- public transportation company ticketing
- card verification
- fleet management
- route tracking

TMS is a Java-based application that includes Data Access Object (DAO) classes, Java Persistence API (JPA), PostgreSQL, Hibernate.
## Features
### Ticketing System
This system allows tickets to be issued through automatic vending machines and authorized vendors.
Weekly and monthly branded cards can be issued to eligible cardholders.
The cards have an annual validity period and must be renewed at the end of the period.
Each ticket and subscription is marked with a unique code.
The system keeps track of the total number of tickets and subscriptions issued during the specified period and when they were issued.
Automated vending machines may or may not work.
### Card validation
The system can instantly authenticate the subscription based on the user’s card number.
### Vehicles management
The system makes it easier to manage the company’s transports, which include trams and buses of varying capacities.
Vehicles can be in service or undergoing maintenance, and service and maintenance times are recorded.
When a ticket is accepted on a vehicle, it is marked as used, and the system can provide statistics on how many tickets have been accepted on a particular vehicle or on all vehicles in a given period of time
### Route Management
Each vehicle in service can be routed in a specific direction, characterized by a departure point, a parking space, and an estimated travel time.
The system records the frequency of the vehicle and the actual time spent traveling in each direction.
### Other
The system is implemented in Java and relies on the Java Persistence API (JPA) for data persistence. A PostgreSQL database is used to store and manage application data.
## Credits
Alessia Palmieri: [alessiapalmieri00@outlook.it](mailto:alessiapalmieri00@outlook.it)<br>
Antonio Bertuccio: [anto.bertu@gmail.com](mailto:anto.bertu@gmail.com)<br>
Daniel Rrapi: [uzo1209@gmail.com](mailto:uzo1209@gmail.com)
