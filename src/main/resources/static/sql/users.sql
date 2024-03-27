create Table users (
 id integer primary key,
 userid varchar(20),
 password varchar(20),
 name varchar(20),
 sex integer,
 mail varchar(30),
 age integer,
 text varchar(200),
 unique_word varchar(200),
 image varchar(200),
 delete_flg boolean,
 created timestamp,
 updated timestamp
);

INSERT INTO users
VALUES(
	1,
	'test',
	'test',
	'テスト君',
	1,
	'email@test2.com',
	22,
	'test', 
	'Java',
	false,
	null,
	null
);