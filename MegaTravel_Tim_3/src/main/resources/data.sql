
delete from software;
delete from relation;


insert into software (id, name, certificated, alias) values (1, 'soft1', false, 'localSoft1');
insert into software (id, name, certificated, alias) values (2, 'soft2', false, 'localSoft2');
insert into software (id, name, certificated, alias) values (3, 'soft3', false, 'localSoft3');
insert into software (id, name, certificated, alias) values (4, 'soft4', false, 'localSoft4');
insert into software (id, name, certificated, alias) values (5, 'soft5', false, 'localSoft5');

delete from certificate where id_subject = 31;