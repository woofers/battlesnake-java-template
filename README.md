
# Table of Contents

-   [Battlesnake Template](#org7a845bc)
    -   [About](#org3ada019)
    -   [Usage](#orgde93732)
        -   [Prerequisites](#org37b2efb)
        -   [Test Board](#orgdb070fe)
        -   [Run Locally](#org52bee51)
        -   [Deployment](#org8fd235d)
    -   [Acknowledgments](#orge9015a3)



<a id="org7a845bc"></a>

# Battlesnake Template

<img height="120" width="120" src="screenshots/advanced.png" />

A simple [Battlesnake](https://www.battlesnake.io) template written in Java.

Battlesnake documentation can be found at <https://docs.battlesnake.io>.

[![img](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)


<a id="org3ada019"></a>

## About

This template is different than the official java 
[Battlesnake.io Template](https://github.com/battlesnakeio/starter-snake-java)
due to a more object-orientated approach, as well as being more type safe.


<a id="orgde93732"></a>

## Usage


<a id="org37b2efb"></a>

### Prerequisites

1.  Install JDK 11 or higher
2.  Install Docker + Docker compose (required for [Docker container method](#org3ff4c85))
3.  Install Heroku CLI (required for [Heroku CLI method](#org08feee7))
4.  Install .war Heroku deployment plug-in `heroku plugins:install heroku-cli-deploy` ) (required for [Heroku CLI method](#org08feee7))
5.  Create a Heroku App online or using the Heroku CLI with `heroku create <name>`

    (alternatively any other hosting service can be used)


<a id="orgdb070fe"></a>

### Test Board

**Online**

Goto [play.battlesnake.io](https://play.battlesnake.io)


<a id="org52bee51"></a>

### Run Locally

**Gradle**

1.  Run the project using either `./gradle run` or `gradlew run` for UNIX and Windows platforms respectively.  This will build and run the project as a \`JAR\` using [Webapp Runner](https://github.com/jsimone/webapp-runner).
2.  Use `http://localhost:8080` as the snake URL.

**Docker**
<a id="org3ff4c85"></a>

1.  Build the project using either `./gradle build` or `gradlew build` for UNIX and Windows platforms respectively.
2.  Run the Docker Tomcat image in a container with `docker-compose up`.
3.  Use `http://localhost:8080/battlesnake` as the snake URL.


<a id="org8fd235d"></a>

### Deployment

**Heroku-GitHub Integration**

1.  Go the dashboard for the Heroku app.
2.  Click on the `deploy` tab.
3.  Scroll down to the `Deployment method` and select `GitHub`.
4.  Enter the repository name and click `Connect`.

**Heroku CLI**
<a id="org08feee7"></a>

1.  Build .war file `./gradlew build`
2.  Deploy to Heroku `heroku war:deploy build/libs/battlesnake.war --app <name>`
3.  Use `https://[name].herokuapp.com/` as the snake URL.


<a id="orge9015a3"></a>

## Acknowledgments

-   **Built by** [Ben Austin](https://github.com/austinben) and [Jaxson Van Doorn](https://github.com/woofers)
-   **Adapted from** [tflinz's snake](https://github.com/tflinz/BasicBattleSnake2018)
-   **Designed for** [Battlesnake.io](https://github.com/battlesnakeio) competition

<img align="left" height="120" width="120" src="screenshots/advanced.png" />
