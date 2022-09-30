create sequence hibernate_sequence start 2 increment 1;

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

alter table if exists comments
    add constraint comments_content_fk
        foreign key (content_id) references content;

alter table if exists comments
    add constraint comments_user_fk
        foreign key (user_id) references users;

alter table if exists content
    add constraint content_file_fk
        foreign key (uploaded_file_id) references file;

alter table if exists content
    add constraint content_user_fk
        foreign key (user_id) references users;

alter table if exists file
    add constraint file_content_fk
        foreign key (content_id) references content;

alter table if exists user_role
    add constraint user_role_user_fk
        foreign key (user_id) references users;