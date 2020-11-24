-- need to point to employeeid
drop table ReimbursementRequest;
create table ReimbursementRequest(id serial,employeeId integer, image bytea, 
amount decimal(6,2) NOT null check(amount > 0.00), 
description varchar not null check(description > ''),
resolver integer, hasImage boolean, primary key(id), 
status varchar,
foreign key(employeeId) references Employee(id),
foreign key (resolver) references Employee(id) );

drop table employee;

-- need to add a status column
create table employee (id serial, 
first_name varchar NOT NULL CHECK (first_name > ''),
last_name varchar NOT NULL CHECK (last_name > ''),
username varchar unique NOT NULL CHECK (username > ''), 
password varchar NOT NULL CHECK (char_length(password) > 7),
email varchar unique not null,
accountType integer,
managerId integer,
isManager boolean,
primary key(id),
foreign key(managerId) references employee(id) )
