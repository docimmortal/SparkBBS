INSERT INTO Users (username, password, enabled) VALUES ('Bob', '{bcrypt}$2a$10$7hssGCuqK0oYxQFMXiL8fOljpIFaKVRrK1F0E/HNb2IlIPabv18NO', '1');
INSERT INTO Authorities (username, authority) VALUES ('Bob','ROLE_USER');
INSERT INTO Users (username, password, enabled) VALUES ('Joe Cool', '{bcrypt}$2a$10$VDNg03PY4cxlHy0KDculyu3Wb1z.c3z6/qGhfRgxXJJ9rEClIqhU2', '1');
INSERT INTO Authorities (username, authority) VALUES ('Joe Cool','ROLE_ADMIN');
INSERT INTO Users (username, password, enabled) VALUES ('Mikey', '{bcrypt}$2a$10$WB8nff.hE.SuNR0QKqNYper2adKCh5gJ5eZ4.yT1GWZybTA1GJL3a', '1');
INSERT INTO Authorities (username, authority) VALUES ('Mikey','ROLE_USER');
INSERT INTO Users (username, password, enabled) VALUES ('SuzieQ', '{bcrypt}$2a$10$hvU35yI51haSD1lQkAMFm.p0X94JSRj5I1LoaUQLJKniQmIymSt9i', '1');
INSERT INTO Authorities (username, authority) VALUES ('SuzieQ','ROLE_USER');

INSERT INTO BBS_User_Details (username, door_id, first_name, last_name, email, photo, last_login) values 
	('Bob','$2a$10$THG95h3cpE0U0kZJT5Z/Lu6e/CJbmG.ieOjskApgCKcYegRN6Tp2O','Bob','Smith','bob.smith@email.com', FILE_READ('classpath:/static/images/bob.jpg'), current_timestamp());
INSERT INTO BBS_User_Details (username, door_id, first_name, last_name, email, photo, last_login) values 
	('Joe Cool','$2a$10$pR5VxQAnxZzq7HYqJlOBcOWlyuUeUInj3DS/G5EPmyL89q7wZwgga','Joe','King','joe@king.com', FILE_READ('classpath:/static/images/joe.jpg'), current_timestamp());
INSERT INTO BBS_User_Details (username, door_id, first_name, last_name, email, photo, last_login) values 
	('Mikey','$2a$10$2uan15OyQUe5ceioFc4GzuIr94h93ed6WNTi/FTs.bJfViVWh2VRK','Mike','Rowave','mike@rowave.com', FILE_READ('classpath:/static/images/none.jpg'), current_timestamp());
INSERT INTO BBS_User_Details (username, door_id, first_name, last_name, email, photo, last_login) values 
	('SuzieQ','$2a$10$Y/v6gNeJql0lS4Zf7i1FH.fOuBjNLKEpNmPoZ5sEdxZLwc1v.mTTi','Suzie','Quinn','suziequinn@whatever.com', FILE_READ('classpath:/static/images/none.jpg'), current_timestamp());
	
INSERT INTO Message_Forums(name, description) values ('General','Catch-all everyday chat.');
INSERT INTO Message_Forums(name, description) values ('Get Creative!','Share your art!');
INSERT INTO Message_Forums(name, description) values ('Humor','Funny stuff!');
INSERT INTO Message_Forums(name, description) values ('Cats','Take a short paws here.');

INSERT INTO Youtube_Videos(video_embed_endpoint) values ('a_iij12svL0');

INSERT INTO Reactions(bbs_user_details_id,reaction_type) values(2,'FUNNY');
INSERT INTO Reactions(bbs_user_details_id,reaction_type) values(3,'FUNNY');
INSERT INTO Reactions(bbs_user_details_id,reaction_type) values(4,'LOVE');

INSERT INTO Messages(title, message, timestamp, bbs_user_details_id, message_forum_id) values 
	('Hello!',CAST('This is a test message.' AS CLOB),'2025-01-01 09:01:22',1,1);
INSERT INTO Messages(title, message, timestamp, bbs_user_details_id, message_forum_id) values 
	('Test 2',CAST('This is another test message.' AS CLOB),'2025-01-03 13:05:12',2,1);
INSERT INTO Messages(title, message, timestamp, bbs_user_details_id, message_forum_id, youtube_video_id) values 
	('Can you guess the video games?',CAST('The hillarious Bec Hill!!!' AS CLOB),'2025-01-01 09:20:34',1,3,1);
INSERT INTO Messages(title, message, timestamp, bbs_user_details_id, message_forum_id) values 
	('Test 3',CAST('This message is out of order.' AS CLOB),'2025-01-05 11:08:02',1,1);

-- First set of reactions are for message 3
UPDATE Reactions SET message_id=3 WHERE id<4;

INSERT INTO Last_Read_Messages(bbs_user_details_id, message_forum_id , message_id) values (1,1,1);