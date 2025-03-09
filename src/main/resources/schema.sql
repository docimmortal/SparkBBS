CREATE TABLE Authorities(
	id bigint NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	authority VARCHAR(45) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE Users(
	id bigint NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(256) NOT NULL,
	enabled tinyint NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE User_Details(
	id bigint NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
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
	user_details_id bigint NOT NULL,
	message_forum_id bigint NOT NULL,
	timestamp TIMESTAMP,
	PRIMARY KEY(id),
	foreign key(user_details_id) references User_Details(id),
	foreign key(message_forum_id) references Message_Forums(id)
);

CREATE TABLE Last_Read_Messages(
	id bigint NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	user_details_id bigint NOT NULL,
	message_forum_id bigint NOT NULL,
	message_id bigint NOT NULL,
	foreign key(user_details_id) references User_Details(id),
	foreign key(message_forum_id) references Message_Forums(id),
	foreign key(message_id) references Messages(id)
);
