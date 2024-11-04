create table uam (
uid VARCHAR(100) --combine username and UID, calculate value and insert into field - create user can not result in same user being created with same password,
username VARCHAR(50),
password VARCHAR(100),
usr_role varchar(50),
admin_flg smallint,
last_login date,
acc_lock_ind smallint
);

insert into uam values('am1jbGF1Z2hpbmZvcm1hdGljYTIwMTkh','jmclaugh','aW5mb3JtYXRpY2EyMDE5IQ==','admin',1,DATE() ,0);

