---
--- drop tables
---

DROP TABLE IF EXISTS tourist_attractions CASCADE;
DROP TABLE IF EXISTS hotels CASCADE;
DROP TABLE IF EXISTS ta_h_seq CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS booked_rooms CASCADE;

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
    id              SERIAL NOT NULL,
    name            VARCHAR,
    image           VARCHAR,
    description     VARCHAR,
    rating          FLOAT,
    rooms_number    INT
);

CREATE TABLE ta_h_seq
(
    id                  SERIAL NOT NULL,
    hotel_id            SERIAL,
    t_attraction_id     SERIAL
);


CREATE TABLE rooms
(
    id              SERIAL NOT NULL,
    hotel_id        SERIAL,
    bedrooms        INT,
    floor           INT,
    price           FLOAT
);

CREATE TABLE hotels
(
    id              SERIAL NOT NULL,
    name            VARCHAR,
    image           VARCHAR,
    description     VARCHAR,
    rating          FLOAT,
    rooms_number    INT
);

CREATE TABLE booked_rooms
(
    id              SERIAL NOT NULL,
    start_date      TIMESTAMP,
    end_date        TIMESTAMP,
    room_id         SERIAL
);


---
--- insert data
---

INSERT INTO tourist_attractions VALUES( , , , , );

INSERT INTO hotels VALUES( , , , , , );

INSERT INTO ta_h_seq VALUES( , , );

INSERT INTO rooms VALUES( , , , , );

INSERT INTO booked_rooms VALUES( , , , );

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
    ADD CONSTRAINT pk_booked_rooms_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_room_id FOREIGN KEY(room_id) REFERENCES rooms(id);