create Table shares (
 id integer primary key,
 userid varchar(20),
 name varchar(20),
 title varchar(50),
 text varchar(1000),
 unique_word varchar(200),
 delete_flg boolean,
 created timestamp,
 updated timestamp
);


INSERT INTO shares 
VALUES(
	1,
	'test',
	'テスト君',
	'sample',
	'sample',
	'Java,アルゴリズム',
	false,
	null,
	null
);