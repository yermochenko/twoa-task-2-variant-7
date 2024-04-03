DROP TABLE IF EXISTS "task";
DROP TABLE IF EXISTS "user";

CREATE TABLE "user" (
	"id"          SERIAL PRIMARY KEY,
	"first_name"  TEXT   NOT NULL,
	"middle_name" TEXT,
	"last_name"   TEXT   NOT NULL,
	"birthday"    DATE   NOT NULL,
	"login"       TEXT   NOT NULL,
	"password"    TEXT   NOT NULL
);

CREATE TABLE "task" (
	"id"       SERIAL PRIMARY KEY,
	"name"     TEXT   NOT NULL,
	"created"  DATE   NOT NULL DEFAULT now()
);

INSERT INTO "user"
-------------------------------------------------------------------------------------------
("id", "first_name", "middle_name", "last_name", "birthday"  , "login" , "password") VALUES
-------------------------------------------------------------------------------------------
(1   , 'Иван'      , 'Иванович'   , 'Иванов'   , '2003-02-01', 'ivanov', '12345'   );
SELECT setval('user_id_seq', 1);

INSERT INTO "task"
-------------------------------------------------
("id", "name"              , "created"   ) VALUES
-------------------------------------------------
(1   , 'Простой случай'    , '2024-01-10'),
(2   , 'Тривиальный случай', '2024-01-15'),
(3   , 'Особый случай'     , '2024-01-13'),
(4   , 'Усложнённый случай', '2024-01-12');
SELECT setval('task_id_seq', 4);