(ns hs-ex.routes
  (:use [net.cgrand.moustache :only [app pass]]
        [net.cgrand.enlive-html :only [deftemplate content html-content defsnippet clone-for]]
        [ring.middleware.file :only [wrap-file]]
        [ring.middleware.stacktrace :only [wrap-stacktrace]]
        [ring.middleware.file-info :only [wrap-file-info]]
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.keyword-params :only [wrap-keyword-params]]
        [ring.util.response :only [response status]]
        [hs-ex.core :only [get-pages get-expr]]
        [clojure.pprint :only [pprint]]))

(defsnippet calc "hs_ex/templates/calc.html" [:#calcblock]
  [{:keys [expr page-name x value]}]
  [:#expr] (content (str page-name " : " expr))
  [:#x] (content (str x))
  [:#value] (content (str value)))

(deftemplate index-page "hs_ex/templates/index.html" []
  [:li] (clone-for [p (get-pages)]
                   (content p)))

(deftemplate calc-page "hs_ex/templates/index.html" [data]
  [:#main] (content (calc data)))

(defn wrap-logger
  [app flag]
  (fn [req]
    (when flag (do (println "******************************************** Request Map ***********************************************")
                   (pprint req)
                   (println "******************************************** Response Map ***********************************************")))
    (app req)))

;;http://stackoverflow.com/questions/5621279/in-clojure-how-can-i-convert-a-string-to-a-number
(defn str->int
  [str]
  (when str
    (if (re-matches (re-pattern "\\d+") str)
      (read-string str))))

(defn index-route []
  (fn [req]
    (-> (index-page) response)))

(defn page-route
  [page-name]
  (fn [req]
    (let [x (-> req :params (get "x") str->int)
          expr (get-expr page-name)
          value ((-> (str "hs-ex.core/" page-name) symbol resolve) x)
          data {:x x :expr expr :value value :page-name page-name}]
      (-> (calc-page data) response)))) 

(def my-app (app
             (wrap-logger true)
             wrap-keyword-params
             wrap-params
             wrap-file-info
             (wrap-file "resources/public/")
             [""]  (index-route)
             ["/"] (index-route)
             [page-name] (page-route page-name)  
             [&] (-> "Nothing was found" response (status 404) constantly)))