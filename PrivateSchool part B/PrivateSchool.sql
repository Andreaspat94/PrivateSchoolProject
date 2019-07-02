CREATE SCHEMA `privateSchool` DEFAULT CHARACTER SET utf8 ;
 use privateSchool;

create table student (
sid int not null primary key auto_increment,
sfname varchar(30) not null,
slname varchar(30) not null,
sdob date,
sfees int,
constraint feescheck check (sfees>0)
);

create table length (
lid int not null primary key auto_increment,
lname varchar(30) not null
);

create table direction (
did int not null primary key auto_increment,
dname varchar(30) not null
);

create table profession (
pid int not null primary key auto_increment,
pname varchar(30) not null
);

create table speriod(
spid int not null primary key auto_increment,
spdate date not null
);

create table eperiod(
epid int not null primary key auto_increment,
epdate date not null
);


create table course (
cid int not null primary key auto_increment,
ctitle varchar(30) not null,
cstream int not null,
ctype int not null,
cstart int not null,
cend int not null,
constraint C1 foreign key (cstream) references direction(did),
constraint C2 foreign key (ctype) references length (lid),
constraint C3 foreign key(cstart) references speriod(spid),
constraint C4 foreign key(cend) references eperiod(epid)
);

 

create table studentsPerCourse (
scid int not null primary key auto_increment,
sid int not null,
cid int not null,
constraint SC1 foreign key(sid) references student(sid),
constraint SC2 foreign key(cid) references course(cid) 
);

create table description (
deid int not null primary key auto_increment,
dename varchar(30) not null
);

create table headline(
hid int not null primary key auto_increment,
hname varchar(30) not null
);


create table submission(
subid int not null primary key auto_increment,
subdate date not null
);

create table assignment (

aid int not null primary key auto_increment,
atitle int not null,
adescription int not null,
asubtime int not null,
aoralMark int not null,
atotalMark int not null,
constraint  checkoralmark check ((aoralMark between 0 and 100) and (atotalMark between 0 and 100)),
constraint A1 foreign key(adescription) references description(deid),
constraint A2 foreign key(atitle) references headline(hid),
constraint A3 foreign key(asubtime) references submission(subid)
); 

create table assignmentsPerCourse (
acid int not null primary key auto_increment,
cid int not null,
aid int not null,
constraint AC1 foreign key(cid) references course(cid),
constraint AC2 foreign key(aid) references assignment(aid)
);

create table trainer (
tid int not null primary key auto_increment,
tfname varchar(30) not null,
tlname varchar(30) not null,
tsubject int not null,
constraint T1 foreign key (tsubject) references profession(pid)
);

create table trainersPerCourse (
tcid int not null primary key auto_increment,
cid int not null,
tid int not null,
constraint TC1 foreign key (cid) references course(cid),
constraint TC2 foreign key( tid) references trainer (tid)
);

create table studentassignments (
said int not null primary key auto_increment,
samark int not null,
acid int not null,
sid int not null,
constraint SA1 foreign key(acid) references assignmentsPerCourse(acid),
constraint SA2 foreign key(sid) references student(sid)
);


insert into student(sfname,slname,sfees,sdob)
values
('Andreas','Paterakis',2500, '1994-05-01'),
('Michalis','Kwnstandinidis',2500, '1990-02-11'),
('Paulos','Natoudis',2500, '1995-10-04'),
('Ioannis','Katsanidis',2500, '1989-01-01'),
('Christos','Paulopoulos',2500, '1998-06-21'),
('Anna','Sevastinou',2500, '1996-05-22'),
('Christina','Papadopoulou',2500, '1994-03-25'),
('Natalia','Christidi',2500, '1992-08-30'),
('Antonis','Aggelopoulos',2500, '1991-11-09'),
('Manos','Ksanthakis',2500, '1994-07-07'),
('Kwstas','Papadakis',2500, '1995-12-02'),
('Natasha','Maurou',2500, '1986-01-06'),
('Sofia','Patrinou',2500, '1985-05-24'),
('Apostolis','Andreadakis',2500, '1999-12-25'),
('Antonis','Klados',2500, '1997-09-29'),
('Despoina','Anastasiou',2500, '1997-09-19'),
('Thanos','Kokkalis',2500, '1988-07-20'),
('Filippos','Sevastakis',2500, '1993-06-27'),
('Dimitris','Thomas',2500, '1993-09-15'),
('Christos','Chatzipaulou',2500, '1995-03-16')
;

