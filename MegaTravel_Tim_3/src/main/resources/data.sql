
delete from user_authority;
delete from authority;
delete from user;
delete from certificate;

delete from software;
delete from relation;

insert into user (id, name, surname, email, password, certificated) values (1, 'admin', 'admin', 'admin@gmail.com', '$2a$10$Z64.qrnsDUmTQvUrXQ14hu8AnXR6ZjquesE8oqW/izeiUYMAA8wDe', false);
insert into authority (id, name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_id) values (1, 1);

insert into software (id, name, certificated, alias) values (1, 'soft1', false, 'localSoft1');
insert into software (id, name, certificated, alias) values (2, 'soft2', false, 'localSoft2');
insert into software (id, name, certificated, alias) values (3, 'soft3', false, 'localSoft3');
insert into software (id, name, certificated, alias) values (4, 'soft4', false, 'localSoft4');
insert into software (id, name, certificated, alias) values (5, 'soft5', false, 'localSoft5');
