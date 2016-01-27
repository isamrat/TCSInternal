(ns TCSInternal.handler
  (:require [TCSInternal.views :as views]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]  )
  (:gen-class))

(defroutes app-routes
           (GET "/" [] (views/home-page))
           (GET "/search-data" [] (views/search-page))
           (POST "/search-data" {params :params} (views/search-result-page params))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(defn -main []
  (jetty/run-jetty #'app {:port  8081
                          :status 200}))