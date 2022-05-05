CREATE TABLE cargo
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255),
    weight   INT,
    cargo_id BIGINT,
    CONSTRAINT pk_cargo PRIMARY KEY (id)
);

CREATE TABLE coordinates
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    x  INT,
    y  INT,
    CONSTRAINT pk_coordinates PRIMARY KEY (id)
);

CREATE TABLE unit
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    coordinates_id       BIGINT,
    fuel                 INT,
    max_fuel             INT,
    max_cargo_weight     INT,
    current_cargo_weight INT,
    CONSTRAINT pk_unit PRIMARY KEY (id)
);

ALTER TABLE cargo
    ADD CONSTRAINT FK_CARGO_ON_CARGO FOREIGN KEY (cargo_id) REFERENCES unit (id);

ALTER TABLE unit
    ADD CONSTRAINT FK_UNIT_ON_COORDINATES FOREIGN KEY (coordinates_id) REFERENCES coordinates (id);