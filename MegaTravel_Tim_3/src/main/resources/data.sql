delete from users_roles;
delete from roles_privileges;
delete from privilege;
delete from role;
delete from user;

delete from software;
delete from relation;
delete from certificate;


insert into user (id, name, surname, email, password, certificated) values (1, 'Admin', 'Admin', 'adminMAMA@gmail.com', '$2a$10$xOP3wZKGwnVIyo67ufG95emMRQDrYHo6AIPpivUIbAw7f/VAFJStu', true);
insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_USER');
insert into role (id, name) values (3, 'ROLE_AGENT');
insert into privilege (id, name) values (1, 'PROBA_PRIV');
insert into roles_privileges (role_id, privilege_id) values (1,1);
insert into users_roles (user_id, role_id) values (1,1);

insert into certificate (id, ca, end_date, id_issuer, id_subject, reason_for_revokation, revoked, start_date) values (57, 1, '2021-04-15 22:00:00.000000', 1, 1, '', 0, '2019-04-15 22:00:00.000000');

insert into software (id, name, certificated, alias) values (1, 'soft1', false, 'localSoft1');
insert into software (id, name, certificated, alias) values (2, 'soft2', false, 'localSoft2');
insert into software (id, name, certificated, alias) values (3, 'soft3', false, 'localSoft3');
insert into software (id, name, certificated, alias) values (4, 'soft4', false, 'localSoft4');
insert into software (id, name, certificated, alias) values (5, 'soft5', false, 'localSoft5');
