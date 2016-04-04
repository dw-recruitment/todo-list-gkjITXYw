drop table todo;
CREATE TABLE todo (
    todo_id serial PRIMARY KEY,
    name varchar (50) NOT NULL,
    text varchar (50) NOT NULL,
    doneness varchar (25) NOT NULL
);
INSERT INTO todo (name, text, doneness) VALUES ('TODO List', 'Create a Clojure project', ':done');
INSERT INTO todo (name, text, doneness) VALUES ('TODO List', 'Draw animated gif', ':done');
INSERT INTO todo (name, text, doneness) VALUES ('TODO List', 'Create an HTTP endpoint for /', ':done');
INSERT INTO todo (name, text, doneness) VALUES ('TODO List', 'Finish creating database', ':todo');
INSERT INTO todo (name, text, doneness) VALUES ('TODO List', 'Display Todos', ':todo');

INSERT INTO todo (name, text, doneness) VALUES ('Shopping List', 'Eggs', ':todo');
INSERT INTO todo (name, text, doneness) VALUES ('Shopping List', 'Butter', ':todo');
INSERT INTO todo (name, text, doneness) VALUES ('Shopping List', 'Beets', ':todo');

--UPDATE todo SET doneness=':todo' WHERE todo_id = 3

--SELECT todo_id, name, text, doneness FROM todo order by name;

--select distinct(name) from todo order by name;