# hs-ex

A fun clojure project with loads of learning.....

## Usage

git clone git@github.com:murtaza52/hs-ex.git

lein2 deps

The server can be stated in either of two ways - 

First- 
1. cd into the project folder.
2. lein2 repl
3. the server should be up and running ! (This uses the ring.util.serve to satrt the server on the repl startup)

Second-
1. Ensure you have [lein-ring "0.7.5"] in ~/.lein/profiles if you are using leiningen 2.
2. lein2 deps
3. lein2 ring server

lein2 ring server

## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
