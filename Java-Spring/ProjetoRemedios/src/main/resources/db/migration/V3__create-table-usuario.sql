create table usuario(
    id bigint primary key auto_increment,
    login varchar(100) not null,
    senha varchar(100) not null
);