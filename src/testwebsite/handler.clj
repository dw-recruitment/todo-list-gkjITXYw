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

(defn complete-todo [id]
  (db/update-todo id ":done")
  (resp/redirect "/"))

(defn undo-todo [id]
  (db/update-todo id ":todo")
  (resp/redirect "/"))

(defn delete-todo [id]
  (db/delete-todo id)
  (resp/redirect "/"))

(defn add-todo [params]
  (let [text (get params "text")
        id (db/create-todo text :todo)]
    (resp/redirect "/")))

(cc/defroutes app-routes
              (cc/GET "/" [] (views/home-page))
              (cc/GET "/admin/:id/complete" [id] (complete-todo id))
              (cc/GET "/admin/:id/undo" [id] (undo-todo id))
              (cc/GET "/admin/:id/delete" [id] (delete-todo id))
              (cc/POST "/add-todo" {params :params} (add-todo params))
              (cc/GET "/home" [] (slurp "resources/templates/home.html"))
              (cc/GET "/about" [] (slurp "resources/templates/about.html"))
              (route/not-found "Not Found"))

(def app
  (-> app-routes
      (resource/wrap-resource "public")
      (wrap-params)))
