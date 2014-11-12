(ns gb.routes.home
  (:require [compojure.core :refer :all]
            [gb.views.layout :as layout]
            [hiccup.form :refer :all]))

(defn home [& [name message error]]
  (layout/common
    [:h1 "Guestbook"]
    [:p "Welcome to my Guestbook"]
    [:hr]
    [:form]
    [:p "Name"]
    [:input]
    [:p "Message"]
    [:textarea {:rows 10 :cols 40}]))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "howdy" :name "bob" :timestamp nil}
          {:message "hello" :name "kevin" :timestamp nil}]]
     [:li
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestamp]])])

(defroutes home-routes
  (GET "/" [] (home)))
