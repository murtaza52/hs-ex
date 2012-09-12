(defproject hs-ex "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [net.cgrand/moustache "1.1.0"]
                 [enlive "1.0.1"]
                 [ring "1.1.5"]]
  :profiles {:dev {:dependencies [[ring-serve "0.1.2"]]}}
  :ring {:handler hs-ex.routes/my-app}
  :repl-options {:init-ns hs-ex.routes
                 :init (do
                         (use 'ring.util.serve)
                         (serve hs-ex.routes/my-app))})
