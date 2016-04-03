(defproject testwebsite "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.5.0"]
                 [ring/ring-defaults "0.2.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [postgresql "9.3-1102.jdbc41"]
                 ]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler testwebsite.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
