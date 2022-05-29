(ns remus
  "
  Clojure wrapper around Java Rome tools.
  https://github.com/igrishaev/remus

  Rome options:

  - `lenient`: a boolean flag which makes Rome to be more loyal
    to some mistakes in XML markup;

  - `encoding`: a string which represents the encoding of the feed.
    When parsing a URL, it comes from the `Content-Encoding` HTTP header.
    Possible values are listed here:
    https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html

  - `content-type`: a string meaning the MIME type of the feed, e.g. `application/rss`
    or something. When parsing a URL, it comes from the `Content-Type` header.
  "

  (:require
   [remus.rome :as rome]
   [clojure.java.io :as io])

  (:import
   java.io.InputStream))


;;
;; Parsing
;;

(defn parse-stream
  "
  An utility function used as a bottleneck
  for parsing a file, URL, etc.
  "
  [^InputStream stream & [opt-rome]]
  (-> stream
      (rome/make-reader opt-rome)
      (rome/reader->feed)))


(defn parse-file
  "
  Parse a feed from a file. Path is a string
  referencing a file on disk.
  "
  [^String path & [opt-rome]]
  (let [stream (-> path io/as-file io/input-stream)]
    (parse-stream stream opt-rome)))
