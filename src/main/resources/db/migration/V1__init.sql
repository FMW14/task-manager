create table projects (id bigint generated by default as identity, created_at timestamp default current_timestamp, description varchar(255), name varchar(255), updated_at timestamp default current_timestamp, creator_id bigint, primary key (id));
create table roles (id bigint generated by default as identity, created_at timestamp default current_timestamp, name varchar(255), updated_at timestamp default current_timestamp, primary key (id));
create table tasks (id bigint generated by default as identity, archived boolean, created_at timestamp default current_timestamp, deadline_time timestamp, description varchar(255), name varchar(255), updated_at timestamp default current_timestamp, primary key (id));
create table tasksauthorities (id bigint generated by default as identity, name varchar(255), primary key (id));
create table taskstates (id bigint generated by default as identity, codename varchar(255), name varchar(255), task_state_id bigint, primary key (id));
create table users (id bigint generated by default as identity, created_at timestamp default current_timestamp, email varchar(255), name varchar(255), password varchar(255), surname varchar(255), updated_at timestamp default current_timestamp, username varchar(255), primary key (id));
create table users_roles (user_id bigint not null, role_id bigint not null);
create table users_tasks (id bigint generated by default as identity, task_id bigint, user_id bigint, task_authority_id bigint, primary key (id));
alter table projects add constraint FK14mww7skdu5cpg6nq2kwcnx0e foreign key (creator_id) references users;
alter table taskstates add constraint FK7io7uiaayvl77fqmwf1gmqvcn foreign key (task_state_id) references taskstates;
alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles;
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users;
alter table users_tasks add constraint FK7todmyl52eiddpi6hc2nfgvbs foreign key (task_id) references tasks;
alter table users_tasks add constraint FK6frwjo48hefay0rg31q970r8t foreign key (user_id) references users;
alter table users_tasks add constraint FKdf638bjknjqf1ox6gr4q3sn0k foreign key (task_authority_id) references tasksauthorities;