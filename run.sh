#!/bin/sh
mvn compile -DskipTests install
java -jar target/catalyzer-0.1-SNAPSHOT.jar server src/main/resources/com/hackzurich/catalyzer/config.yml
