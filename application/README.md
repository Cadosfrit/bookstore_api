# BookStore

## Bookstore API Documentation

This project is a RESTful API built using Spring Boot that provides a backend service for managing a digital bookstore. It allows users to sign up, log in, and securely interact with a collection of books. The API supports creating, retrieving, updating, and deleting book entries, as well as powerful search, filter, pagination, and sorting capabilities.

Authentication is handled via JSON Web Tokens (JWT), ensuring that only authorized users can access protected endpoints. MongoDB is used as the underlying database for storing user and book data.

This API is designed to be simple to use, developer-friendly, and ready for integration with any frontend or mobile application. Whether you're building a personal project, a book review platform, or an online bookstore, this API can serve as a solid foundation.

---

## Requirements

Before running this project, make sure you have the following installed:

- Java 17 or later
- MongoDB (running locally on port 27017)
- Postman or any other API testing tool (optional)

---

## How to Run the Application (JAR file)

If you have the JAR file (`target/application-0.0.1-SNAPSHOT.jar`), follow these steps:

1. Start MongoDB:
   ```
   mongod
   ```

2. Open a terminal and navigate to the project directory.

3. Run the application:
   ```
   java -jar target/application-0.0.1-SNAPSHOT.jar
   ```

4. The server will start at:
   ```
   http://localhost:8080
   ```

---

## API Endpoints

### Authentication

#### POST `/auth/signup`
##### Creates a new user account.

**Curl:**
```bash
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"email": "testuser@example.com", "password": "test1234"}'
```



**Request Body:**
```json
{
  "email": "testuser@example.com",
  "password": "test1234"
}
```

#### POST `/auth/login`
##### Logs in a user and returns a JWT token.

**Curl:**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "testuser@example.com", "password": "test1234"}'
```



**Request Body:**
```json
{
  "email": "testuser@example.com",
  "password": "test1234"
}
```

---





### Book Management

#### POST `/books`
##### Adds a new book. Requires Authorization header.

**Curl:**
```bash
curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your_token>" \
  -d '{
        "title": "Secrets of the JavaScript Ninja",
        "author": "John Resig",
        "category": "JavaScript",
        "price": 445,
        "rating": 4.6,
        "publishedDate": "2016-12-10"
      }'
```



**Request Body:**
```json
{
  "title": "Secrets of the JavaScript Ninja",
  "author": "John Resig",
  "category": "JavaScript",
  "price": 445,
  "rating": 4.6,
  "publishedDate": "2016-12-10"
}
```



#### GET `/books`
##### Returns a paginated and sorted list of books.

**Curl:**
```bash
curl -X GET "http://localhost:8080/books?page=1&size=5&sortBy=rating&sortDir=desc" \
  -H "Authorization: Bearer <your_token>"
```



**Query Parameters:**
- `page` – page number (default 0)
- `size` – number of items per page (default 10)
- `sortBy` – field to sort by (e.g., `rating`)
- `sortDir` – `asc` or `desc`

**Example:**
```
/books?page=1&size=5&sortBy=rating&sortDir=desc
```



#### GET `/books/{id}`
##### Gets a book by its ID.

**Curl:**
```bash
curl -X GET http://localhost:8080/books/<book_id> \
  -H "Authorization: Bearer <your_token>"
```





#### PUT `/books/{id}`
##### Updates a book by ID.

**Curl:**
```bash
curl -X PUT http://localhost:8080/books/<book_id> \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your_token>" \
  -d '{
        "title": "Updated Book Title",
        "author": "Updated Author Name",
        "category": "New Category",
        "price": 299.99,
        "rating": 4.5,
        "publishedDate": "2024-11-15"
      }'
```



**Request Body:**
```json
{
  "title": "Updated Book Title",
  "author": "Updated Author Name",
  "category": "New Category",
  "price": 299.99,
  "rating": 4.5,
  "publishedDate": "2024-11-15"
}
```



#### DELETE `/books/{id}`
##### Deletes a book by its ID.

**Curl:**
```bash
curl -X DELETE http://localhost:8080/books/<book_id> \
  -H "Authorization: Bearer <your_token>"
```



---




### Search & Filter



#### GET `/books/search`
##### Searches books by title. Supports pagination and sorting.

**Curl:**
```bash
curl -X GET "http://localhost:8080/books/search?title=Java&page=0&size=5&sortBy=price&sortDir=desc" \
  -H "Authorization: Bearer <your_token>"
```



**Query Parameters:**
- `title`
- `page`
- `size`
- `sortBy`
- `sortDir`

**Example:**
```
/books/search?title=Java&page=0&size=5&sortBy=price&sortDir=desc
```



#### GET `/books/filter`
##### Filters books by any combination of author, category, and rating. Also supports pagination and sorting.

**Curl:**
```bash
curl -X GET "http://localhost:8080/books/filter?author=Andrew%20Hunt&category=Programming&rating=4.1&page=0&size=5&sortBy=price&sortDir=asc" \
  -H "Authorization: Bearer <your_token>"
```



**Query Parameters:**
- `author`
- `category`
- `rating`
- `page`
- `size`
- `sortBy`
- `sortDir`

**Example:**
```
/books/filter?author=Andrew Hunt&category=Programming&rating=4.1&page=0&size=5&sortBy=price&sortDir=asc
```

---


## Authorization

All `/books/**` endpoints require a JWT token in the header:

```
Authorization: Bearer <your_token_here>
```
