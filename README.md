# PROXIAD STACK LEARNING
## Overview

This project demonstrates the use of Keycloak to authenticate requests to a Spring Boot backend in a Dockerized environment.
## Quick Start

    Clone the repository:
```
git clone https://github.com/samehajala/proxiad-stack-learning.git
```
Copy the environment file:
```
cp .env.example .env
```
Start the services:
```
docker compose up
```
Open `http://localhost:8080` and log in with:

- Username: `admin`
- Password: `admin`

Create a realm named `myrealm`.

Create a client named `myapp`.

Create two users:

    user → Assign `CAN_READ` role
    admin → Assign `CAN_WRITE` role

Generate an access token by making a POST request to:

`http://localhost:8080/realms/myrealm/protocol/openid-connect/token`

Request body (x-www-form-urlencoded):

```
grant_type=password
client_id=myapp
username=user
password=user
client_secret=<client-secret>
```
Use the obtained Bearer token to authenticate requests to the Spring Boot API.
