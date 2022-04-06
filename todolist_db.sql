drop database todolistdb;
drop user todolistuser;
create user todolistuser with password 'password';
create database todolistdb with template=template0 owner=todolistuser;
\connect todolistdb;
alter default privileges grant all on tables to todolistuser;
alter default privileges grant all on sequences to todolistuser;


create table tdl_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table tdl_tasks(
userTask_id integer primary key not null,
user_id integer not null,
task varchar(50) not null
);

alter table tdl_tasks add constraint tasks_users_fk
foreign key (user_id) references tdl_users(user_id);


create sequence tdl_users_seq increment 1 start 1;
create sequence tdl_tasks_seq increment 1 start 1000;