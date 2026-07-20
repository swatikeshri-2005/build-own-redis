# Redis Clone in Java 🚀

A Redis-inspired in-memory key-value database built completely from scratch in Java using TCP sockets and the RESP (Redis Serialization Protocol).

This project is being developed step by step to understand how Redis works internally instead of simply using the official Redis server.

---

## Features

### Networking

- TCP Server
- Multi-client support
- Thread Pool (ExecutorService)
- Socket Programming

### RESP Protocol

- RESP Parser
- RESP Writer
- Simple Strings
- Bulk Strings
- Integers
- Arrays
- Error Responses

### Database

- In-memory Key-Value Store
- ConcurrentHashMap based storage
- Thread-safe operations

### Supported Commands

| Command | Description |
|----------|-------------|
| PING | Test server connection |
| ECHO | Echo a message |
| SET | Store a key-value pair |
| GET | Retrieve a value |
| DEL | Delete a key |
| EXISTS | Check if a key exists |
| KEYS | List all keys |
| EXPIRE | Set expiration time |
| TTL | Check remaining lifetime |
| PERSIST | Remove expiration |
| INCR | Increment integer value |
| DECR | Decrement integer value |
| MSET | Store multiple key-value pairs |
| MGET | Retrieve multiple values |

---

# Project Structure

```
redis-clone/
│
├── src/
│   ├── Main.java
│   │
│   ├── server/
│   │      RedisServer.java
│   │      ClientHandler.java
│   │      ConnectionManager.java
│   │
│   ├── protocol/
│   │      RESPParser.java
│   │      RESPWriter.java
│   │
│   ├── commands/
│   │      Command.java
│   │      CommandRegistry.java
│   │      PingCommand.java
│   │      EchoCommand.java
│   │      SetCommand.java
│   │      GetCommand.java
│   │      Delcommand.java
│   │      ExistsCommand.java
│   │      KeysCommand.java
│   │      ExpireCommand.java
│   │      TTLCommand.java
│   │      PersistCommand.java
│   │      IncrCommand.java
│   │      DecrCommand.java
│   │      MSetCommand.java
│   │      MGetCommand.java
│   │
│   ├── database/
│   │      RedisDatabase.java
│   │      ExpiryManager.java
│   │
│   └── TestClient.java
│
├── README.md
├── pom.xml
└── .gitignore
```

---

# Architecture

```
                    Test Client
                         │
                         ▼
                  TCP Socket (6379)
                         │
                         ▼
                  Redis Server
                         │
                         ▼
               Connection Manager
                         │
                         ▼
                 Client Handler
                         │
                         ▼
                RESP Parser
                         │
                         ▼
              Command Registry
                         │
 ┌───────────────┬───────────────┬───────────────┐
 ▼               ▼               ▼
PING          SET/GET        EXPIRE/TTL
 │               │               │
 └───────────────┴───────────────┘
                 │
                 ▼
          Redis Database
                 │
                 ▼
      ConcurrentHashMap Storage
```

---

# Implemented Parts

## Part 1

### TCP Server

- Java ServerSocket
- Socket Programming
- Multi-client support
- Thread Pool

---

## Part 2

### RESP Protocol

Implemented Redis Serialization Protocol

Supported Types

- Simple String
- Bulk String
- Integer
- Arrays
- Error

---

## Part 3

### Interactive Client

Built a Java CLI client that communicates with the Redis server.

Example

```
redis> PING
+PONG

redis> ECHO Hello
Hello
```

---

## Part 4

### Key Value Database

Implemented

- SET
- GET

Example

```
redis> SET name Swati
+OK

redis> GET name
Swati
```

---

## Part 5

### Key Management

Commands

- DEL
- EXISTS
- KEYS

Example

```
redis> DEL name
:1

redis> EXISTS name
:0

redis> KEYS *
```

---

## Part 6

### Key Expiration

Implemented

- EXPIRE
- TTL
- PERSIST

Example

```
redis> SET city Kolkata

redis> EXPIRE city 30

redis> TTL city

redis> PERSIST city
```

---

## Part 7

### Numeric & Multi-Key Commands

Implemented

- INCR
- DECR
- MSET
- MGET

Example

```
redis> SET count 10

redis> INCR count

:11

redis> MSET
name Swati
city Kolkata
age 21

redis> MGET
name city age
```

---

## Part 8

### Command Registry

Refactored the server using the Command Pattern.

Instead of using a large switch statement, commands are dynamically registered inside a Command Registry.

Benefits

- Cleaner architecture
- Easier to add new commands
- Better scalability
- Object-Oriented Design

---

# Technologies Used

- Java
- Socket Programming
- Multithreading
- ConcurrentHashMap
- RESP Protocol
- Object-Oriented Programming
- Command Pattern

---

# How to Compile

```
javac -d out src\Main.java src\TestClient.java src\server\*.java src\protocol\*.java src\commands\*.java src\database\*.java
```

---

# Run Server

```
java -cp out Main
```

---

# Run Client

```
java -cp out TestClient
```

---

# Example Session

```
redis> PING
+PONG

redis> SET name Swati
+OK

redis> GET name
Swati

redis> EXISTS name
:1

redis> DEL name
:1

redis> SET count 10
+OK

redis> INCR count
:11

redis> DECR count
:10

redis> KEYS *
count

redis> exit
```

---

# Upcoming Features

- Transactions (MULTI / EXEC / DISCARD)
- Append Only File (AOF)
- RDB Snapshots
- Pub/Sub
- Master-Replica Replication
- Streams
- Persistence
- NIO Server
- Performance Optimizations
- Redis Compatibility

---

# Learning Objectives

This project demonstrates

- Socket Programming
- Networking
- Thread Pools
- Concurrency
- Custom Protocol Design
- In-Memory Database Design
- Object-Oriented Design
- Command Pattern
- System Design Fundamentals

---

# Author

**Swati Keshri**

B.Tech Computer Science & Engineering

Built for learning distributed systems, databases, networking, and Java backend development.

⭐ If you found this project interesting, consider giving it a star on GitHub!
