(ns testwebsite.handler
  (:require [testwebsite.views :as views]
            [compojure.core :as cc]
            [compojure.route :as route]
            [ring.middleware.resource :as resource]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            ))



(cc/defroutes app-routes
              (cc/GET "/" [] (slurp "resources/templates/home.html"))
              (route/not-found "Not Found"))

(def app
  (-> app-routes
      (resource/wrap-resource "public")
      )
  )
