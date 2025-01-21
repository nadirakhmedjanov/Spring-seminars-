create table if not exists tasks (
    id bigint not null auto_increment primary key,
    description VARCHAR(255) not null,
    status VARCHAR(255) not null,
    created_at TIMESTAMP not null
);