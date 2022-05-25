---
--- drop tables
---

DROP TABLE IF EXISTS tourist_attractions CASCADE;
DROP TABLE IF EXISTS hotels CASCADE;
DROP TABLE IF EXISTS ta_h_seq CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS booked_rooms CASCADE;
DROP TABLE IF EXISTS transport CASCADE;
DROP TABLE IF EXISTS cities CASCADE;
DROP TABLE IF EXISTS seats CASCADE;
DROP TABLE IF EXISTS booked_seats CASCADE;

---
--- create tables
---

CREATE TABLE tourist_attractions
(
    id              SERIAL NOT NULL,
    name            VARCHAR,
    image           VARCHAR,
    description     VARCHAR,
    rating          FLOAT
);

CREATE TABLE hotels
(
    id                  SERIAL NOT NULL,
    name                VARCHAR,
    image               VARCHAR,
    description         VARCHAR,
    rating              FLOAT,
    rooms_number        INT
);

CREATE TABLE ta_h_seq
(
    id                  SERIAL NOT NULL,
    hotel_id            SERIAL,
    t_attraction_id     SERIAL
);

CREATE TABLE rooms
(
    id                  SERIAL NOT NULL,
    hotel_id            SERIAL,
    bedrooms            INT,
    floor               INT,
    price               FLOAT
);

CREATE TABLE booked_rooms
(
    id                  SERIAL NOT NULL,
    start_date          TIMESTAMP,
    end_date            TIMESTAMP,
    room_id             SERIAL
);

CREATE TABLE transport
(
    id                  SERIAL NOT NULL,
    type                VARCHAR,
    t_attraction_id     SERIAL,
    city_id             SERIAL
);

CREATE TABLE cities
(
    id                  SERIAL NOT NULL,
    name                VARCHAR
);

CREATE TABLE seats
(
    id                  SERIAL NOT NULL,
    transport_id        SERIAL
);

CREATE TABLE booked_seats
(
    id                  SERIAL NOT NULL,
    seat_id             SERIAL,
    start_date          TIMESTAMP,
    end_date            TIMESTAMP
);


---
--- insert data
---

INSERT INTO tourist_attractions VALUES( , , , , );

INSERT INTO hotels VALUES( , , , , , );

INSERT INTO ta_h_seq VALUES( , , );

INSERT INTO rooms VALUES( , , , , );

INSERT INTO booked_rooms VALUES( , , , );

INSERT INTO transport VALUES( , , , );

INSERT INTO seats VALUES( , );

INSERT INTO rooms VALUES( , , , , );

INSERT INTO booked_seats VALUES( , , , );

---
--- add constraints
---

ALTER TABLE ONLY tourist_attractions
    ADD CONSTRAINT pk_tourist_attractions_id PRIMARY KEY(id);

ALTER TABLE ONLY hotels
    ADD CONSTRAINT pk_hotel_id PRIMARY KEY(id);

ALTER TABLE ta_h_seq
    ADD CONSTRAINT fk_tourist_attractions_id FOREIGN KEY (t_attraction_id) REFERENCES tourist_attractions(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY(hotel_id) REFERENCES hotels(id) ON DELETE CASCADE;

ALTER TABLE rooms
    ADD CONSTRAINT pk_room_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY(hotel_id) REFERENCES hotels(id);

ALTER TABLE booked_rooms
    ADD CONSTRAINT pk_booked_room_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_room_id FOREIGN KEY(room_id) REFERENCES rooms(id);

ALTER TABLE transport
    ADD CONSTRAINT pk_transport_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_tourist_attractions_id FOREIGN KEY (t_attraction_id) REFERENCES tourist_attractions(id),
    ADD CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES cities(id);

ALTER TABLE cities
    ADD CONSTRAINT pk_city_id PRIMARY KEY(id);

ALTER TABLE seats
    ADD CONSTRAINT pk_seat_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_transport_id FOREIGN KEY (transport_id) REFERENCES transport(id);

ALTER TABLE booked_seats
    ADD CONSTRAINT pk_booked_seat_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_seat_id FOREIGN KEY (seat_id) REFERENCES seats(id);
