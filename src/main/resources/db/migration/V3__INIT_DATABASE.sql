-- account 表 id 使用uuid 类型，需要先添加uuid-ossp模块。
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE site_issue.issue_detail (
    ID INT PRIMARY KEY     NOT NULL,
	site_id INTEGER,
	issue_key TEXT COLLATE pg_catalog."default",
	alert_type TEXT COLLATE pg_catalog."default",
	"alert_resolution " TEXT COLLATE pg_catalog."default",
	"duration_start " DATE,
	"duration_end " DATE,
	org_id INTEGER
);
CREATE TABLE myschema.COMPANY(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL,
   JOIN_DATE      DATE
);
INSERT INTO myschema.COMPANY (ID,NAME,AGE,ADDRESS,SALARY,JOIN_DATE) VALUES (1, 'Paul', 32, 'California', 20000.00,'2001-07-13');
INSERT INTO myschema.COMPANY (ID,NAME,AGE,ADDRESS,JOIN_DATE) VALUES (2, 'Allen', 25, 'Texas', '2007-12-13');
INSERT INTO myschema.COMPANY (ID,NAME,AGE,ADDRESS,SALARY,JOIN_DATE) VALUES (3, 'Teddy', 23, 'Norway', 20000.00, DEFAULT );
INSERT INTO myschema.COMPANY (ID,NAME,AGE,ADDRESS,SALARY,JOIN_DATE) VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00, '2007-12-13' ),
(5, 'David', 27, 'Texas', 85000.00, '2007-12-13');

CREATE TABLE "myschema"."test_jsonb"(
"testid" varchar(64),
"testjson" jsonb
)
WITH (OIDS=FALSE);

INSERT INTO myschema.test_jsonb VALUES (uuid_generate_v1(), '{"personId":"123","name":"李四","age":"18","createTime":"2019-10-31 17:30:30"}');
INSERT INTO myschema.test_jsonb VALUES (uuid_generate_v1(), '{"personId":"125","name":"王五","age":"20","createTime":"2019-10-31 17:31:33"}');