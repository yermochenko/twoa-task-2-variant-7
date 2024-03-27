DROP TABLE IF EXISTS "task";

CREATE TABLE "task" (
	"id"      SERIAL PRIMARY KEY,
	"name"    TEXT   NOT NULL,
	"created" DATE   NOT NULL DEFAULT now()
);

INSERT INTO "task"
-------------------------------------------------
("id", "name"              , "created"   ) VALUES
-------------------------------------------------
(1   , 'Простой случай'    , '2024-01-10'),
(2   , 'Тривиальный случай', '2024-01-12'),
(3   , 'Особый случай'     , '2024-01-13'),
(4   , 'Усложнённый случай', '2024-01-15');
SELECT setval('task_id_seq', 4);