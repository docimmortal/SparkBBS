CREATE TABLE Users(
	username VARCHAR(50) NOT NULL,
	password VARCHAR(256) NOT NULL,
	enabled tinyint NOT NULL,
	PRIMARY KEY(username)
);

CREATE TABLE Authorities(
	username VARCHAR(50) unique NOT NULL,
	authority VARCHAR(50) NOT NULL,
	foreign key (username) references Users(username)
);

create unique index ix_auth_username on authorities (username,authority);

create table persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

CREATE TABLE BBS_User_Details(
	id bigint NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL,
	door_id VARCHAR(60) NOT NULL,
	first_name VARCHAR(60) NOT NULL,
	last_name VARCHAR(60) NOT NULL,
	email VARCHAR(120) NOT NULL,
	photo BLOB,
	last_login TIMESTAMP,
	PRIMARY KEY(id)
);

CREATE TABLE Message_Forums(
	id bigint NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(1000) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE Messages(
	id bigint NOT NULL AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	message CLOB,
	bbs_user_details_id bigint NOT NULL,
	message_forum_id bigint NOT NULL,
	timestamp TIMESTAMP,
	PRIMARY KEY(id),
	foreign key(bbs_user_details_id) references BBS_User_Details(id),
	foreign key(message_forum_id) references Message_Forums(id)
);

CREATE TABLE Last_Read_Messages(
	id bigint NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	bbs_user_details_id bigint NOT NULL,
	message_forum_id bigint NOT NULL,
	message_id bigint NOT NULL,
	foreign key(bbs_user_details_id) references BBS_User_Details(id),
	foreign key(message_forum_id) references Message_Forums(id),
	foreign key(message_id) references Messages(id)
);

CREATE TABLE Email_Validations(
	id bigint NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	email VARCHAR(120) NOT NULL,
	code_key VARCHAR(10),
	date_sent TIMESTAMP,
	validated tinyint NOT NULL
);