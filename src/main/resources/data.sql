INSERT INTO Authorities (username, authority) VALUES ('Bob','ROLE_USER');
INSERT INTO Users (username, password, enabled) VALUES ('Bob', '{bcrypt}$2a$10$7hssGCuqK0oYxQFMXiL8fOljpIFaKVRrK1F0E/HNb2IlIPabv18NO', '1');
INSERT INTO Authorities (username, authority) VALUES ('Joe Cool','ROLE_ADMIN');
INSERT INTO Users (username, password, enabled) VALUES ('Joe Cool', '{bcrypt}$2a$10$VDNg03PY4cxlHy0KDculyu3Wb1z.c3z6/qGhfRgxXJJ9rEClIqhU2', '1');


INSERT INTO User_Details (username, first_name, last_name, email, photo, last_login) values 
	('Bob','Bob','Smith','bob.smith@email.com', FILE_READ('classpath:/static/images/bob.jpg'), current_timestamp());
INSERT INTO User_Details (username, first_name, last_name, email, photo, last_login) values 
	('Joe Cool','Joe','King','joe@king.com', FILE_READ('classpath:/static/images/joe.jpg'), current_timestamp());
	
INSERT INTO Message_Forums(name, description) values ('General','Catch-all everyday chat.');
INSERT INTO Message_Forums(name, description) values ('Get Creative!','Share your art!');
INSERT INTO Message_Forums(name, description) values ('Humor','Funny stuff!');
INSERT INTO Message_Forums(name, description) values ('Cats','Take a short paws here.');

INSERT INTO Messages(title, message, timestamp, user_details_id, message_forum_id) values 
	('Hello!',CAST('This is a test message.' AS CLOB),'2025-01-01 09:01:22',1,1);
INSERT INTO Messages(title, message, timestamp, user_details_id, message_forum_id) values 
	('Test 2',CAST('This is another test message.' AS CLOB),'2025-01-03 13:05:12',2,1);
INSERT INTO Messages(title, message, timestamp, user_details_id, message_forum_id) values 
	('Ha ha!',CAST('This is another new message.' AS CLOB),'2025-01-01 09:20:34',1,3);
INSERT INTO Messages(title, message, timestamp, user_details_id, message_forum_id) values 
	('Test 3',CAST('This message is out of order.' AS CLOB),'2025-01-05 11:08:02',1,1);
	
INSERT INTO Last_Read_Messages(user_details_id, message_forum_id , message_id) values (1,1,1);