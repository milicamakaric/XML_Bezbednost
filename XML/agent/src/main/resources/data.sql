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
delete from reservation;
delete from user;

delete from room;

insert into role (id, name) values (2, 'ROLE_AGENT');

insert into permission (id, name) values (6, 'addAccommodationUnit');
insert into permission (id, name) values (9, 'loginAgent');
insert into permission (id, name) values (18, 'getAgentAccommodation');
insert into permission (id, name) values (19, 'getAgentRooms');
insert into permission (id, name) values (20, 'addSpecialPrice');
insert into permission (id, name) values (21, 'addRoom');
insert into permission (id, name) values (22, 'getAgentMessages');
insert into permission (id, name) values (23, 'sendAnswer');
<<<<<<< HEAD
insert into permission (id, name) values (24, 'addAgentReservation');
=======
insert into permission (id, name) values (24, 'aproveComm');
insert into permission (id, name) values (25, 'getAgentOfRoom');
insert into permission (id, name) values (26, 'sendMessage');
>>>>>>> dd8a5c305aaf98e23ab045ea374a447f45760da2


insert into role_permissions (role_id, permission_id) values (2, 6);
insert into role_permissions (role_id, permission_id) values (2, 18);
insert into role_permissions (role_id, permission_id) values (2, 19);
insert into role_permissions (role_id, permission_id) values (2, 9);
insert into role_permissions (role_id, permission_id) values (2, 20);
insert into role_permissions (role_id, permission_id) values (2, 21);
insert into role_permissions (role_id, permission_id) values (2, 22);
insert into role_permissions (role_id, permission_id) values (2, 24);


insert into address (id, city, number, ptt, state, street,distance) values (20, 'City agent adresa', '1a', 10000, 'State agent', 'Street1',10);

insert into role_permissions (role_id, permission_id) values (2, 23);


insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked, pib, pass_changed) values (3, 'agent', 'agent', 'agent@gmail.com', '$2a$10$Ec2InGzSXZXK6ig5xeCOiOA5RsIQITfTRsX8T7Uo1OhN3scAgy8iS', true, 'agent', 'ROLE_AGENT', 20, false, false, '111111111', false);

insert into user_roles (user_id, role_id) values (3, 2);
<<<<<<< HEAD

insert into accommodation_type (id, name) values(1, 'hotel');
insert into accommodation_type (id, name) values(2, 'bed&breakfast');
insert into accommodation_type (id, name) values(3, 'apartman');
insert into cancelation (id, allowed, number_of_days) values (1, false, -1);

insert into accommodation (id, name, description, rating, address_id, cancelation_id, type_id, stars) values (1, 'Hotel1', 'New hotel in the city.', 0, 1, 1, 1, 3);
insert into accommodation (id, name, description, rating, address_id, cancelation_id, type_id, stars) values (2, 'Hotel2', 'The hotel with tradicion.', 0, 1, 1, 2, 4);
insert into accommodation (id, name, description, rating, address_id, cancelation_id, type_id, stars) values (3, 'Hotel3', 'The hotel with tradicion.', 0, 2, 1, 2, 5);

insert into accommodation_agent (accommodation_id, agent_id) values (1, 3); 
insert into accommodation_agent (accommodation_id, agent_id) values (2, 3); 
insert into accommodation_agent (accommodation_id, agent_id) values (3, 3); 

insert into room (id, capacity, default_price, accommodation_id, agent_id) values (1, 3, 400, 1, 3);

insert into message (id, title, content, sending, agent_id, client_id) values (1, 'Hello', 'It is my first message to you.', true, 3, 2);
insert into message (id, title, content, sending, agent_id, client_id) values (2, 'Question', 'Is there my reservation for room 2 in your database?', true, 3, 2);
insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked) values (100, 'clientBase', 'clientBase', 'clientBase@gmail.com', '$2a$10$eWguQzMPEVDCzEm0qxBHQeatjNbGyKs4lJHXagpnoMqX1Yf.wDHZG', true, 'client', 'ROLE_CLIENT', 1, false, false);
=======
>>>>>>> dd8a5c305aaf98e23ab045ea374a447f45760da2
