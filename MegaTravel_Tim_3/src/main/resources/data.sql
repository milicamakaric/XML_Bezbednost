delete from software;
delete from certificate;
delete from user;

<<<<<<< HEAD
insert into software (id, name, certificated) values (1, 'soft1', false);
insert into software (id, name, certificated) values (2, 'soft2', false);
insert into software (id, name, certificated) values (3, 'soft3', false);
insert into software (id, name, certificated) values (4, 'soft4', false);
insert into software (id, name, certificated) values (5, 'soft5', false);
=======
insert into software (id, name, certificated, alias) values (1, 'soft1', false, 'localSoft1');
insert into software (id, name, certificated, alias) values (2, 'soft2', true, 'localSoft2');
insert into software (id, name, certificated, alias) values (3, 'soft3', false, 'localSoft3');
insert into software (id, name, certificated, alias) values (4, 'soft4', true, 'localSoft4');
insert into software (id, name, certificated, alias) values (5, 'soft5', false, 'localSoft5');
>>>>>>> 906c37549e68fb8b01efc49f4f1120048bd53253
