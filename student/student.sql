create table Employees (empid number primary key,  firstname varchar2(30), lastname varchar2(30), age number);

INSERT INTO Employees (firstname, lastname, age) VALUES ('Poongothai', 'Krishnan', 54);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Mohan', 'Krishnan', 31);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Karthick', 'Krishnan', 29);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Suder', 'Krishnan', 26);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Kaviya', 'Krishnan', 24);

create sequence emp_id_seq;

create trigger trg_emp_id
      before insert on Employees
      for each row
    begin
      select emp_id_seq.nextval
        into :new.empid
       from dual;
 	end;
   