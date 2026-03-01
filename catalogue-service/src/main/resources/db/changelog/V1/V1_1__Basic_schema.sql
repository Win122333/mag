create schema if not exists catalogue;

create table catalogue.t_product(
    id serial primary key,
    c_title varchar(50) not null,
    c_details varchar(1000)
)