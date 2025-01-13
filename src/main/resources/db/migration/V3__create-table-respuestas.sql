create table respuestas(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    topico_id bigint not null,
    mensaje varchar(255) not null,
    fecha_creacion datetime not null,
    status tinyint not null,

    primary key(id),
    constraint fk_respuestas_usuario_id foreign key (usuario_id) references usuarios(id),
    constraint fk_respuestas_topico_id foreign key (topico_id) references topicos(id)
);
