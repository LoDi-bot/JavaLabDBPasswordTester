create table password
(
    id       serial primary key,
    password varchar(20) unique
);