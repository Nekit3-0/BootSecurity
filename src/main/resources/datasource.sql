create table if not exists user (id bigint not null primary key auto_increment,
                                 name varchar(255) not null, surname varchar(255) not null, age varchar(255) not null,
    login varchar(255) not null, password varchar(255) not null);

create table if not exists role (id int not null primary key auto_increment, role varchar(255) not null);

create table if not exists user_roles (user_id bigint, foreign key (user_id) references user(id),
    role_id int, foreign key (user_id) references user(id));

insert into user (name, surname, age,  login, password)
values ('Ivan', 'Petrov', '21','IvanPetrov', '$2a$12$ZJhUv1jTQ8IhM6lOpSmtuuf/jyXMbEQoACw8Rq5FNjcB90wIwyx0W');
insert into user (name, surname, age,  login, password)
values ('Fedor', 'Ivanov', '12', 'FedorIvanov', '$2a$12$ZJhUv1jTQ8IhM6lOpSmtuuf/jyXMbEQoACw8Rq5FNjcB90wIwyx0W');
insert into role (role) values ('ROLE_ADMIN'), ('ROLE_USER');
insert into user_roles set user_id=(select id from user where login = 'IvanPetrov'),
                           role_id = (select id from role where role = 'ROLE_ADMIN');
insert into user_roles set user_id=(select id from user where login = 'FedorIvanov'),
                           role_id = (select id from role where role = 'ROLE_USER');