CREATE TABLE Authorities(
	id int NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	authority VARCHAR(45) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE Users(
	id int NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(256) NOT NULL,
	enabled tinyint NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE details(
	did int NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	first_name VARCHAR(60) NOT NULL,
	last_name VARCHAR(60) NOT NULL,
	email VARCHAR(120) NOT NULL,
	photo BLOB,
	PRIMARY KEY(did)
);

CREATE TABLE messages(
	mid int NOT NULL AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	message CLOB,
	PRIMARY KEY(mid)
);

CREATE TABLE message_forums(
	mfid int NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(1000) NOT NULL,
	PRIMARY KEY(mfid)
);

CREATE TABLE details_messages(
	dpk INT NOT NULL,
	mpk INT NOT NULL,
	foreign key(dpk) references details(did),
	foreign key(mpk) references messages(mid)
);

CREATE TABLE message_forum_messages(
	mfpk INT NOT NULL,
	mpk INT NOT NULL,
	foreign key(mfpk) references message_forums(mfid),
	foreign key(mpk) references messages(mid)
);
	