# ![Brand](assets/rootop-logo.jpg)

# Career Switch Challenge 🏁

## Goals 🏆

#### Solve the riddle!!. 🤓

1. Get a token from https://rooftop-career-switch.herokuapp.com/token?email=usuario@gmail.com using your personal email address.

    ```yaml
    {
       "token": "R5cCI6Ik-iwib-F0Ij-IyfQ-iJIUzI1NiIsI"
    }
    ```

2. Fetch the array of unsorted blocks from
    https://rooftop-career-switch.herokuapp.com/blocks?token={token} using the token provided. Sample data:

    ```yaml
    {
       "data": ["qwer", "asdf", "zcvf", "erty", "jhgf", "polk", "gthu", "uhgt"],
       "chunkSize": 4,
       "length": 32
    }
    ```
3. The first block is in place, so you need to figure out how the others are sorted sending a POST request to https://rooftop-career-switch.herokuapp.com/check?token={token} using the token provided and this body:

    ```yaml
    {
       "blocks": [
          "qwer",
          "jhgf"
       ]
    }
    ```
   You'll get `{"message":true}` if _"qwer"_ is after _"jhgf"_, otherwise `{"message":false}`.

### Considerations 👀

* Optimize the code sending the less amount of request to the server
* Be careful about using recursive or iterative structures, be sure they don't produce infinite loops. Test the code before trying the API.

## Requirements 👍
+ [JDK 11](https://adoptium.net/temurin/releases?version=11) or later

## Build 🔨
To build the JAR, runs the next command line.
* For Windows:
  `./gradlew.bat build`

* For Linux or Mac:
  `./gradlew build`

This will generate the executable `build/libs/career-switch-challenge-1.0-SNAPSHOT.jar`

## Execute application ⚙️
This is a JAR application, it's designed to run on console, to execute use the next command
`java -jar career-switch-challenge-1.0-SNAPSHOT.jar`

## Tests ✅

To running test use:
* For Windows:
  `./gradlew.bat clean test`

* For Linux or Mac:
  `./gradlew clean test`

