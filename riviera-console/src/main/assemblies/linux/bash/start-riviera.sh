#!/bin/bash

LD=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

#echo "LAUNCH DIRECTORY"
#echo "----------------"
#echo "${LD}"
#echo $'\n'

#echo "CURRENT DIRECTORY"
#echo "-----------------"
#pwd
#echo $'\n'

PD=${LD}/..
JAVA_BIN=${PD}/jre/bin/java
#echo "JAVA BIN"
#echo "--------"
#echo "${JAVA_BIN}"
#echo $'\n'

# START RIVIERA
${JAVA_BIN} -jar ${PD}/lib/${project.build.finalName}.jar "$@"
