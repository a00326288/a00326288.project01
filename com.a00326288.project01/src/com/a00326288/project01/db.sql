/*ALTER TABLE `uam` RENAME TO `uam_old`*/

DROP TABLE uam;

create table uam (
user_id INTEGER PRIMARY KEY,
uid VARCHAR(100), --combine username and UID, calculate value and insert into field - create user can not result in same user being created with same password,
username VARCHAR(50),
password VARCHAR(100),
usr_role varchar(50),
admin_flg smallint,
last_login date,
acc_lock_ind smallint
);

insert into uam (uid,username,password,usr_role,admin_flg,last_login,acc_lock_ind) VALUES ('am1jbGF1Z2hpbmZvcm1hdGljYTIwMTkh','jmclaugh','aW5mb3JtYXRpY2EyMDE5IQ==','admin',1,DATE(),0);

INSERT INTO uam
(uid, username, password, usr_role, admin_flg, last_login, acc_lock_ind) VALUES('am1jbGF1Z2hsaW4zMSFpbmZvcm1hdGlhMjAzOSE=', 'jmclaughlin31!', 'aW5mb3JtYXRpYTIwMzkh', '', 0, '', 0);

 
SELECT * FROM uam;


DROP TABLE events;

CREATE TABLE events(
event_id INTEGER PRIMARY KEY,
event_name VARCHAR(100),
event_description VARCHAR(500),
event_start_date DATE,
event_end_date DATE
);

INSERT INTO events (event_name, event_description, event_start_date, event_end_date) SELECT 'Snow White', 'Snow white and 7 dwarfs is a classic', '15/07/2023', '20/09/2023';

INSERT INTO events (event_name, event_description, event_start_date, event_end_date) SELECT 'Lion King', 'Follow Simba on his epic adventure', '27/09/2023', '20/11/2023';

SELECT * FROM events;


DROP TABLE bookings;

DELETE FROM bookings;

CREATE TABLE bookings (
booking_id INTEGER PRIMARY KEY,
booking_date date,
venue_id integer,
event_id integer,
user_id integer
);

INSERT INTO bookings (booking_date,venue_id,event_id,user_id) VALUES('30/08/2023',1,2,1);

SELECT * FROM bookings;

DROP TABLE venue;

CREATE TABLE venue (
venue_id integer PRIMARY KEY,
venue_name varchar(100),
venue_address varchar(200),
city varchar(100)
)

SELECT * FROM venue;

INSERT INTO VENUE (venue_name, venue_address,city) values('Bord Gáis Energy Theatre','Bord Gáis Energy Theatre Grand Canal Square Docklands Dublin 2 D02 PA03 Ireland','Dublin' );
INSERT INTO VENUE (venue_name, venue_address,city) values('Gaiety Theatre','King St South, Dublin 2','Dublin' );
INSERT INTO VENUE (venue_name, venue_address,city) values('Grand Opera House','2-4 Great Victoria St, Belfast BT2 7HR','Belfast' );
INSERT INTO VENUE (venue_name, venue_address,city) values('Town Hall Theatre','393 (Main Auditorium); 52 (Studio Space)','Galway' );




SELECT a.username, b.booking_id, e.event_name,v.venue_name FROM uam a INNER JOIN bookings b ON a.user_id = b.user_id INNER JOIN events e ON b.event_id = e.event_id INNER JOIN venue v ON b.venue_id = v.venue_id ;
