(ns gb.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))

(def db {:classname  "org.sqlite.JDBC",
         :subprotocol   "sqlite",
         :subname       "db.sq3"})

(defn create-gb-table []
  (sql/with-connection
    db
    (sql/create-table
      :gb
      [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
      [:name "TEXT"]
      [:message "TEXT"])
    (sql/do-commands "CREATE INDEX timestamp_index ON gb (timestamp)")))

(defn read-guests []
  (sql/with-connection
    db
    (sql/with-query-results res
      ["SELECT * FROM gb ORDER BY timestamp DESC"]
      (doall res))))

(defn save-message [name message]
  (sql/with-connection
    db
    (sql/insert-values
      :gb
      [:name :message :timestamp]
      [name message (new java.util.Date)])))