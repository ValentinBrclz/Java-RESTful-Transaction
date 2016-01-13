# Java RESTful Transaction
Java RESTful web services that stores and returns transactions

# Technologies
 1. Java 8
 2. Maven 3
 3. Spring 1.3.1
 4. Jersey 2.22.1
 5. Jackson 2.7.0

Proudly made with IntelliJ IDEA <3

# Documentation
(todo)

# Curl examples
 * Transaction
 ** GET: curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/transactionservice/transaction/1
 ** PUT: curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X PUT -d "{\"amount\":1000,\"type\":\"shopping\"}" http://localhost:8080/transactionservice/transaction/5
 * Type
 ** ?
 * Sum
 ** ?