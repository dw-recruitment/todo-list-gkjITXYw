(ns testwebsite.views
  (:require [testwebsite.db :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Locations: " title)]
   (hic-p/include-css "/css/styles.css")])

(def header-links
  [:div#header-links
   "[ "
   [:a {:href "/"} "Todo's"]
   " | "
   [:a {:href "/home"} "Home"]
   " | "
   [:a {:href "/about"} "About"]
   " ]"])

(defn home-page
  []
  (hic-p/html5
    (gen-page-head "Todo's")
    header-links
    [:h1 "Under Construction"]
    [:p "These are your Todo's"]
    [:ul
     (for [todo (db/select-todos-from-db)]
       [:li (str (:text todo) " " (:doneness todo))]
       )
     ]
    ))


