﻿drop schema public cascade;
create schema public;

create sequence user_id_seq;

create table users(
    user_id integer not null default nextval ('user_id_seq'),
    username varchar(40) not null,
    email varchar(40) not null,
    password varchar(40) not null,
    constraint pk_users primary key (user_id));