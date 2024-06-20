create table person (
    name    varchar(100) not null,
    surname varchar(100) not null,
    city    varchar(255) null
);

create table city (
    id   int auto_increment primary key,
    name varchar(255) not null,
    country_code varchar(2) not null
);

create index city_index on city (id, name);