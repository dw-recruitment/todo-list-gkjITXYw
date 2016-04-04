(ns testwebsite.views
  (:require [testwebsite.db :as db]
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

(defn is-done? [doneness]
  (= doneness ":done"))

(defn create-text [text doneness]
  (if (is-done? doneness)
    [:del text]
    text))

(defn create-delete-button [id]
  [:a {:href (str "/admin/" id "/delete")} [:button {:type "button"} "delete"]])

(defn create-button [id doneness]
  (if (is-done? doneness)
    [:a {:href (str "/admin/" id "/undo")} [:button {:type "button"} "undo"]]
    [:a {:href (str "/admin/" id "/complete")} [:button {:type "button"} "complete"]]))

(defn home-page
  []
  (hic-p/html5
    (gen-page-head "Testing")
    header-links
    [:h1 "Under Construction"]
    [:p "These are your Todo's:"]
    [:ul {:style "list-style:none"}
     (for [todo (db/select-todos-from-db)]
       (let [text (:text todo)
             id (:todo_id todo)
             doneness (:doneness todo)]
         [:li (create-text text doneness) (str " ") (create-button id doneness) (str " ") (create-delete-button id)]))]
    [:h3 "Add a Todo"]
    [:form {:action "/add-todo" :method "POST"}
     [:p "Text: " [:input {:type "text" :name "text"}] (str " ") [:input {:type "submit" :value "create todo"}]]]))
