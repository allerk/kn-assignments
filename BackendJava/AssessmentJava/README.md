# Getting Started

## About:
This is Java Servlet based application to test DB class model for Quiz Application.
It can be used only by running tests (only DAO based).


## How to run?
[//]: # (- Open gradle -> gretty -> appRun or simply ```./gradlew appStart``` or by running junit tests &#40;i.e test -> java -> ee.quiz&#41;)
[//]: # (- Build a project using gradle: ```./gradlew clean build```)
- Use ```docker compose up``` to create db
- Run manually ```schema.sql``` script to set up db
- By running junit tests (i.e test -> java -> ee.quiz)
## Features
- There is no auto-clear db function!!! To be able to make db empty run first 6 lines in ```schema.sql```
- There are no REST api to interact with. It was planned before, but something went wrong later :(