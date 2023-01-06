drop database if exists eshop;

create database eshop;

use eshop;

drop table if exists customers;
create table customers (
    name varchar(32) not null unique,
    address varchar(128) not null,
    email varchar(128) not null,

    primary key(name)
);

drop table if exists line_item;
create table line_item (
    item_id int auto_increment,
    order_id varchar(8) not null,
    item varchar(32) not null,
    quantity int not null,

    primary key(item_id)
);

drop table if exists customer_order;
create table customer_order (
    order_id varchar(8) not null,
    name varchar(32) not null unique,
    address varchar(128) not null,
    email varchar(128) not null,
    status enum('pending', 'dispatched') default'pending',
    order_date date not null,

    primary key(order_id),
    foreign key(name) references customers(name)
);

drop table if exists order_status;
create table order_status (
    delivery_id varchar(256) not null,
    order_id varchar(8) not null,
    status enum('pending', 'dispatched') default'pending',
    status_update timestamp not null,

    primary key(delivery_id),
    foreign key(order_id) references customer_order(order_id)
);

insert into customers(name, address, email) values
("fred", "201 Cobblestone Lane", "fredflintstone@bedrock.com"),
("sherlock", "221B Baker Street, London", "sherlock@consultingdetective.org"),
("spongebob", "124 Conch Street, Bikini Bottom", "spongebob@yahoo.com"),
("jessica", "698 Candlewood Land", "Cabot Cove: fletcher@gmail.com"),
("dursley", "4 Privet Drive, Little Whinging, Surrey", "dursley@gmail.com");