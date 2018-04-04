SET SQL_SAFE_UPDATES = 0;
create table ms_users (user_id int, user_name varchar(25), email varchar(30), phone_no varchar(15));
alter table ms_users add primary key(user_id);
alter table ms_users modify user_id int not null auto_increment;

create table ms_orders (order_id int not null auto_increment primary key, user_id int, item_id int, quantity int);

create table ms_items (item_id int not null auto_increment primary key, item_name varchar(20), price double, description varchar(50), quantity int);


insert into ms_users(user_name, email, phone_no) values('Prabhash','kumar.anand@kpit.com', '8553186276');
insert into ms_users(user_name, email, phone_no) values('Akash','akash.mirani@kpit.com', '8149322393');

insert into ms_items(item_name, price, description, quantity) values ('iPhone X', 90000.0, 'Apple iPhone X 128GB', 12);
insert into ms_items(item_name, price, description, quantity) values ('Galaxy S8', 70000.0, 'Samsung Galaxy S8 64GB', 16);
insert into ms_items(item_name, price, description, quantity) values ('1+ 5T', 50000.0, 'One plus 5T 128GB', 24);
insert into ms_items(item_name, price, description, quantity) values ('MacBook Pro', 60000.0, 'MacBook Pro 512 GB 8GB i5', 8);

insert into ms_orders(user_id, item_id, quantity) values (1, 6, 1);
insert into ms_orders(user_id, item_id, quantity) values (1, 8, 2);
insert into ms_orders(user_id, item_id, quantity) values (2, 5, 3);
insert into ms_orders(user_id, item_id, quantity) values (2, 8, 1);