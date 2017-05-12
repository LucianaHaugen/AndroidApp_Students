PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE students(id integer primary key not null, name text not null);
INSERT INTO "students" VALUES(1,'Luciana Haugen');
INSERT INTO "students" VALUES(2,'Jimmy-Cannon Nwadinkpa');
INSERT INTO "students" VALUES(3,'Niklas Gustafsson');
INSERT INTO "students" VALUES(4,'Daniel Jonsson');
INSERT INTO "students" VALUES(5,'Erik Gabrielsson');
INSERT INTO "students" VALUES(6,'Oliver Persson');
INSERT INTO "students" VALUES(7,'Giogio Di Feola');
INSERT INTO "students" VALUES(8,'John Claeson');
INSERT INTO "students" VALUES(9,'Poja Mofakheri');
INSERT INTO "students" VALUES(10,'Parviz Mozaffari');
COMMIT;


BEGIN TRANSACTION;
CREATE TABLE courses(id integer primary key asc,course_code varchar(12));
INSERT INTO "courses" VALUES(1,'Java');
INSERT INTO "courses" VALUES(2,'DaBA');
INSERT INTO "courses" VALUES(3,'ObjPro');
INSERT INTO "courses" VALUES(4,'KliPro');
INSERT INTO "courses" VALUES(5,'UtvVerk');
INSERT INTO "courses" VALUES(6,'ServPro');
INSERT INTO "courses" VALUES(7,'SysInt');
INSERT INTO "courses" VALUES(8,'Komm');
INSERT INTO "courses" VALUES(9,'LIA1');
INSERT INTO "courses" VALUES(10,'LIA2');
COMMIT;
