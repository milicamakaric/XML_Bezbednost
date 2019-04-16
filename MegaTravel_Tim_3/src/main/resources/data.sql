

delete from software;
delete from relation;
delete from certificate;


insert into certificate (id, ca, end_date, id_issuer, id_subject, reason_for_revokation, revoked, start_date) values (57, 1, '2021-04-15 22:00:00.000000', 1, 1, '', 0, '2019-04-15 22:00:00.000000');

insert into software (id, name, certificated, alias) values (1, 'soft1', false, 'localSoft1');
insert into software (id, name, certificated, alias) values (2, 'soft2', false, 'localSoft2');
insert into software (id, name, certificated, alias) values (3, 'soft3', false, 'localSoft3');
insert into software (id, name, certificated, alias) values (4, 'soft4', false, 'localSoft4');
insert into software (id, name, certificated, alias) values (5, 'soft5', false, 'localSoft5');
