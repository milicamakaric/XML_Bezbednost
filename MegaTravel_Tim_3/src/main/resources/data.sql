delete from software;
delete from certificate;
delete from user;

insert into software (id, name, certificated) values (1, 'soft1', false);
insert into software (id, name, certificated) values (2, 'soft2', true);
insert into software (id, name, certificated) values (3, 'soft3', false);
insert into software (id, name, certificated) values (4, 'soft4', true);
insert into software (id, name, certificated) values (5, 'soft5', false);