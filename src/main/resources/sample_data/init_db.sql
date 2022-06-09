---
--- drop tables
---

DROP TABLE IF EXISTS tourist_attractions CASCADE;
DROP TABLE IF EXISTS ta_i_seq CASCADE;
DROP TABLE IF EXISTS tourist_attraction_images CASCADE;
DROP TABLE IF EXISTS hotels CASCADE;
DROP TABLE IF EXISTS h_i_seq CASCADE;
DROP TABLE IF EXISTS hotel_images CASCADE;
DROP TABLE IF EXISTS ta_h_seq CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS r_i_seq CASCADE;
DROP TABLE IF EXISTS restaurant_images CASCADE;
DROP TABLE IF EXISTS booked_rooms CASCADE;
DROP TABLE IF EXISTS transport CASCADE;
DROP TABLE IF EXISTS cities CASCADE;
DROP TABLE IF EXISTS seats CASCADE;
DROP TABLE IF EXISTS booked_seats CASCADE;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS attraction_schedule CASCADE;
DROP TYPE IF EXISTS user_status;

---
--- create tables
---

CREATE TABLE tourist_attractions
(
    id                  SERIAL NOT NULL,
    name                VARCHAR,
    description         VARCHAR,
    rating              FLOAT,
    url                 VARCHAR,
    location            VARCHAR
);

CREATE TABLE ta_i_seq
(
    t_attraction_id     SERIAL,
    ta_image_id         SERIAL
);

CREATE TABLE tourist_attraction_images
(
    id                  SERIAL NOT NULL,
    image               VARCHAR
);

CREATE TABLE hotels
(
    id                  SERIAL NOT NULL,
    name                VARCHAR,
    description         VARCHAR,
    rating              FLOAT,
    rooms_number        INT
);

CREATE TABLE h_i_seq
(
    hotel_id            SERIAL,
    hotel_image_id      SERIAL
);

CREATE TABLE hotel_images
(
    id                  SERIAL NOT NULL,
    image               VARCHAR
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

CREATE TABLE r_i_seq
(
    restaurant_id       SERIAL,
    restaurant_image_id SERIAL
);

CREATE TABLE restaurant_images
(
    id                  SERIAL NOT NULL,
    image               VARCHAR
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

CREATE TABLE restaurants
(
    id                  SERIAL NOT NULL,
    name                VARCHAR,
    t_attraction_id     SERIAL,
    rating              FLOAT
);

CREATE TYPE USER_STATUS AS ENUM
(
    'SIGNED_IN',
    'SIGNED_OUT'
);

CREATE TABLE users
(
    id                  SERIAL NOT NULL,
    name                VARCHAR,
    email               VARCHAR,
    password            VARCHAR,
    phone_number        VARCHAR,
    billing_address     VARCHAR,
    user_status         USER_STATUS
);

CREATE TABLE attraction_schedule
(
    id                  SERIAL NOT NULL,
    t_attraction_id     SERIAL,
    monday              VARCHAR,
    tuesday             VARCHAR,
    wednesday           VARCHAR,
    thursday            VARCHAR,
    friday              VARCHAR
);

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

ALTER TABLE cities
    ADD CONSTRAINT pk_city_id PRIMARY KEY(id);

ALTER TABLE transport
    ADD CONSTRAINT pk_transport_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_tourist_attractions_id FOREIGN KEY (t_attraction_id) REFERENCES tourist_attractions(id),
    ADD CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES cities(id);

ALTER TABLE seats
    ADD CONSTRAINT pk_seat_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_transport_id FOREIGN KEY (transport_id) REFERENCES transport(id);

ALTER TABLE booked_seats
    ADD CONSTRAINT pk_booked_seat_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_seat_id FOREIGN KEY (seat_id) REFERENCES seats(id);

ALTER TABLE restaurants
    ADD CONSTRAINT pk_restaurant_id PRIMARY KEY(id),
    ADD CONSTRAINT fk_tourist_attrations_id FOREIGN KEY (t_attraction_id) REFERENCES tourist_attractions(id);

ALTER TABLE users
    ADD CONSTRAINT pk_user_id PRIMARY KEY(id);

ALTER TABLE tourist_attraction_images
    ADD CONSTRAINT pk_ta_images_id PRIMARY KEY(id);

ALTER TABLE hotel_images
    ADD CONSTRAINT pk_hotel_images_id PRIMARY KEY(id);

ALTER TABLE restaurant_images
    ADD CONSTRAINT pk_restaurant_images_id PRIMARY KEY(id);

ALTER TABLE ta_i_seq
    ADD CONSTRAINT fk_ta_id FOREIGN KEY (t_attraction_id) REFERENCES tourist_attractions(id),
    ADD CONSTRAINT fk_ta_i_id FOREIGN KEY (ta_image_id) REFERENCES tourist_attraction_images(id);

ALTER TABLE h_i_seq
    ADD CONSTRAINT fk_h_id FOREIGN KEY (hotel_id) REFERENCES hotels(id),
    ADD CONSTRAINT fk_h_i_id FOREIGN KEY (hotel_image_id) REFERENCES hotel_images(id);

ALTER TABLE r_i_seq
    ADD CONSTRAINT fk_r_id FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    ADD CONSTRAINT fk_r_i_id FOREIGN KEY (restaurant_image_id) REFERENCES restaurant_images(id);

ALTER TABLE attraction_schedule
    ADD CONSTRAINT pk_id PRIMARY KEY (id),
    ADD CONSTRAINT fk_ta_id FOREIGN KEY (t_attraction_id) REFERENCES tourist_attractions(id);
