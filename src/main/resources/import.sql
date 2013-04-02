-- Insert samples into the database(HSQL). 

insert into appuser (account, password, fullName, email) values ('admin', '1234', 'Admin', 'admin@your.com');
insert into appuser (account, password, fullName, email) values ('zkoss', '1234', 'ZKOSS', 'info@zkoss.org');


insert into apptodo (subject, priority, complete) values ('Buy some milk', 2, 0);
insert into apptodo (subject, priority, complete) values ('Gift for Dennis', 1, 0);
insert into apptodo (subject, priority, complete) values ('Pay credit-card bill', 0, 0);
