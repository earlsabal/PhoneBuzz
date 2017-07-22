name := """PhoneBuzz"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice

// https://mvnrepository.com/artifact/com.twilio.sdk/twilio-java-sdk
libraryDependencies += "com.twilio.sdk" % "twilio" % "7.11.0"
