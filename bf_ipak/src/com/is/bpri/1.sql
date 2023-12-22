create table bproduct(
id number(20),
branch char(5),
customer number(8),
prodid number(3),
vdate date,
currency char(3),
amount number (20),
emp_id number(5),
state number(1)
);

create table bproduct_desc(
id number(20),
detail_group number(6),
detail_id number(10));


SEQ_bproduct
create sequence SEQ_bproduct
minvalue 1
maxvalue 99999999
start with 2
increment by 1;
