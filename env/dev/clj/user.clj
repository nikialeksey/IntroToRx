(ns user
  (:require [mount.core :as mount]
            rxjs.core))

(defn start []
  (mount/start-without #'rxjs.core/repl-server))

(defn stop []
  (mount/stop-except #'rxjs.core/repl-server))

(defn restart []
  (stop)
  (start))


