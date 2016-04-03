(ns testwebsite.handler
  (:require [testwebsite.views :as views]
            [testwebsite.db :as db]
            [compojure.core :as cc]
            [compojure.route :as route]
            [ring.middleware.resource :as resource]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :as resp]
            ))

(defn add-todo [params]
  (let [text (get params "text")
        id (db/create-todo text :todo)]
    (resp/redirect "/")))

(cc/defroutes app-routes
              (cc/GET "/" [] (views/home-page))
              (cc/POST "/add-todo" {params :params} (add-todo params))
              (cc/GET "/home" [] (slurp "resources/templates/home.html"))
              (cc/GET "/about" [] (slurp "resources/templates/about.html"))
              (route/not-found "Not Found"))

(def app
  (-> app-routes
      (resource/wrap-resource "public")
      (wrap-params)))
