create table topicos(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    curso varchar(100) not null,
    titulo varchar(100) not null,
    mensaje varchar(255) not null,
    fecha_creacion datetime not null,
    status tinyint not null,

    primary key(id),
    constraint fk_topicos_usuario_id foreign key (usuario_id) references usuarios(id)
);
