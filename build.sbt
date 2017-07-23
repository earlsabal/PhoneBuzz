name := """PhoneBuzz"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice

libraryDependencies += javaJdbc
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"

// https://mvnrepository.com/artifact/com.twilio.sdk/twilio-java-sdk
libraryDependencies += "com.twilio.sdk" % "twilio" % "7.11.0"
