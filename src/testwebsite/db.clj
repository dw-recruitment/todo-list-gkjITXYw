(ns testwebsite.db
  (:require [clojure.java.jdbc :as sql]))

(let [db-host "localhost"
      db-port 5432
      db-name "test1"]
  (def db {:classname   "org.postgresql.Driver"             ; must be in classpath
           :subprotocol "postgresql"
           :subname     (str "//" db-host ":" db-port "/" db-name)
           :user        "postgres"
           :password    "postgres"}))

(defn convert-id->int [id]
  (Integer/parseInt id))

(defn select-names-from-db []
  (doall
    (sql/query db ["SELECT distinct(name) FROM todo ORDER BY name;"])))

(defn select-todos-from-db-by-name [name]
  (doall
    (sql/query db [(str "SELECT todo_id, name, text, doneness FROM todo WHERE name='" name "' order by todo_id;")])))

(defn create-todo [name text doneness]
  (sql/insert! db :todo {:name (clojure.string/replace name #"'" "") :text (clojure.string/replace text #"'" "") :doneness (str doneness)}))

(defn update-todo [id doneness]
  (sql/db-do-commands db true (str "UPDATE todo SET doneness='" doneness "' WHERE todo_id = " (convert-id->int id))))

(defn delete-todo [id]
  (sql/delete! db :todo ["todo_id = ?" (convert-id->int id)]))
