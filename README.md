# PROXIAD STACK LEARNING

## QUICK START

Start keycloak using 
`docker run -p 8080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.1.1 start-dev`

Create a realm (`myrealm`)

Create a client (which is our app)

Create these permession `CAN_WRITE`, `CAN_READ`, `CAN_DELETE`

Create two user and assign to them different permissions 

To generate an jwt token send a request with this body x-www-form-urlencoded

- grant_type:password
- client_id:myapp-api
- username:<username>
- password<username>
- client_secret:<the-client-secret> (if set Confidential to true)