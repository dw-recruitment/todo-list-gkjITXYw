(ns testwebsite.db
  (:require [clojure.java.jdbc :as sql]
            [clojure.string :as string]
            ))

(let [db-host "localhost"
      db-port 5432
      db-name "test1"]
  (def db {:classname   "org.postgresql.Driver"             ; must be in classpath
           :subprotocol "postgresql"
           :subname     (str "//" db-host ":" db-port "/" db-name)
           :user        "postgres"
           :password    "postgres"}))

(defn select-todos-from-db []
  (doall
    (sql/query db ["SELECT text, doneness FROM todo;"])))
