Java RESTful Transaction
-----
Java RESTful web services that stores and returns transactions by id or type, or return the sum of a transaction and its children

# Technologies
 1. Java 8
 2. Maven 3
 3. Spring 1.3.1
 4. Jersey 2.22.1
 5. Jackson 2.7.0

# Performance
In this script, the choice has been made to have a fast reading of information (GET), but a longer writting (PUT).

The local database (in memory) has been optimised for a fast reading using specific indexes (parent_id and type), but that make the writting process 3x slower. To do so, the MemoryDatabase uses three ConcurrentHashmap which performance are similar to an Hashmap and the complexity for searching, reading and insering is ```O(1)``` which is really good.

A specific fonction had to be created to do the sum of the parent and its children. ```getSumRecursively()``` does it recursively and its complexity is ```O(n)```.

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