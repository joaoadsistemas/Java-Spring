create table person
(
    id integer not null,
    name varchar(255) not null,
    location varchar(255),
    birth_date timestamp,
    primary key (id)
);

INSERT INTO person(id, name, location, birth_date)
VALUES (1001, 'Robson', 'Sorocaba', current_date);

INSERT INTO person(id, name, location, birth_date)
VALUES (1002, 'Marta', 'Cerquilho', current_date);

INSERT INTO person(id, name, location, birth_date)
VALUES (1003, 'Luana', 'Tatui', current_date);

INSERT INTO person(id, name, location, birth_date)
VALUES (1004, 'Vieira', 'Tiete', current_date);