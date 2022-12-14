create sequence hibernate_sequence start 1 increment 1;

create table comments
(
    id            int8         not null,
    comment       varchar(255) not null,
    creation_date timestamp,
    content_id    int8,
    user_id       int8,
    primary key (id)
);

create table content
(
    id               int8          not null,
    content          varchar(2048) not null,
    creation_date    timestamp,
    title            varchar(255)  not null,
    uploaded_file_id int8,
    user_id          int8,
    primary key (id)
);

create table file
(
    id         int8 not null,
    filename   varchar(255),
    content_id int8,
    primary key (id)
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table users
(
    id       int8         not null,
    active   boolean      not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

