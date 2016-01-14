Java RESTful Transaction
-----
Java RESTful web services that stores and returns transactions by id or type, or return the sum of a transaction and its children

# Technologies
 1. Java 8
 2. Maven 3
 3. Spring 1.3.1
 4. Jersey 2.22.1
 5. Jackson 2.7.0

# Documentation
The complete documentation can be found by browsing the [Javadoc](https://github.com/ValentinBrclz/Java-RESTful-Transaction/tree/master/doc).

# Curl examples
## Adding a transaction
```sh
curl -i
     -H "Accept: application/json"
     -H "Content-Type: application/json"
     -X PUT
     -d "{\"amount\":1000,\"type\":\"shopping\"}"
     http://localhost:8080/transactionservice/transaction/5
```
## Reading a transaction
```sh
curl -i
     -H "Accept: application/json"
     -X GET
     http://localhost:8080/transactionservice/transaction/1
```
## Getting transactions by type
```sh
curl -i
     -H "Accept: application/json"
     -X GET
     http://localhost:8080/transactionservice/types/grocery
```
## Getting the sum of a transaction and its children
```sh
curl -i
     -H "Accept: application/json"
     -X GET
     http://localhost:8080/transactionservice/sum/1
```

## License and credits
* License: GNU General Public Licence (2.0)
* Author: [Valentin Berclaz](http://www.valentinbeclaz.com/)