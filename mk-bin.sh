#!/bin/sh
set -eux

[ -d target/bin ] || mkdir target/bin

cat src/main/bin/stub.sh target/web-lang-1.0.0-SNAPSHOT-jar-with-dependencies.jar > target/bin/weblang
chmod +x target/bin/weblang
