(ns gb.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [gb.models.db :as db]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [gb.routes.home :refer [home-routes]]))

(defn init []
  (println "gb is starting"))
  (if-not (.exists (java.io.File. "./db.sq3"))
    (db/create-gb-table))

(defn destroy []
  (println "gb is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes app-routes)
      (handler/site)
      (wrap-base-url)))
