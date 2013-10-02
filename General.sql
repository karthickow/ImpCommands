select * from v$database;
select * from v$instance;
select * from database_properties where property_name like '%TAB%';

select distinct privilege from all_tab_privs;
select * from dba_alert_arguments;
select * from dba_alert_history;
select * from dba_all_tables;
select * from dba_application_roles;
select * from dba_connect_role_grantees;
select * from dba_data_files;
select * from dba_db_links;
select * from dba_errors;
select * from dba_external_tables;
select * from dba_external_locations;
select * from dba_free_space;
select * from dba_global_context;
select * from dba_indexes;
select * from dba_indextypes;
select * from dba_java_arguments;
select * from dba_jobs;
select * from dba_locks;
select * from dba_lock;
select * from dba_objects;
select * from dba_object_tables;
select * from dba_policies;
select * from dba_procedures;
select * from dba_profiles;
select * from dba_roles;
select * from dba_role_privs;
select * from dba_segments;
select * from dba_sequences where sequence_name ='IUID_SEQ' ;
select * from dba_services;
select * from dba_source;
select distinct privilege from dba_sys_privs order by 1;
select * from dba_synonyms;
select * from dba_tab_cols;
select * from dba_tab_columns;
select * from dba_tab_privs;
select * from dba_tables;
select * from dba_tablespaces;
select * from dba_temp_files;
select * from dba_temp_free_space;
select * from dba_triggers where owner ='SCHEMA_OWNER' ;
select * from dba_views;
select * from dba_workspaces;
select * from dba_users;


show parameter OS_AU;
show user;

-- Remove existing users and roles with the same names.
--DROP USER schema_owner CASCADE;
--DROP USER app_user CASCADE;
--DROP ROLE schema_rw_role;
--DROP ROLE schema_ro_role;

-- Schema owner
--create user schema_owner identified by Admin123 default tablespace users temporary tablespace temp quota unlimited on users;
--grant connect, create table to schema_owner;

-- Application user 
--create user app_user identified by Admin123 default tablespace users temporary tablespace temp;
--grant connect to app_user;

--CREATE ROLE schema_rw_role;
--CREATE ROLE schema_ro_role;

--GRANT schema_rw_role TO app_user;

--CREATE TABLE test_tab (
--  id          NUMBER,
--  description VARCHAR2(50),
--  CONSTRAINT test_tab_pk PRIMARY KEY (id)
--);

--GRANT SELECT ON test_tab TO schema_ro_role;
--GRANT SELECT, INSERT, UPDATE, DELETE ON test_tab TO schema_rw_role;

desc test_tab;

--GRANT create trigger to schema_owner;
--grant create sequence to schema_owner;

--drop trigger TRG_IUID;
--drop sequence IUID_SEQ;
--
-- alter session set current_schema = SYSMAN;
-- exec mgmt_target.set_agent_tzrgn('L-156038836:3938','Asia/Calcutta');
select * from mgmt_targets;
select name from v$database;
select instance from v$thread;
SELECT * FROM V$TIMEZONE_FILE;

-- emctl status dbconsole
-- emctl start dbconsole
-- emctl status agent
-- emctl start agent
-- lsnrctl status
-- lsnrctl start

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


grant connect, resource, create session, create view to demomigration identified by demomigration;

-- backup database using command prompt 
set ORACLE_SID=xe 
D:\Karthick\InstalledApps\oraclexe\app\oracle\product\11.2.0\server\bin>rman target /
backup database;
list backup;
delete backup;
backup database plus archivelog;

sqlplus / as sysdba
shutdown abort;
startup;

RMAN> startup nomount;
RMAN>restore controlfile from '*_1_1';
RMAN> alter database mount;
RMAN> restore database;
RMAN> recover database;
RMAN> alter database open resetlogs;  -- archive log mode

-- Enable archive mode 
-- To check archive mode status
 archive log list;
 
 show parameter recovery_file_dest;
 show parameter DB_RECOVERY_FILE_DEST;
 alter system set log_archive_dest_1='LOCATION=D:/Karthick/InstalledApps/oraclexe/app/oracle/archive' scope = both;
 -- Now we shutdown the database and bring it backup in mount mode.
 shutdown immediate;
 startup mount;
 -- Lastly all that is needed it set archive log mode and open the database.
 alter database archivelog;
 alter database open;
 
 --You can switch to the log file to see that an archive is written to archive log location.
 alter system switch logfile;
 
 -- Disable Archive Log Mode
 -- Now we shutdown the database and bring it backup in mount mode.
 shutdown immediate;
 startup mount;
 -- All that is left is to disable archive log mode and open the database.
 alter database noarchivelog;
 alter database open;
 archive log list;
 
 -- export and import schema
 exp userid=dbo_BusBookingSystem/dbo_BusBookingSystem@XE file=c:\bus.dmp
 imp BusBookingSystem/BusBookingSystem@XE fromuser=dbo_BusBookingSystem touser=BusBookingSystem file=c:\bus.dmp
 
 grant connect, resource, create session, create view to BusBookingSystem identified by BusBookingSystem;
 -- The Oracle DROP USER CASCADE command drops a user and all owned objects
 DROP  USER  BusBookingSystem  CASCADE;