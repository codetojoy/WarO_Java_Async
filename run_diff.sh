#!/bin/bash

./gradlew -q run > out.log
diff out.log gold.master.log

echo "Ready."

