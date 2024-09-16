## Description:

This project was created to explore and experiment with microservices architecture and essential concepts such as service discovery, configuration management, and API security. It consists of three microservices: Authentication, Packet Management, and Tracking.

The Authentication Microservice handles user registration, authentication, and logout, with JWT token issuance.
The Packet Microservice manages the creation, sending, and pickup of packets.
The Tracking Microservice allows tracking of packet statuses and updates their state.

The project also integrates:
A Config Server for centralized configuration management.
A Eureka Server for service discovery, allowing dynamic scaling and fault tolerance.
An API Gateway that handles routing and JWT token validation to ensure secure communication between services.

## Authentication Microservice

### Base URL: `/auth`

1. **Register User**
  - `POST /register`
  - Description: Registers a new user.

2. **Authenticate User**
  - `POST /authenticate`
  - Description: Authenticates a user and returns an authentication token.

3. **Logout**
  - `POST /logout`
  - Description: Logs out the currently authenticated user.

---

## Packet Microservice

### Base URL: `/packet`

1. **Create Packet**
  - `POST /`
  - Description: Creates a new packet.

2. **Send Packet**
  - `PUT /{trackingNumber}/send`
  - Description: Marks a packet as ready for pickup and stores it.

3. **Get Packets to be Sent**
  - `GET /to-be-sent`
  - Description: Retrieves packets pending to be sent.

4. **Pick Up Packet**
  - `PUT /{trackingNumber}/pickup`
  - Description: Marks a packet as picked up and removes it from storage.

5. **Get Packets Ready for Pickup**
  - `GET /ready-for-pickup`
  - Description: Retrieves packets ready for pickup.

---

## Tracking Microservice

### Base URL: `/tracking/packet`

1. **Create Packet**
  - `POST /`
  - Description: Creates a new packet and sets its status to "TO_BE_SENT".

2. **Get All Packets**
  - `GET /all`
  - Description: Retrieves all packets, ordered by creation date.

3. **Update Packet Status**
  - `PUT /{trackingNumber}/update-status`
  - Description: Updates the status of a packet by its tracking number.

4. **Track Packet**
  - `GET /{trackingNumber}/track`
  - Description: Retrieves the details of a packet by its tracking number.

5. **Send Packet**
  - `PUT /send-packet`
  - Description: Updates packet details and marks it as sent.