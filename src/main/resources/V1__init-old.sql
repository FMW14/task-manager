create table notifications (id bigint generated by default as identity, created_at timestamp default current_timestamp, message varchar(255), updated_at timestamp default current_timestamp, task_id bigint, user_id bigint, primary key (id));
create table projects (id bigint generated by default as identity, created_at timestamp default current_timestamp, description varchar(255), name varchar(255), updated_at timestamp default current_timestamp, creator_id bigint, primary key (id));
create table roles (id bigint generated by default as identity, created_at timestamp default current_timestamp, name varchar(255), updated_at timestamp default current_timestamp, primary key (id));
create table taskparticipants (id bigint generated by default as identity, task_id bigint, user_id bigint, task_authority_id bigint, primary key (id));
create table tasks (id bigint generated by default as identity, archived boolean, created_at timestamp default current_timestamp, deadline_time timestamp, description varchar(255), name varchar(255), updated_at timestamp default current_timestamp, project_id bigint, task_state_id bigint, primary key (id));
create table tasksauthorities (id bigint generated by default as identity, name varchar(255), primary key (id));
create table taskstates (id bigint generated by default as identity, codename varchar(255), name varchar(255), primary key (id));
create table users (id bigint generated by default as identity, created_at timestamp default current_timestamp, email varchar(255), name varchar(255), password varchar(255), surname varchar(255), updated_at timestamp default current_timestamp, username varchar(255), primary key (id));
create table users_projects (user_id bigint not null, project_id bigint not null);
create table users_roles (user_id bigint not null, role_id bigint not null);
alter table notifications add constraint FK2ktjq1slw0ldkuy5rx8fbte2p foreign key (task_id) references tasks;
alter table notifications add constraint FK9y21adhxn0ayjhfocscqox7bh foreign key (user_id) references users;
alter table projects add constraint FK14mww7skdu5cpg6nq2kwcnx0e foreign key (creator_id) references users;
alter table taskparticipants add constraint FKc1q4q9ccgfhfy2rivep3l0g44 foreign key (task_id) references tasks;
alter table taskparticipants add constraint FK7flwhnigdf7t9yvtys88pxahr foreign key (user_id) references users;
alter table taskparticipants add constraint FKiasaiwhxqhwnlh6tbfjygdg1v foreign key (task_authority_id) references tasksauthorities;
alter table tasks add constraint FKsfhn82y57i3k9uxww1s007acc foreign key (project_id) references projects;
alter table tasks add constraint FKdqtsfiodxf575vn7k91t0gg7v foreign key (task_state_id) references taskstates;
alter table users_projects add constraint FKkoh0xgfur21fdanq862b3qx7n foreign key (project_id) references projects;
alter table users_projects add constraint FKen924y69h6d6chaojjgqfaow8 foreign key (user_id) references users;
alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles;
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users;