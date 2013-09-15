select dbms_xdb.gethttpport as "HTTP-Port", dbms_xdb.getftpport as "FTP-Port" from dual;
--begin 
--  execute  dbms_xdb.sethttpport('8086'); 
--  Execute  dbms_xdb.setftpport('2100'); 
--end

--create user sonar identified by sonar default tablespace USERS temporary tablespace TEMP profile DEFAULT quota UNLIMITED on USERS;
--GRANT CONNECT TO sonar; 
--GRANT create procedure, create session, create table, create type, create view, create synonym, create trigger, resource TO sonar;

--create user manager identified by manager default tablespace USERS temporary tablespace TEMP profile DEFAULT quota UNLIMITED on USERS;
--grant connect to manager;
--grant create procedure, create session, create table, create type, create view, create synonym, create trigger, resource, create sequence to manager;

-- table
CREATE TABLE CONTACTS
(   
    id                NUMBER not null,
    firstname         VARCHAR2(30),
    lastname          VARCHAR2(30),
    cell_no           VARCHAR2(15),
    email_id          VARCHAR2(30),
    website           VARCHAR2(150),
    birthdate         DATE,
    created           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by        VARCHAR2(40) DEFAULT USER, 
    last_updated      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    last_updated_by   VARCHAR2(40) DEFAULT USER,
    
    constraint pk_id PRIMARY KEY(id)
);

-- Sequence
create sequence contacts_id_seq start with 1 increment by 1 minvalue 1 maxvalue 10000 cache 10;

-- Trigger
--create or replace TRIGGER CONTACTS_trg 
--BEFORE INSERT ON CONTACTS 
--FOR EACH ROW
--BEGIN
--   SELECT contacts_id_seq.NEXTVAL INTO :NEW.id FROM DUAL;
--END;


select * from contacts;

------------------------------------


--create user student identified by student default tablespace USERS temporary tablespace TEMP profile DEFAULT quota UNLIMITED on USERS;
--grant connect to student;
--grant create procedure, create session, create table, create type, create view, create synonym, create trigger, resource, create sequence to student;

select * from student;
select student_seq.nextval from dual;
