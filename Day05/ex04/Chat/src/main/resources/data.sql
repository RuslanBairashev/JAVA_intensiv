delete from usr where login in('Ann','Bob','Tom', 'Sam', 'Don');
insert into usr (login, password) values('Ann','123');
insert into usr (login, password) values('Bob','123');
insert into usr (login, password) values('Tom','123');
insert into usr (login, password) values('Sam','123');
insert into usr (login, password) values('Don','123');

delete from chatroom where name in('room1','room2','room3', 'room4', 'room5');
insert into chatroom (name, owner) values('room1', 1);
insert into chatroom (name, owner) values('room2', 2);
insert into chatroom (name, owner) values('room3', 3);
insert into chatroom (name, owner) values('room4', 4);
insert into chatroom (name, owner) values('room5', 5);

delete from message where message_id in(1,2,3,4,5);
insert into message (author, room, text, date_time) values(1,1,'test1', current_timestamp);
insert into message (author, room, text, date_time) values(2,2,'test1', current_timestamp);
insert into message (author, room, text, date_time) values(3,3,'test1', current_timestamp);
insert into message (author, room, text, date_time) values(4,4,'test2', current_timestamp);
insert into message (author, room, text, date_time) values(5,5,'test2', current_timestamp);




