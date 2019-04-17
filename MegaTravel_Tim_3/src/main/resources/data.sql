delete from users_roles;
delete from roles_privileges;
delete from privilege;
delete from role;
delete from user;

delete from software;
delete from relation;
delete from certificate;


insert into user (id, name, surname, email, password, certificated) values (1, 'Admin', 'Admin', 'adminmama@gmail.com', '$2a$10$xOP3wZKGwnVIyo67ufG95emMRQDrYHo6AIPpivUIbAw7f/VAFJStu', true);
insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_USER');
insert into role (id, name) values (3, 'ROLE_AGENT');
insert into privilege (id, name) values (1, 'PROBA_PRIV');
insert into roles_privileges (role_id, privilege_id) values (1,1);
insert into users_roles (user_id, role_id) values (1,1);