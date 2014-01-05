create table Employees (empid number primary key,  firstname varchar2(30), lastname varchar2(30), age number);
create sequence emp_id_seq;
create trigger trg_emp_id
      before insert on Employees
      for each row
    begin
      select emp_id_seq.nextval
        into :new.empid
       from dual;
 	end;

INSERT INTO Employees (firstname, lastname, age) VALUES ('Poongothai', 'Krishnan', 54);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Mohan', 'Krishnan', 31);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Karthick', 'Krishnan', 29);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Suder', 'Krishnan', 26);
INSERT INTO Employees (firstname, lastname, age) VALUES ('Kaviya', 'Krishnan', 24);

select * from EMPLOYEES;

create table books (bookid number primary key,  bookname varchar2(30));
create sequence book_id_seq;
create trigger trg_book_id
      before insert on books
      for each row
    begin
      select book_id_seq.nextval
        into :new.bookid
       from dual;
 	end; 
 select * from books;	
 