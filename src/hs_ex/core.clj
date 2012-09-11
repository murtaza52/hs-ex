(ns hs-ex.core)

(def pages (atom {}))

(defn add-page [page-name body]
  (swap! pages merge {page-name body}))

(defn get-pages
  ([] (let [p (->> @pages keys)]
        (map str p)))
  ([n] (let [p (->> @pages keys (take n))]
       (map str p))))

(defn get-expr [page-name]
  (@pages (symbol page-name)))

(defmacro defcalc [page-name body]
  (add-page page-name body)
  `(defn ~page-name [~'x] ~body))

(defcalc plusthree (+ 3 x))

(defcalc plustwo (+ 2 x))

(defcalc timesten (* x 10))
(defcalc cubed (* x x x))
(defcalc xcond (cond (= x 1) 100 (= x 2) 200 :else 0))
