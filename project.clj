(defproject gb "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 ;;jdbc dependencies
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]]
  ;; add this one as im getting error since the classpath is different?
  ;; http://stackoverflow.com/questions/6770342/clojure-lein-task-cannot-find-jdbc-even-though-application-can-find-it-fine
  :dev-dependencies [[[org.clojure/java.jdbc "0.2.3"]]]

  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler gb.handler/app
         :init gb.handler/init
         :destroy gb.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
