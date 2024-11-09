/*ALTER TABLE `uam` RENAME TO `uam_old`*/

DROP TABLE uam;

create table uam (
id INTEGER PRIMARY KEY,
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