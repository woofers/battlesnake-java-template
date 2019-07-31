
# Table of Contents

-   [Battlesnake Template](#org0f72bd7)
    -   [About](#org037056d)
    -   [Usage](#org13dbb15)
        -   [Prerequisites](#orgc3d5570)
        -   [Test Board](#orgfcf9300)
        -   [Run Locally](#org6e620be)
        -   [Deployment](#orgc3f22c5)
    -   [Acknowledgments](#org0f4301b)



<a id="org0f72bd7"></a>

# Battlesnake Template

<img height="120" width="120" src="screenshots/advanced.png" />

A un-official [Battlesnake](https://www.battlesnake.io) template written in Java.

Battlesnake documentation can be found at [docs.battlesnake.io](https://docs.battlesnake.io).

[![img](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)


<a id="org037056d"></a>

## About

This template is different from the official Java [Battlesnake.io Template](https://github.com/battlesnakeio/starter-snake-java) in a few ways:

-   GSON is used for JSON serialization rather than Jackson
-   Pure Java Servlets are used rather than the Spark Framework
-   Tomcat is used as the servlet container rather than Jetty which is used by default by Spark
-   Gradle is used as the build tool rather than Maven

The official Java [Template](https://github.com/battlesnakeio/starter-snake-java) is also simpler and smaller
however this template comes out-of-the-box with the following additional features:

-   Logging support using Log4j2
-   Logs server response time
-   Code formatting, removing used imports using spotless


<a id="org13dbb15"></a>

## Usage


<a id="orgc3d5570"></a>

### Prerequisites

1.  Install JDK 11 or higher
2.  Install Docker + Docker compose (required for [Docker container method](#org6458b36))
3.  Install Heroku CLI (required for [Heroku CLI method](#orgb98a84b))
4.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy` ) (required for [Heroku CLI method](#orgb98a84b))
5.  Create a Heroku App online or using the Heroku CLI with `heroku create <name>`

    (alternatively any other hosting service can be used)


<a id="orgfcf9300"></a>

### Test Board

**Online**

Goto [play.battlesnake.io](https://play.battlesnake.io)


<a id="org6e620be"></a>

### Run Locally

**Gradle**

1.  Run the project using either `./gradle run` or `gradlew run` for UNIX and Windows platforms respectively.  This will build and run the project as a \`JAR\` using [Webapp Runner](https://github.com/jsimone/webapp-runner).
2.  Use `http://localhost:8080` as the snake URL.

**Docker**
<a id="org6458b36"></a>

1.  Build the project using either `./gradle build` or `gradlew build` for UNIX and Windows platforms respectively.
2.  Run the Docker Tomcat image in a container with `docker-compose up`.
3.  Use `http://localhost:8080/battlesnake` as the snake URL.


<a id="orgc3f22c5"></a>

### Deployment

**Heroku-GitHub Integration**

1.  Go the dashboard for the Heroku app.
2.  Click on the `deploy` tab.
3.  Scroll down to the `Deployment method` and select `GitHub`.
4.  Enter the repository name and click `Connect`.

**Heroku CLI**
<a id="orgb98a84b"></a>

1.  Build .war file `./gradlew build`
2.  Deploy to Heroku `heroku war:deploy build/libs/battlesnake.war --app <name>`
3.  Use `https://[name].herokuapp.com/` as the snake URL.


<a id="org0f4301b"></a>

## Acknowledgments

-   **Built by** [Ben Austin](https://github.com/austinben) and [Jaxson Van Doorn](https://github.com/woofers)
-   **Adapted from** [tflinz's snake](https://github.com/tflinz/BasicBattleSnake2018)
-   **Designed for** [Battlesnake.io](https://github.com/battlesnakeio) competition

<img align="left" height="120" width="120" src="screenshots/advanced.png" />
