@echo off
set "JAVA_HOME=C:\Users\itsan\.jdks\graalvm-jdk-17.0.12"
set "PATH=%JAVA_HOME%\bin;%PATH%"
mvn %*
