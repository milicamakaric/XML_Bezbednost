delete from accommodation_comments;
delete from reservation;

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

delete from accommodation_comments;
delete from accommodation_addServices;
delete from accommodation_comments;
delete from accommodation;
delete from cancelation;
delete from comment;
delete from accommodation_type;
delete from additional_service;
delete from address;
delete from room;
delete from comment;

insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_AGENT');
insert into role (id, name) values (3, 'ROLE_CLIENT');

insert into permission (id, name) values (1, 'blockUser');
insert into permission (id, name) values (2, 'deleteUser');
insert into permission (id, name) values (3, 'addAgent');

insert into permission (id, name) values (4, 'allUsers');
insert into permission (id, name) values (5, 'reservation');

insert into permission (id, name) values (6, 'addAccommodationUnit');
insert into permission (id, name) values (7, 'loginClient');
insert into permission (id, name) values (8, 'loginAdmin');
insert into permission (id, name) values (9, 'loginAgent');
insert into permission (id, name) values (10, 'addAccommodationType');
insert into permission (id, name) values (11, 'getTypes');
insert into permission (id, name) values (12, 'addAdditionalService');
insert into permission (id, name) values (13, 'getServices');
insert into permission (id, name) values (14, 'activateUser');
insert into permission (id, name) values (15, 'addAccommodation');
insert into permission (id, name) values (16, 'getAccommodations');
insert into permission (id, name) values (17, 'getAgents');
insert into permission (id, name) values (18, 'getAgentAccommodation');
insert into permission (id, name) values (19, 'getAgentRooms');
insert into permission (id, name) values (20, 'addSpecialPrice');
insert into permission (id, name) values (21, 'addRoom');
insert into permission (id, name) values (22, 'getAgentMessages');
insert into permission (id, name) values (28, 'getComm');
insert into permission (id, name) values (27, 'aproveComm');
insert into permission (id, name) values (25, 'getAgentOfRoom');
insert into permission (id, name) values (26, 'sendMessage');

insert into permission (id, name) values (29, 'getAgentReservations');

insert into permission (id, name) values (24, 'addAgentReservation');
insert into permission (id, name) values (23, 'sendAnswer');



insert into role_permissions (role_id, permission_id) values (1, 1);
insert into role_permissions (role_id, permission_id) values (1, 2);
insert into role_permissions (role_id, permission_id) values (1, 3);
insert into role_permissions (role_id, permission_id) values (1, 4);
insert into role_permissions (role_id, permission_id) values (1, 8);
insert into role_permissions (role_id, permission_id) values (1, 10);
insert into role_permissions (role_id, permission_id) values (1, 11);
insert into role_permissions (role_id, permission_id) values (1, 12);
insert into role_permissions (role_id, permission_id) values (1, 13);
insert into role_permissions (role_id, permission_id) values (1, 14);
insert into role_permissions (role_id, permission_id) values (1, 15);
insert into role_permissions (role_id, permission_id) values (1, 16);
insert into role_permissions (role_id, permission_id) values (1, 17);
insert into role_permissions (role_id, permission_id) values (1, 28);
insert into role_permissions (role_id, permission_id) values (1, 27);

insert into role_permissions (role_id, permission_id) values (2, 6);
insert into role_permissions (role_id, permission_id) values (2, 18);
insert into role_permissions (role_id, permission_id) values (2, 19);
insert into role_permissions (role_id, permission_id) values (2, 9);
insert into role_permissions (role_id, permission_id) values (2, 20);

insert into role_permissions (role_id, permission_id) values (2, 29);

insert into role_permissions (role_id, permission_id) values (2, 21);
insert into role_permissions (role_id, permission_id) values (2, 22);
insert into role_permissions (role_id, permission_id) values (2, 23);
insert into role_permissions (role_id, permission_id) values (2, 24);

insert into role_permissions (role_id, permission_id) values (3, 5);
insert into role_permissions (role_id, permission_id) values (3, 7);
insert into role_permissions (role_id, permission_id) values (3, 25);
insert into role_permissions (role_id, permission_id) values (3, 26);



insert into address (id, city, number, ptt, state, street,distance) values (1, 'City 1', '1a', 10000, 'State1', 'Street1',10);
insert into address (id, city, number, ptt, state, street,distance) values (2, 'City 2', '2a', 20000, 'State2', 'Street2',20);


insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked) values (1, 'admin', 'admin', 'admin@gmail.com', '$2a$10$QdIyICaBORkvQftKsAvnoeUdrVkQAJJ.h73i4m.ze2xybC7YD6NGW', true, 'administrator', 'ROLE_ADMIN', 1, false, false);

