drop database if exists BM;
create database BM;

use BM;

create table users(
    id int not null auto_increment,
    name varchar(100) not null,
    userName varchar(100) unique not null,
    password text not null,
    budget float not null,
    phone varchar(100) null,
    email varchar(100) unique null,
    createdAt timestamp default current_timestamp,
    updatedAt timestamp default current_timestamp on update current_timestamp,
    primary key(id)
);
create table money (
id int not null auto_increment,
inOrOut varchar(100) not null,
category varchar(100) not null,
amount float not null,
userID int not null,
primary key(id)
)