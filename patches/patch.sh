#!/bin/bash 

if [[ -z "$CHIPYARD" ]]
then
  echo "CHIPYARD is not set" >& 2
  exit 1
fi

PWD=`pwd`

cp -i RocketSha3Configs.scala ${CHIPYARD}/generators/chipyard/src/main/scala/config/
cd ${CHIPYARD} && (git apply -R --check ${PWD}/build.sbt.patch 2> /dev/null || git apply ${PWD}/build.sbt.patch)
