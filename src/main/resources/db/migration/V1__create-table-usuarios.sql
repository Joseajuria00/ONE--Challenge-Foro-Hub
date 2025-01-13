create table usuarios(
    id bigint not null auto_increment,
    name varchar(100) not null,
    login varchar(100) not null unique,
    password varchar(300) not null,
    status tinyint not null,

    primary key(id)
);