# SimpleERS

## Project Description

The Expense Reimbursement System (ERS) manages the process of reimbursing employees for expenses incurred while on company time. All employees can log in and submit requests for reimbursement and view their past tickets and pending requests. Managers can then log in and view all reimbursement requests and past history for all employees. These managers are authorized to approve and deny requests for expense reimbursements.

## Technologies Used

* Servlets
* Java
* JavaScript
* HTML
* CSS
* JDBC
* SQL
* AJAX
* Bootstrap
* RDS
* Tomcat
* Git
* Maven

## Features

List of features ready and TODOs for future development
* Add reimbursement request as employee
* approve/deny reimbursement request as manager
* Create/ destroy session on login and logout
* Bootstrap styling

## Getting Started

git clone https://github.com/ScottMikul/SimpleERS.git

Set up an AWS database:
log into AWS and create an postgresql RDS
Connect to the database and initialize the data for the database utilizing the scripts in the following order
1) ersSchema.sql
2) data.sql
3) ersData.sql

Set up environment variables to utilize the
rds database so that the following code in the connection factory works.
System.getenv("awsdb")
System.getenv("dbname") 
System.getenv("dbpass")

Launch the project from local host on a tomcat server

## License

This project uses the following license: [MIT](LICENCE.md).
