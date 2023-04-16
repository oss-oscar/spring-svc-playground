create table if not exists pokemon(
    id serial primary key,
    pokedex integer not null,
    name varchar(50) not null
);