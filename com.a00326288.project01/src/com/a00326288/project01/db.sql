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


CREATE TABLE events_x_venue_x_price
(
event_id integer,
venue_id integer,
price_id integer
)

INSERT INTO events_x_venue_x_price values(1,2,2);

SELECT * FROM events_x_venue_x_price

DROP TABLE events;

CREATE TABLE events(
event_id INTEGER,
event_name VARCHAR(100),
event_description VARCHAR(500),
event_start_date DATE,
event_end_date DATE
);

INSERT INTO events (event_id,event_name, event_description, event_start_date, event_end_date) SELECT 1,'Snow White', 'Snow white and 7 dwarfs is a classic', '15/07/2023', '20/09/2023';

INSERT INTO events (event_id,event_name, event_description, event_start_date, event_end_date) SELECT 2,'Lion King', 'Follow Simba on his epic adventure', '27/09/2023', '20/11/2023';

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

DROP TABLE venues;

CREATE TABLE venues (
venue_id integer ,
venue_name varchar(100),
venue_address varchar(200),
city varchar(100)
)

DELETE FROM venues;

SELECT * FROM venues;

INSERT INTO venues (venue_id,venue_name, venue_address,city) values(1,'Bord Gáis Energy Theatre','Bord Gáis Energy Theatre Grand Canal Square Docklands Dublin 2 D02 PA03 Ireland','Dublin' );
INSERT INTO venues (venue_id,venue_name, venue_address,city) values(2,'Gaiety Theatre','King St South, Dublin 2','Dublin');
INSERT INTO venues (venue_id,venue_name, venue_address,city) values(3,'Grand Opera House','2-4 Great Victoria St, Belfast BT2 7HR','Belfast');
INSERT INTO venues (venue_id,venue_name, venue_address,city) values(4,'Town Hall Theatre','393 (Main Auditorium); 52 (Studio Space)','Galway');





SELECT a.event_id,a.event_name,a.event_description,a.event_start_date,a.event_end_date,b.venue_id,c.venue_name,c.venue_address,c.city,d.price_id, d.price FROM events a inner JOIN events_x_venue_x_price b on a.event_id=b.event_id INNER JOIN venues c ON b.venue_id = c.venue_id INNER JOIN prices d ON b.price_id = d.price_id where a.event_id=1;
     
DROP TABLE prices;

CREATE TABLE prices(
price_id INTEGER,
price integer
)



INSERT INTO prices values(2,150)


SELECT * FROM events;
SELECT * FROM venues;
SELECT * FROM prices;

