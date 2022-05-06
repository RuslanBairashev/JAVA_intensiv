create table if not exists usr (
    user_id serial primary key,
    login varchar,
    password varchar
);

create table if not exists chatroom (
    chatroom_id serial primary key,
    name varchar,
    owner int,
    CONSTRAINT owner FOREIGN KEY (owner)
    REFERENCES usr(user_id)
);

create table if not exists message (
    message_id serial primary key,
    author int,
    room int,
    text varchar,
    date date,
    time time,
    CONSTRAINT author FOREIGN KEY (author)
    REFERENCES usr(user_id),
    CONSTRAINT room FOREIGN KEY (room)
    REFERENCES chatroom(chatroom_id)
);