insert into user_roles (user_id, role_id) values (1, 1);

insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked) values (2, 'client', 'client', 'client@gmail.com', '$2a$10$eWguQzMPEVDCzEm0qxBHQeatjNbGyKs4lJHXagpnoMqX1Yf.wDHZG', true, 'client', 'ROLE_CLIENT', 1, false, false);

insert into user_roles (user_id, role_id) values (2, 3);


insert into user (id, name, surname, email, password, enabled, dtype, role, address_id, deleted, blocked, pib, pass_changed) values (3, 'agent', 'agent', 'agent@gmail.com', '$2a$10$Ec2InGzSXZXK6ig5xeCOiOA5RsIQITfTRsX8T7Uo1OhN3scAgy8iS', true, 'agent', 'ROLE_AGENT', 1, false, false, '111111111', false);

insert into user_roles (user_id, role_id) values (3, 2);

insert into accommodation_type (id, name) values(1, 'hotel');

insert into accommodation_type (id, name) values(2, 'bed&breakfast');
insert into accommodation_type (id, name) values(3, 'apartman');

insert into cancelation (id, allowed, number_of_days) values (1, false, -1);

insert into accommodation (id, name, description, rating, address_id, cancelation_id, type_id, stars) values (1, 'Hotel1', 'New hotel in the city.', 0, 1, 1, 1, 3);
insert into accommodation (id, name, description, rating, address_id, cancelation_id, type_id, stars) values (2, 'Hotel2', 'The hotel with tradicion.', 0, 1, 1, 2, 4);
insert into accommodation (id, name, description, rating, address_id, cancelation_id, type_id, stars) values (3, 'Hotel3', 'The hotel with tradicion.', 0, 2, 1, 2, 5);

insert into comment (id,content,allowed) values (1," sve pohvale",false);
insert into accommodation_comments (accommodation_id,comments_id) values (1,1);
insert into accommodation_agent (accommodation_id, agent_id) values (1, 3); 
insert into accommodation_agent (accommodation_id, agent_id) values (2, 3); 

insert into message (id, content, sending, title, agent_id, client_id) values (1, 'prva poruka', false, 'prvi title', 3, 2);
insert into message (id, content, sending, title, agent_id, client_id) values (2, 'druga poruka', false, 'drugi title', 3, 2);
insert into message (id, content, sending, title, agent_id, client_id) values (3, 'treca poruka', false, 'treci title', 3, 2);

insert into accommodation_agent (accommodation_id, agent_id) values (3, 3); 

insert into room (id, capacity, default_price, accommodation_id, agent_id) values (1, 3, 400, 1, 3);
insert into room (id, capacity, default_price, accommodation_id, agent_id) values (2, 4, 500, 2, 3);
insert into room (id, capacity, default_price, accommodation_id, agent_id) values (3, 2, 300, 3, 3);

insert into additional_service (id, name) values (1, 'WIFI');
insert into additional_service (id, name) values (2, 'TV');
insert into additional_service (id, name) values (3, 'Pet friendly');


insert into reservation (id, start_date, end_date, total_price, status, client_id, room_id) values (1, '2019-08-25 22:00:00.000000', '2019-08-30 22:00:00.000000', 100, 'active', 2 , 1);
insert into reservation (id, start_date, end_date, total_price, status, client_id, room_id) values (2, '2018-12-25 22:00:00.000000', '2018-12-29 22:00:00.000000', 200, 'canceled', 2 , 1);
insert into reservation (id, start_date, end_date, total_price, status, client_id, room_id) values (3, '2018-12-25 22:00:00.000000', '2018-12-29 22:00:00.000000', 300, 'finished', 2 , 2);
insert into reservation (id, start_date, end_date, total_price, status, client_id, room_id) values (4, '2018-12-25 22:00:00.000000', '2018-12-29 22:00:00.000000', 400, 'reserved', 2 , 3);

insert into price_for_night (id, start_date, end_date, price) values (1, '2019-07-01 22:00:00.000000', '2019-07-10 22:00:00.000000', 600);
insert into price_for_night (id, start_date, end_date, price) values (2, '2019-07-05 22:00:00.000000', '2019-07-20 22:00:00.000000', 500);
insert into price_for_night (id, start_date, end_date, price) values (3, '2019-07-01 22:00:00.000000', '2019-07-10 22:00:00.000000', 444);

insert into room_prices (room_id, prices_id) values(1, 1);
insert into room_prices (room_id, prices_id) values(2, 3);