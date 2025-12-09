# Bank-Application-Crud-Operation

This is a simple console-based Bank Management System built using Java and XAMPP.
It allows users to create an account, deposit money, withdraw money, and check their balance.


Features:
1.Register a new bank account
2.Deposit amount
3.Withdraw amount
4.Check account balance
5.Input validation for all fields
6.XAMPP database connectivity using JDBC

Database Setup
Create a database and table in XAMPP:

CREATE DATABASE bank;

CREATE TABLE BOI(
    accountnumber INT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    contact BIGINT,
    address VARCHAR(255),
    amount DOUBLE
);


How to Run:
create a package bank. inside it put the two file BOI.java and Validation.java
Compile and run the main file BOI.java 

Description:
The project contains the following classes:
DBConnection – connects Java to XAMPP
Operation – performs register, deposit, withdraw, and balance check
Validation – validates inputs like name, account number, email, contact, amount
BOI – main class with menu options
