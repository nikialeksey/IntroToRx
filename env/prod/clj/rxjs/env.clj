(ns rxjs.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[rxjs started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[rxjs has shut down successfully]=-"))
   :middleware identity})
