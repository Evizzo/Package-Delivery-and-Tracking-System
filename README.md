
---

# API Endpoints

## Authentication Service
### Base URL: `/auth`

- **POST /register**
  - **Description:** Registers a new user.
  - **Request Body:** `RegisterRequest`.
  - **Response:** `200 OK` with `AuthenticationResponse`.

- **POST /authenticate**
  - **Description:** Authenticates a user with the provided credentials.
  - **Request Body:** `AuthenticationRequest`.
  - **Response:** `200 OK` with `AuthenticationResponse`.

- **POST /logout**
  - **Description:** Logs out the currently authenticated user.
  - **Response:** `200 OK`.

## Company Service
### Base URL: `/company/packet`

- **POST /**
    - **Description:** Creates a new packet.
    - **Request Body:** `PacketDTO`.
    - **Response:** `201 CREATED` with the created `PacketDTO`.

- **PUT /{trackingNumber}/send**
    - **Description:** Sends a packet by its tracking number.
    - **Parameters:** `trackingNumber` (UUID).
    - **Response:** `200 OK`.

- **GET /to-be-sent**
    - **Description:** Retrieves packets that are to be sent.
    - **Response:** `200 OK` with a list of `PacketDTO`.

## Post Office Service
### Base URL: `/post-office/packet`

- **PUT /{trackingNumber}/send**
    - **Description:** Marks a packet as ready for pickup and stores it.
    - **Parameters:** `trackingNumber` (UUID).
    - **Response:** `200 OK`.

- **PUT /{trackingNumber}/pickup**
    - **Description:** Marks a packet as picked up and removes it from storage.
    - **Parameters:** `trackingNumber` (UUID).
    - **Response:** `200 OK`.

## Carrier Service
### Base URL: `/carrier/packet`

- **GET /ready-for-pickup**
    - **Description:** Retrieves packets that are ready for pickup.
    - **Response:** `200 OK` with a list of `PacketDTO`.

- **PUT /{trackingNumber}/status**
    - **Description:** Updates the status of a packet by its tracking number.
    - **Parameters:**
        - `trackingNumber` (UUID): The packet's tracking number.
        - `status` (PacketStatus): The new status.
    - **Response:** `204 NO CONTENT`.

- **PUT /{trackingNumber}/pickup**
    - **Description:** Marks a packet as picked up.
    - **Parameters:** `trackingNumber` (UUID).
    - **Response:** `204 NO CONTENT`.

## Tracking Service
### Base URL: `/tracking/packet`

- **POST /**
    - **Description:** Creates a new packet and sets its status to "TO_BE_SENT".
    - **Request Body:** `PacketDTO`.
    - **Response:** `201 CREATED` with the created `PacketDTO`.

- **GET /all**
    - **Description:** Retrieves all packets, ordered by creation date.
    - **Response:** `200 OK` with a list of `PacketDTO`.

- **PUT /{trackingNumber}/update-status**
    - **Description:** Updates the status of a packet by its tracking number.
    - **Parameters:**
        - `trackingNumber` (UUID).
        - `status` (PacketStatus).
    - **Response:** `200 OK`.

- **GET /{trackingNumber}/track**
    - **Description:** Retrieves packet details by its tracking number.
    - **Parameters:** `trackingNumber` (UUID).
    - **Response:** `200 OK` with an `Optional<PacketDTO>`.

- **PUT /send-packet**
    - **Description:** Updates packet details and marks it as sent.
    - **Request Body:** `PacketDTO`.
    - **Response:** `200 OK`.

---