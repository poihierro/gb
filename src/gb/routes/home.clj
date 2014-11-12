(ns gb.routes.home
  (:require [compojure.core :refer :all]
            [gb.views.layout :as layout]
            [hiccup.form :refer :all]))

(defn home [& [name message error]]
  (layout/common
    [:h1 "Guestbook"]
    [:p "Welcome to my Guestbook"]
    [:p error]
    (show-guests)
    [:hr]
    ;; create a form with text fields called "name" and "message"
    ;; this will be sent when the for posts to the server
    (form-to [:post "/"]
    [:p "Name"]
    (text-field "name" name)
    [:p "Message"]
    (text-area {:rows 10 :cols 40} "message" message)
    [:br]
    (submit-button "comment"))))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "howdy" :name "bob" :timestamp nil}
          {:message "hello" :name "kevin" :timestamp nil}]]
     [:li
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestamp]])])

(defn save-message [name message]
  (cond
    (empty? name)
    (home name message "someone forgot to leave a name")
    (empty? message)
    (home name message "missing message entry")
    :else
    (do
      (println name message)
      (home))))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [name message] (save-message name message)))
