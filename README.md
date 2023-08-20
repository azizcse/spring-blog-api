# Product API

This project provides a RESTful API for managing products.

## Prerequisites

- Java 11 or later
- Gradle

## Getting Started

1. Clone the repository:

   ```shell
   git clone https://github.com/AsifTD13/SimpleCrud.git

2. Navigate to the project directory:
   
   cd SimpleCrud
   
3. Build the project:
   
   ./gradlew build
   
4. Run the API:
   
   ./gradlew bootRun
   
   The API will start running on http://localhost:8090.
 
## API Endpoints

### Authentication API

This API endpoint is used to authenticate users and obtain a JWT (JSON Web Token) for subsequent API calls.

    Method: POST
    URL: http://localhost:8090/v1/authenticate
    Request Body:
        username (string): The username of the user.
        password (string): The password of the user.

**Example request**

POST /v1/authenticate HTTP/1.1

Host: localhost:8090

Content-Type: application/json

{
  "username": "admin",
  "password": "admin"
}

**Example Response**


HTTP/1.1 200 OK

Content-Type: application/json

{
  "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4NDcyOTY4OSwiZXhwIjoxNjg0NzY1Njg5fQ.j6Uu4MtZmT2Gyq41zuMZJq5K9qxr46ngLCZERVpRqec"
}

**The response will contain the JWT token (jwt) that can be used for subsequent API calls as an authentication token.**


### Get All Products

    Method: GET
    URL: /v1/products

Retrieves a list of all products.

### Get a Product

    Method: GET
    URL: /v1/products/{id}

Retrieves a specific product by its ID.

### Create a Product

    Method: POST
    URL: /v1/products
    Request Body:
        name (string): The name of the product.
        description (string): The description of the product.
        price (number): The price of the product.
        category (string): The category of the product.

Creates a new product.

### Update a Product

    Method: PUT
    URL: /v1/products/{id}
    Request Body:
        name (string): The updated name of the product.
        description (string): The updated description of the product.
        price (number): The updated price of the product.
        category (string): The updated category of the product.

Updates an existing product with the specified ID.

### Delete a Product

    Method: DELETE
    URL: /v1/products/{id}

Deletes an existing product with the specified ID.

##	Running Unit Tests

1.  Navigate to the project directory (if you are not already in it): 

    ```Shell
    
    cd SimpleCrud
    
2.  Run the unit tests

    ```Shell
    
    
    ./gradlew test 
    

    
