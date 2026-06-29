-- CREATE is executed by the service.
-- create table currency_exchange (id bigint not null, conversion_multiple decimal(19,2), environment varchar(255), currency_from varchar(255), currency_to varchar(255), primary key (id))

insert into currency_exchange (id, conversion_multiple, environment, currency_from, currency_to) values (10001, 65, '', 'USD', 'INR');
insert into currency_exchange (id, conversion_multiple, environment, currency_from, currency_to) values (10002, 35, '', 'AUD', 'INR');
insert into currency_exchange (id, conversion_multiple, environment, currency_from, currency_to) values (10003, 105, '', 'EUR', 'INR');
