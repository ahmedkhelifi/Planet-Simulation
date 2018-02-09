#!/bin/sh

THE_CLASSPATH=
PROGRAM_NAME=planetsSimulation.java
cd src
for i in `ls *.jar`
  do
  THE_CLASSPATH=${THE_CLASSPATH}:${i}
done

javac -classpath ".:${THE_CLASSPATH}" $PROGRAM_NAME

if [ $? -eq 0 ]
then
  echo "compile worked!"
fi

java planetsSimulation