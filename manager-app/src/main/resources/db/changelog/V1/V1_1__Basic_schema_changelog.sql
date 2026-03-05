create schema if not exists user_management;

-- 1. Таблица пользователей
create table user_management.t_user(
    id serial primary key,
    c_username varchar not null check(length(trim(c_username)) > 0) unique,
    c_password varchar
);

-- 2. Таблица ролей (прав)
create table user_management.t_authority (
    id serial primary key,
    authority varchar not null check(length(trim(authority)) > 0) unique
);

-- 3. Связующая таблица (Many-to-Many)
create table user_management.t_user_authority (
    id serial primary key,
    id_user int not null references user_management.t_user (id),
    id_authority int not null references user_management.t_authority (id),
    constraint uk_user_authority unique (id_user, id_authority)
);