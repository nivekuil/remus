(defproject remus "0.2.2-SNAPSHOT"
  :description "Attentive RSS/Atom feed parser"

  :deploy-repositories {"releases" {:url "https://repo.clojars.org" :creds :gpg}}

  :release-tasks [["vcs" "assert-committed"]
                  ["test"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :url "https://github.com/igrishaev/remus"

  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[com.rometools/rome "1.16.0"]
                 [clj-http "3.10.2"]]

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.1"]
                                  [log4j/log4j "1.2.17"]]
                   :global-vars  {*warn-on-reflection* true
                                  *assert*             true}}})
