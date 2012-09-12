# hs-ex

A fun clojure project with loads of learning.....

## Usage

git clone git@github.com:murtaza52/hs-ex.git

lein2 deps

The server can be stated in either of two ways - 

First- 
a) cd into the project folder.
b) lein2 repl
c) the server should be up and running ! (This uses the ring.util.serve to satrt the server on the repl startup)

Second-
a) Ensure you have [lein-ring "0.7.5"] in ~/.lein/profiles if you are using leiningen 2.
b) lein2 deps
c) lein2 ring server

lein2 ring server

## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
