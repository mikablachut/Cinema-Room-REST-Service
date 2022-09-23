# Cinema-Room-REST-Service
* [Description](#description)
* [Technologies](#technologies)
* [Setup](#setup)
* [Endpoints](#endpoints)
    * [Cinema Seats Endpoint](#cinema-seats-endpoint)
    * [Purchase Ticket Endpoint](#purchase-ticket-endpoint)
    * [Return Ticket Endpoint](#return-ticket-endpoint)
    * [Cinema Statistic Endpoint](#cinema-statistic-endpoint)
    
## Description

<details>
<summary>Click here to see general information about the <b>Project</b>!</summary>

Web application which allows to manage a small movie theathre. The service supoorts REST requests: availability of seats, ticket sales, ticket refund and cinema statistics (current income, number of available seats, number of purchased tickets).

The idea for project comes from [JetBrains Academy](https://www.jetbrains.com/academy/) Java Beckend Developer track.
  
</details>

## Technologies

<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>Gradle</li>
  <li>JUnit</li>
  <li>Mockito</li>
  <li>REST API</li>
</ul>

## Setup

Make sure you have [git](https://git-scm.com/) installed.

### Gradle

<b>Java 17 is required for this step.</b>

Clone repository and enter its folder:

```
git clone https://github.com/mikablachut/Cinema-Room-REST-Service.git
cd Cinema-Room-REST-Service
```

Now you can run the app using Gradle:

```
gradle bootRun
```

Application can also be started using ```java -jar``` command but for that, you need to generate jar file.
Steps for this scenario:

```
gradle build
java -jar .\build\libs\cinema-2.7.1.jar
```

## Endpoints

When you start the application a cinema room will be created. The cinema room will have 9 rows with 9 seats in each of them. You can use [Postman](https://www.postman.com) or any similar program to test the APIs.

### Cinema Seats Endpoint

Endpoint returns a JSON object that contains information about the rows, columns, available seats and their price in the theatre.

GET method

```GET /seats```

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
   "total_rows":9,
   "total_columns":9,
   "available_seats":[
      {
         "row":1,
         "column":1,
         "price":10
      },
      {
         "row":1,
         "column":2,
         "price":10
      },
      {
         "row":1,
         "column":3,
         "price":10
      },

      ........

      {
         "row":9,
         "column":8,
         "price":8
      },
      {
         "row":9,
         "column":9,
         "price":8
      }
   ]
}
```
</p>
</details>

### Purchase Ticket Endpoint

Endpoint recives a JSON object with two fields: row number and column number. If the seat is available, the program will mark it as purchased and responds with 200 (Ok). If the seat has been reserved or if users entered the wrong row/column number, the program will respond with 400 (BadRequest).

POST method

```POST /purchase``` correct request

Request body:

```json
{
    "row": 3,
    "column": 4
}
```

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556",
    "ticket": {
        "row": 3,
        "column": 4,
        "price": 10
    }
}
```

</p>
</details>

```POST /purchase``` request, the ticket is already booked

Request body:

```json
{
    "row": 3,
    "column": 4
}
```

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
  "error": "The ticket has been already purchased!"
}
```

</p>
</details>

```POST /purchase```  request, a wrong row number

Request body:

```json
{
    "row": 15,
    "column": 4
}
```

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
   "error": "The number of a row or a column is out of bounds!"
}
```

</p>
</details>

### Return Ticket Endpoint

Endpoint recives a JSON object with field token. If the program identifies the ticket to which the token refers to, the seat will be marked as available and information about the returned ticket will be displayed. If the program cannot identify the ticket by the token, it will respond with a status code 400.

POST method

```POST /return``` with the correct token

Request body:

```json
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
    "returned_ticket": {
        "row": 1,
        "column": 2,
        "price": 10
    }
}
```

</p>
</details>

```POST /return``` with an expired token

Request body:

```json
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
    "error": "Wrong token!"
}
```

</p>
</details>

### Cinema Statistic Endpoint

Endpoint handles POST requests with URL parameters. If the URL parameters contain a password with a 'super_secret' value, the program will return the movie theatre statistics. If the parameters don't contain a password or a wrong value has been entered, the program will respond with a 401 status code.

POST method

```POST /stats``` request with the correct password

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
    "current_income": 30,
    "number_of_available_seats": 78,
    "number_of_purchased_tickets": 3
}
```

</p>
</details>

```POST /stats``` request with no parameters

<details>
<summary><b>Sample response</b></summary>
<p>

```json
{
    "error": "The password is wrong!"
}
```

</p>
</details>
