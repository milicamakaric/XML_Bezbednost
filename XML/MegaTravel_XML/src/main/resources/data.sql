
delete from user_roles;
delete from user;
delete from address;
delete from role_permissions;
delete from permission;
delete from role;



insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_AGENT');
insert into role (id, name) values (3, 'ROLE_CLIENT');

insert into permission (id, name) values (1, 'blockUser');
insert into permission (id, name) values (2, 'deleteUser');
insert into permission (id, name) values (3, 'addAgent');

insert into role_permissions (role_id, permission_id) values (1, 1);
insert into role_permissions (role_id, permission_id) values (1, 2);
insert into role_permissions (role_id, permission_id) values (1, 3);

insert into address (id, city, latitude, longitude, number, ptt, state, street) values (1, 'City 1', 50.5, 50.5, '1a', 10000, 'State1', 'Street1');


insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked) values (1, 'admin', 'admin', 'admin@gmail.com', '$2a$10$QdIyICaBORkvQftKsAvnoeUdrVkQAJJ.h73i4m.ze2xybC7YD6NGW', true, 'administrator', 'ROLE_ADMIN', 1, false, false);

insert into user_roles (user_id, role_id) values (1, 1);

insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked) values (2, 'client', 'client', 'client@gmail.com', '$2a$10$eWguQzMPEVDCzEm0qxBHQeatjNbGyKs4lJHXagpnoMqX1Yf.wDHZG', true, 'client', 'ROLE_CLIENT', 1, false, false);

insert into user_roles (user_id, role_id) values (1, 3);


insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked) values (3, 'agent', 'agent', 'agent@gmail.com', '$2a$10$Ec2InGzSXZXK6ig5xeCOiOA5RsIQITfTRsX8T7Uo1OhN3scAgy8iS', true, 'agent', 'ROLE_AGENT', 1, false, false);

insert into user_roles (user_id, role_id) values (3, 2);