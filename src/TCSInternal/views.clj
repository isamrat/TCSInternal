(ns TCSInternal.views
  (:require [TCSInternal.service :as service]
            [hiccup.page :as hic]
            ))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Locations: " title)]
   (hic/include-css "/css/styles.css")])

(def header-links
  [:div#header-links
   "[ "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/search-data"} "Search Data"]
   " ]"])

(defn home-page []
  (hic/html5
    (gen-page-head "Home")
    header-links
    [:h1 "Welcome"]
    [:p "Please follow the links"]))

(defn search-page []
  (hic/html5
    (gen-page-head "Search Data")
    header-links
    [:h1 "Search Data"]
    [:form {:action "/search-data" :method "POST"}
     [:p "Employee No. " [:input {:type "number" :name "empno"}]]
     [:p [:input {:type "submit" :value "View VPN token"}]]]))

(defn search-result-page [{:keys [empno]}]
  (def result (service/vpn-search empno))
  (if-not(= result nil)  (hic/html5
                           (gen-page-head "Search Result")
                           header-links
                           [:h1 "Search Result"]
                           [:p "Emp No=" empno]
                           [:p "VPN no=" result]
                           ) (hic/html5
                                (gen-page-head "Search Result")
                                header-links
                                [:h1 "Empno Not Exist"] ))
 )