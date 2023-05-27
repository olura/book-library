create table Person (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL,
    birth_date int NOT NULL

);

create table Book (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL,
    author varchar NOT NULL,
    year int NOT NULL,
    person_id int references person(id) ON DELETE SET NULL
);