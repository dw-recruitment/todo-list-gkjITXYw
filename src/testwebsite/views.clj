(ns testwebsite.views
  (:require [testwebsite.db :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Todo's: " title)]
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
    (gen-page-head "Testing")
    header-links
    [:h1 "Under Construction"]
    [:p "These are your Todo's"]
    [:ul
     (for [todo (db/select-todos-from-db)]
       [:li [:del (:text todo)] (:doneness todo)])]
    [:h3 "Add a Todo"]
    [:form {:action "/add-todo" :method "POST"}
     [:p "Text: " [:input {:type "text" :name "text"}] [:input {:type "submit" :value "create todo"}]]]))
