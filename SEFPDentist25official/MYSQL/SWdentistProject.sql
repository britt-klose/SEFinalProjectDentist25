set FOREIGN_KEY_CHECKS=0;
set SQL_MODE = 'NO_ZERO_DATE';

-- Initialize Database
drop database if exists dentistsdb;
create database dentistsdb;
use dentistsdb;

-- Create Patients Table
CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone  varchar(200),
    email varchar(50) not null,
    date_of_birth DATE NOT NULL,
    -- update DOB to a string instead of a date
    address varchar(100) not null,
    city varchar(100),
    state  varchar(100),
    zipcode  varchar(100),
    gender  varchar(100),

    constraint gender_constraint check (gender in
    ('F', 'M', 'O'))
);

-- Create Procedures Table
drop table if exists procedures;
CREATE TABLE procedures (
    procedure_id INT PRIMARY KEY AUTO_INCREMENT,
    procedure_name VARCHAR(100) NOT NULL,
    description VARCHAR(300),
    start_price int,
    requires_anestesia VARCHAR(100) NOT NULL 
    -- needs to be updated to anesthesia
);

-- Create Patient History Table
drop table if exists patient_history;
CREATE TABLE patient_history (
    record_id INT primary key,
    patient_id INT NOT NULL,
    procedure_id INT NOT NULL,
    procedure_date DATE NOT NULL,
    dentist VARCHAR(100) NOT NULL,
    assistant VARCHAR(100) NOT NULL,
    notes  varchar(200),

    FOREIGN KEY (patient_id) REFERENCES patients(patient_id), 
    FOREIGN KEY (procedure_id) REFERENCES procedures(procedure_id), 
    
    constraint dentist_constraint check(
    dentist in
    ('Brittany Klose', 'John Smith', 'Samuel Duro-Aina', 'John Smith ')),
    
    constraint assistant_constraint check(
    assistant in
    ('Sam Winchester', 'Nathaniel Bacon', 'Nicolas Jackson', 'Mark Grayson', 'Nolan Edward', 'Harry Potter'))
);

select * from patients;

select * from procedures;

select * from patient_history;

SELECT DISTINCT dentist FROM patient_history;

SELECT DISTINCT assistant FROM patient_history;