insert into length(lname)
values('Full_Time'),('Part_Time')
;

insert into direction(dname)
values('Java'),('C#')
;

insert into profession(pname)
values('Java'),('C#')
;

insert into speriod(spdate)
values
('2019-05-11')
;

insert into eperiod(epdate)
values
('2019-08-11'),
('2019-11-11')
;

insert into course (ctitle,cstream,ctype,cstart,cend)
values
('CB8', 1,1, 1,1),
('CB8', 1,2, 1,2),
('CB8', 2,1, 1,1),
('CB8', 2,2, 1,2)
;

insert into studentsPerCourse (sid,cid)
values
(1,1),(2,1),(3,1),(4,1),(5,1),
(6,2),(7,2),(8,2),(9,2),(10,2),
(11,3),(12,3),(13,3),(14,3),(15,3),
(16,4),(17,4),(18,4),(19,4),(20,4),
 -- students in two courses
(1,3),(4,3),(6,4)
;

insert into description(dename)
values
('Private_School_App_part_A'),
('Private_School_App_part_B'),
('Bonus_Project_Java'),
('Hospital_App_part_A'),
('Hospital_App_part_B'),
('Bonus_Project_C#')
;

insert into headline(hname)
values
('Assignment_1'),
('Assignment_2'),
('Assignment_3')
;


insert into submission(subdate) 
values
-- full time courses
('2019-05-30'),
('2019-06-17'),
('2019-07-10'),
-- part time courses
('2019-06-22'),
('2019-08-30'),
('2019-10-15')
;

insert into assignment(atitle,adescription,asubtime,aoralMark,atotalMark)
values
-- for full time courses
		-- privateSchool 
(1, 1, 1, 100, 100),
(2, 2, 2, 100, 100),
		-- bonus
(3, 3, 3, 100, 100),
		-- Hospital
(1, 4, 1, 100, 100),
(2, 5, 2, 100, 100),
		-- bonus
(3, 6, 3, 100, 100),

-- for part time courses
		-- private school
(1, 1, 4, 100, 100),
(2, 2, 5, 100, 100),
		-- bonus
(3, 3, 6, 100, 100),
		-- Hospital
(1, 4, 4, 100, 100),
(2, 5, 5, 100, 100),
		-- bonus 
(3, 6, 6, 100, 100)
;


insert into assignmentsPerCourse(cid,aid)
values
-- java full time 
(1,1),(1,2),(1,3),
-- java part time
(2,7),(2,8),(2,9),
 -- C# full time
(3,4),(3,5),(3,6),
-- C# part time 
(4,10),(4,11),(4,12)
;

insert into trainer(tfname,tlname,tsubject)
values
('Ioannis', 'Mpezos', 1),
('Stavros','Niarchos',1),
('Andreas','Dhmou',1),
('Giorgos','Paulidis',2),
('Apostolos','Papadakis',2),
('Sakis','Basileiou',2)
;

insert into trainersPerCourse(cid,tid)
values
(1,1),(2,2),(3,3),(4,4),(3,5),(2,6)
;

insert into studentassignments(sid,acid,samark)
values
(1,1,50),(1,2,70),(1,3,67),(1,7,76),(1,8,65),(1,9,83),
(2,1,30),(2,2,25),
(3,1,80),(3,2,80),
(4,1,100),(4,2,91),(4,7,65),(4,8,89),(4,9,91),
(5,1,97),(5,2,94),

(6,4,10),(6,5,0),(6,10,30),(6,11,28),
(7,4,15),(7,5,24),
(8,4,64),(8,5,45),
(9,4,76),(9,5,55),
(10,4,82),(10,5,84),

(11,7,1),(11,8,1),
(12,7,50),(12,8,50),
(13,7,98),(13,8,99),
(14,7,47),(14,8,76),
(15,7,35),(15,8,45),

(16,10,70),(16,11,80),
(17,10,95),(17,11,90),
(18,10,30),(18,11,25),
(19,10,60),(19,11,70),
(20,10,48),(20,11,57)
;


