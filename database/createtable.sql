--drop table todo;
--CREATE TABLE todo (
--    todo_id serial PRIMARY KEY,
--    text varchar (50) NOT NULL,
--    doneness varchar (25) NOT NULL
--);
--INSERT INTO todo (text, doneness) VALUES ('Create a Clojure project', ':done');
--INSERT INTO todo (text, doneness) VALUES ('Draw animated gif', ':done');
--INSERT INTO todo (text, doneness) VALUES ('Create an HTTP endpoint for /', ':done');
--INSERT INTO todo (text, doneness) VALUES ('Finish creating database', ':todo');
--INSERT INTO todo (text, doneness) VALUES ('Display Todos', ':todo');

--UPDATE todo SET doneness=':todo' WHERE todo_id = 3

SELECT todo_id, text, doneness FROM todo order by todo_id;