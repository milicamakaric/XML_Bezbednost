delete from accommodation_agent;
delete from user_roles;
delete from message;
delete from room_prices;
delete from price_for_night;
delete from reservation;
delete from room;
delete from user;
delete from role_permissions;
delete from permission;
delete from role;
delete from accommodation_addServices;
delete from accommodation_comments;
delete from accommodation;
delete from cancelation;
delete from comment;
delete from accommodation_type;
delete from additional_service;
delete from address;

insert into role (id, name) values (2, 'ROLE_AGENT');

insert into permission (id, name) values (6, 'addAccommodationUnit');
insert into permission (id, name) values (9, 'loginAgent');
insert into permission (id, name) values (18, 'getAgentAccommodation');
insert into permission (id, name) values (19, 'getAgentRooms');
insert into permission (id, name) values (20, 'addSpecialPrice');
insert into permission (id, name) values (21, 'addRoom');

insert into role_permissions (role_id, permission_id) values (2, 6);
insert into role_permissions (role_id, permission_id) values (2, 18);
insert into role_permissions (role_id, permission_id) values (2, 19);
insert into role_permissions (role_id, permission_id) values (2, 9);
insert into role_permissions (role_id, permission_id) values (2, 20);
insert into role_permissions (role_id, permission_id) values (2, 21);

insert into address (id, city, number, ptt, state, street,distance) values (20, 'City agent adresa', '1a', 10000, 'State agent', 'Street1',10);

insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked, pib, pass_changed) values (3, 'agent', 'agent', 'agent@gmail.com', '$2a$10$Ec2InGzSXZXK6ig5xeCOiOA5RsIQITfTRsX8T7Uo1OhN3scAgy8iS', true, 'agent', 'ROLE_AGENT', 20, false, false, '111111111', false);

insert into user_roles (user_id, role_id) values (3, 2);
