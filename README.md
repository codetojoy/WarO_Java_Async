
WarO_Java
=========

a Java submission for War-O as a code exercise

* this project uses:
    - Java 8 streams
    - Java 10 `var`
    - Java 15 (second preview) `record`
    - Java 15 (preview) `sealed`
* goals include: 
    - API/remote strategy (called in the background, via `CompleteableFuture`)
        - see [here](https://github.com/codetojoy/WarO_Strategy_API_Java) for implementation
    - functional style
    - immutable objects (not necessarily efficient)
    - minimal use of for-loops
* Spring's Java configuration is used to configure players

To Build:
---------

* requires JDK 15

useful commands:

* `./gradlew clean test`
    - on Windows, use `gradlew.bat`
* `./gradlew run`
* `./gradlew build`

See test output in `~/build/reports/tests/index.html`

See executable zip in `~/build/distributions`

To Run:
---------

* configure `src/main/java/org/peidevs/waro/config/Config.java`
* `./gradlew run`
    - on Windows, use `gradlew.bat`

Rules:
---------

Rules are [here](Rules.md).
