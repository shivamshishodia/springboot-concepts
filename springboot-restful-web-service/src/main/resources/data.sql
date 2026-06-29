-- CREATE is executed by the service.
-- create table user (id integer not null, age timestamp, name varchar(20), primary key (id));

insert into user values (1, sysdate(), 'adam');
insert into user values (2, sysdate(), 'bill');
insert into user values (3, sysdate(), 'calvin');
insert into user values (4, sysdate(), 'dan');
insert into user values (5, sysdate(), 'elvin');

insert into post values (100, 'My first post', 1);
insert into post values (101, 'My second post', 1);
