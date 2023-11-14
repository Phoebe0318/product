# Product Management API

## Introduction

This is a simple API for managing products. It allows you to perform basic CRUD operations on products, as well as retrieve lists of published and unpublished products.

## API Endpoints

### Create Product

- **Endpoint**: `/products`
- **Method**: `POST`
- **Description**: Create a new product.
- **Required Fields**: `title`, `price`, `publish`, `description`, `stockQuantity`

### Edit Product

- **Endpoint**: `/products/{productId}`
- **Method**: `PUT`
- **Description**: Edit details of an existing product.
- **Parameters**: `productId` (integer)
- **Required Fields**: Any fields you want to update (e.g., `title`, `price`, `publish`)

### Delete Product

- **Endpoint**: `/products/{productId}`
- **Method**: `DELETE`
- **Description**: Delete an existing product.
- **Parameters**: `productId` (integer)

### Read All Products

- **Endpoint**: `/products`
- **Method**: `GET`
- **Description**: Retrieve a list of all products.
- **Response**: Array of products with fields `title` and `price`.

### Read Published Products

- **Endpoint**: `/products/published`
- **Method**: `GET`
- **Description**: Retrieve a list of published products.
- **Response**: Array of published products with fields `title` and `price`.

### Read Unpublished Products

- **Endpoint**: `/products/unpublished`
- **Method**: `GET`
- **Description**: Retrieve a list of unpublished products.
- **Response**: Array of unpublished products with fields `title` and `price`.

## How to Use

1. **Create a New Product**:
   - Send a `POST` request to `/products` with the required fields.

2. **Edit an Existing Product**:
   - Send a `PUT` request to `/products/{productId}` with the fields you want to update.

3. **Delete a Product**:
   - Send a `DELETE` request to `/products/{productId}`.

4. **Retrieve All Products**:
   - Send a `GET` request to `/products`.

5. **Retrieve Published Products**:
   - Send a `GET` request to `/products/published`.

6. **Retrieve Unpublished Products**:
   - Send a `GET` request to `/products/unpublished`.

### Using Docker

1. **Pull the Docker Image:**

    ```bash
    docker pull anlica62712/product
    ```

2. **Run the Docker Container:**

    ```bash
    docker run -d -p 8080:8080 --name product anlica62712/product:latest
    ```

    Replace `8080` with the desired port if needed.

3. **Access the API:**

    Open your web browser or use a tool like [curl](https://curl.se/) or [Postman](https://www.postman.com/) to interact with the API.

    - Base URL: `http://localhost:8080` (Replace `8080` with the port you specified)
    - API Endpoints:
        - Create Product: `POST /products`
        - Edit Product: `PUT /products/{productId}`
        - Delete Product: `DELETE /products/{productId}`
        - Get All Products: `GET /products`
        - Get Published Products: `GET /products/published`
        - Get Unpublished Products: `GET /products/unpublished`

4. **Stop the Docker Container:**

    ```bash
    docker stop product
    ```

    Ensure to stop the container when you are done testing.

## Important Note

- Ensure that Docker is installed on your machine before running the above commands.
- Adjust port numbers and container names according to your preferences.
