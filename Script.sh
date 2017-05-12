#!/bin/bash
javac -cp .:webroot/WEB-INF/lib/javax.json.jar:webroot/WEB-INF/lib/servlet-api.jar:webroot/WEB-INF/lib/sqlite-jdbc-3.8.11.2.jar webroot/WEB-INF/classes/se/yrgo/student/*/*.java && java -cp webroot/WEB-INF/lib/javax.json.jar:webroot/WEB-INF/lib/sqlite-jdbc.3.8.11.2.jar:webroot/WEB-INF/classes/ -DformatsXML=formats.xml se.yrgo.student.main.Main $1