-- all students
select sid as ID, sfname as First_Name, slname as Last_Name, sdob as Date_Of_Birth,sfees as Tuition_Fees from student;


-- all trainers
select tid as ID, tfname as First_Name,tlname as Last_Name,pname as Subject from trainer,profession
where trainer.tsubject = profession.pid ;
--------
select * from profession;
delete from trainer where tid= 7;
-----

-- all courses
select c.cid as ID, c.ctitle as Title, d.dname as Stream, l.lname as Type, s.spdate as Starting_Date, e.epdate as Ending_Date
from course as c , direction as d,length as l,speriod as s,  eperiod as e
where c.cstream = d.did and c.ctype = l.lid and c.cstart = s.spid and c.cend = e.epid
order by ID;

-- all assignments

select a.aid as ID, h.hname as Title, d.dename as Description, s.subdate as Submission_Date,a.aoralMark as Oral_Mark, a.atotalMark as Total_Mark
from assignment as a,description as d,headline as h,submission as s
where a.atitle = h.hid and a.adescription = d.deid and a.asubtime = s.subid
order by ID;

-- all students per course

		select sc.scid as ID, sc.sid as Student_ID,s.sfname as St_First_Name,s.slname as St_Last_Name,s.sdob as St_Date_Of_Birth,
		sc.cid as Course_ID, c.ctitle as Title, d.dname as Stream, l.lname as Type
		from studentsPerCourse as sc, student as s, course as c, direction as d, length as l
		where c.cstream = d.did and c.ctype = l.lid and sc.sid = s.sid and sc.cid = c.cid
		order by ID;
    
-- all trainers per course


select tc.tcid as ID, tc.tid as Trainer_ID,t.tfname as Tr_First_Name, t.tlname as Tr_Last_Name, pname as Subject,
c.cid as Course_ID, c.ctitle as Title, d.dname as Stream, l.lname as Type
from trainerspercourse as tc, trainer as t, course as c, direction as d, length as l, profession as p
where tc.cid = c.cid and tc.tid = t.tid and c.cstream = p.pid and c.ctype = l.lid and t.tsubject = d.did
order by ID;

-- all assignments per course

select  ac.acid as ID, ac.aid as Assignment_ID, h.hname as Title, des.dename as Description ,sub.subdate as Submission_Date,
ac.cid as Course_ID, c.ctitle as Title, d.dname as Stream, l.lname as Type
from assignmentspercourse as ac, assignment as a, course as c, direction as d, length as l,description as des, headline as h, submission as sub
where ac.cid = c.cid and ac.aid = a.aid and c.cstream = d.did and c.ctype = l.lid and a.atitle = h.hid 
and a.adescription = des.deid and a.asubtime = sub.subid
order by ID;

-- all assignmets per course per student


select sa.said as ID, s.sid as St_ID, s.sfname as St_First_Name, s.slname as St_Last_Name, s.sdob as St_Date_Of_Birth,
a.aid as Ass_ID,h.hname as Ass_Title,des.dename as Ass_Description,sub.subdate as Ass_Submission_Date,
ac.cid as Course_ID, c.ctitle as C_Title, dir.dname as C_Stream, l.lname as C_Type, sa.samark as Total_Mark

from student as s,assignment as a, assignmentsPerCourse as ac, studentassignments as sa,course as c,headline as h, description as des,
submission as sub, direction as dir, length as l

where s.sid = sa.sid and ac.acid = sa.acid  and ac.cid = c.cid and a.aid = ac.aid  and a.atitle = h.hid 
and a.adescription = des.deid and a.asubtime = sub.subid and dir.did = c.cstream and l.lid = c.ctype
order by s.sid;




-- print all students that belong to more than one courses
	select sc.sid as Student_ID,s.sfname as St_First_Name,s.slname as St_Last_Name,s.sdob as St_Date_Of_Birth
		from studentsPerCourse as sc, student as s, course as c, direction as d, length as l
		where c.cstream = d.did and c.ctype = l.lid and sc.sid = s.sid and sc.cid = c.cid 
        group by sc.sid having count(*) > 1
		order by sc.sid;

	