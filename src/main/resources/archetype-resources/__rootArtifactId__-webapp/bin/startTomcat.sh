#!/bin/bash
rm target/logs/*
mvn -Denv=prystasj -Dlog.dir=target/logs tomcat:run
