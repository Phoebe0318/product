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

