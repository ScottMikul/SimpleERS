INSERT INTO  ReimbursementRequest (employeeid ,status, image, amount,description , resolver, hasimage) values (2 ,'pending', null, 2.00, 'chum bucket', null,false);
INSERT INTO  ReimbursementRequest (employeeid ,status, image, amount,description , resolver, hasimage) values (2 ,'pending', null, 2.00, 'crabby patty', null,false);
INSERT INTO  ReimbursementRequest (employeeid , status, image, amount,description , resolver ,hasimage) values (3 ,'rejected', null, 300.00, 'clarinet', 1, false);
INSERT INTO  ReimbursementRequest (employeeid , status, image, amount,description , resolver ,hasimage) values (4 ,'rejected', null, 3.00, 'mayonaise', 1, false);
INSERT INTO  ReimbursementRequest (employeeid , status,image, amount,description , resolver, hasimage) values (2 ,'approved',null, 200.00, 'OCA exam', 1, false);
INSERT INTO  ReimbursementRequest (employeeid , status,image, amount,description , resolver, hasimage) values (2 ,'approved',null, 200.00, 'OCA exam', 1, false);


INSERT INTO employee (first_name,last_name,username, password, email, accounttype, managerid,ismanager) values ('krusty', 'krab', 'krabofthesea', 'ilovemoney', 'krabby@gmail.com', 1,null, true);
INSERT INTO employee (first_name,last_name,username, password, email, accounttype, managerid, ismanager) values ('sponge', 'bob', 'spongeman', 'krustykrab4me', 'sponge@gmail.com', 1,1,false);
INSERT INTO employee (first_name,last_name,username, password, email, accounttype, managerid, ismanager) values ('squid', 'ward', 'squidofthesea', 'ihatemyneighbor', 'squidward@gmail.com', 1,1,false);
INSERT INTO employee (first_name,last_name,username, password, email, accounttype, managerid, ismanager) values ('patrick', 'star', 'patrickstar', 'pattrickstar', 'squidward@gmail.com', 1,1,false);
INSERT INTO employee (first_name,last_name,username, password, email, accounttype, managerid, ismanager) values ('karen', 'plankton', 'robotofthesea', 'oilonly4me', 'robot@gmail.com', 1,null,true);
INSERT INTO employee (first_name,last_name,username, password, email, accounttype, managerid, ismanager) values ('plank', 'ton', 'plankton', 'plankton4me', 'plankton@gmail.com', 1,1,false);



SELECT * FROM employee;
truncate table employee cascade ;
drop table employee;
select * from reimbursementrequest ;
drop table reimbursementrequest ;
truncate table reimbursementrequest ;

