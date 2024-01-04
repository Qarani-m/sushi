# Shushi API Documentation


## Introduction

The Sushi API provides endpoints to manage sushi items, including creating, updating, deleting, and filtering.

## Base URL

The base URL for the API is `/api/sushi`.

## Endpoints

### Get all sushi items

- **Endpoint:** `/all`
- **Method:** `GET`
- **Description:** Retrieve a list of all sushi items.
- **Example URL:** `/api/sushi/all`

### Get a specific sushi item by ID

- **Endpoint:** `/one/{sushiId}`
- **Method:** `GET`
- **Description:** Retrieve details of a specific sushi item by its ID.
- **Example URL:** `/api/sushi/one/{sushiId}`
- **Example Response:**
  ```json
  {
    "name": "California Roll",
    "price": 8.99,
    "description": "A delicious roll with crab, avocado, and cucumber.",
    "stars": 4,
    "category": "Sushi",
    "imageUrl": "https://example.com/california-roll.jpg"
  }
  ```

### Create a new sushi item

- **Endpoint:** `/admin/create`
- **Method:** `POST`
- **Description:** Create a new sushi item.
- **Example URL:** `/api/sushi/admin/create`
- **Example Request Body:**
  ```json
  {
    "name": "Spicy Tuna Roll",
    "price": 10.99,
    "description": "A flavorful roll with spicy tuna and avocado.",
    "stars": 5,
    "category": "Sushi",
    "imageUrl": "https://example.com/spicy-tuna-roll.jpg"
  }
  ```
- **Example Response:**
  ```json
  {
    "name": "Spicy Tuna Roll",
    "price": 10.99,
    "description": "A flavorful roll with spicy tuna and avocado.",
    "stars": 5,
    "category": "Sushi",
    "imageUrl": "https://example.com/spicy-tuna-roll.jpg"
  }
  ```

### Update a sushi item by ID

- **Endpoint:** `/admin/update/{sushiId}`
- **Method:** `PUT`
- **Description:** Update details of a specific sushi item by its ID.
- **Example URL:** `/api/sushi/admin/update/{sushiId}`
- **Example Request Body:**
  ```json
  {
    "name": "Updated Spicy Tuna Roll",
    "price": 12.99,
    "description": "An updated flavorful roll with spicy tuna and avocado.",
    "stars": 4,
    "category": "Sushi",
    "imageUrl": "https://example.com/updated-spicy-tuna-roll.jpg"
  }
  ```
- **Example Response:**
  ```json
  {
    "name": "Updated Spicy Tuna Roll",
    "price": 12.99,
    "description": "An updated flavorful roll with spicy tuna and avocado.",
    "stars": 4,
    "category": "Sushi",
    "imageUrl": "https://example.com/updated-spicy-tuna-roll.jpg"
  }
  ```

### Delete a sushi item by ID

- **Endpoint:** `/admin/delete/{sushiId}`
- **Method:** `DELETE`
- **Description:** Delete a specific sushi item by its ID.
- **Example URL:** `/api/sushi/admin/delete/{sushiId}`
- **Example Response:**
    - `204 No Content` if deletion is successful
    - `404 Not Found` if the sushi item with the specified ID is not found

### Filter sushi items

- **Endpoint:** `/filter`
- **Method:** `GET`
- **Description:** Filter sushi items based on price range, category, and stars.
- **Example URL:** `/api/sushi/filter?minPrice=10.0&maxPrice=20.0&category=Sushi&stars=4`
- **Example Response:**
  ```json
  [
    {
      "name": "Spicy Tuna Roll",
      "price": 12.99,
      "description": "A flavorful roll with spicy tuna and avocado.",
      "stars": 4,
      "category": "Sushi",
      "imageUrl": "https://example.com/spicy-tuna-roll.jpg"
    },
    {
      "name": "Dragon Roll",
      "price": 15.99,
      "description": "An exquisite roll with eel, avocado, and cucumber.",
      "stars": 5,
      "category": "Sushi",
      "imageUrl": "https://example.com/dragon-roll.jpg"
    }
  ]
  ```

***



# Cart API Documentation

## Introduction

The Cart API provides endpoints to manage the shopping cart, including creating, updating, and retrieving cart contents.

## Base URL

The base URL for the API is `/api/private/cart`.

## Endpoints

### Create a new cart

- **Endpoint:** `/create`
- **Method:** `POST`
- **Description:** Create a new cart for a user.
- **Example URL:** `/api/private/cart/create`
- **Example Request Body:**
  ```json
  {
    "userId": "user123"
  }
  ```
- **Example Response:**
  ```json
  {
    "idd": "user123",
    "items": [],
    "userId": "6595536e2d22290bf5a2ad9a"
  }
  ```

### Add an item to the cart

- **Endpoint:** `/addItem/{userId}`
- **Method:** `POST`
- **Description:** Add an item to the user's cart.
- **Example URL:** `/api/private/cart/addItem/user123`
- **Example Request Body:**
  ```json
  {
    "itemId":"6595536e2d22290bf5a2ad9a"
  }
  ```
  - **Example Response:**
    ```json
    {
      "id": "65955a1a2fd0295eb81c833c",
      "items": [
          {
              "sushi": {
                  "id": "6595536e2d22290bf5a2ad9a",
                  "name": "Sushi Example",
                  "price": 12.79,
                  "description": "A delicious sushi roll example.",
                  "stars": 5,
                  "category": "Sushi",
                  "imageUrl": "https://example.com/sushi-example.jpg"
              },
              "quantity": 1
          }
      ],
      "userId": "3"
    }
    ```

### Remove an item from the cart

- **Endpoint:** `/removeItem/{userId}`
- **Method:** `POST`
- **Description:** Remove an item from the user's cart.
- **Example URL:** `/api/private/cart/removeItem/user123`
- **Example Request Body:**
  ```json
  {
    "itemId":"6595536e2d22290bf5a2ad9a"
  }
  ```
- **Example Response:**
  ```json
  {
    "id": "user123",
    "items": [],
    "userId": "6595536e2d22290bf5a2ad9a"
  }
  ```

### Get the contents of the cart

- **Endpoint:** `/all/{userId}`
- **Method:** `GET`
- **Description:** Retrieve the contents of the user's cart.
- **Example URL:** `/api/private/cart/all/user123`
- **Example Response:**
  ```json
  {
	"id": "65955a1a2fd0295eb81c833c",
	"items": [
		{
			"sushi": {
				"id": "6595536e2d22290bf5a2ad9a",
				"name": "Sushi Example",
				"price": 12.79,
				"description": "A delicious sushi roll example.",
				"stars": 5,
				"category": "Sushi",
				"imageUrl": "https://example.com/sushi-example.jpg"
			},
			"quantity": 1
		},
		{
			"sushi": {
				"id": "6595536e2d22290bf5a2ad9a",
				"name": "Sushi Example",
				"price": 12.79,
				"description": "A delicious sushi roll example.",
				"stars": 5,
				"category": "Sushi",
				"imageUrl": "https://example.com/sushi-example.jpg"
			},
			"quantity": 1
		}
	],
	"userId": "3"
  }
  ```

### Clear the contents of the cart

- **Endpoint:** `/clear-cart/{userId}`
- **Method:** `GET`
- **Description:** Clear all items from the user's cart.
- **Example URL:** `/api/private/cart/clear-cart/user123`
- **Example Response:**
  - `204 No Content`

### Get the number of items in the cart

- **Endpoint:** `/numberOfItems/{userId}`
- **Method:** `GET`
- **Description:** Retrieve the number of items in the user's cart.
- **Example URL:** `/api/private/cart/numberOfItems/user123`
- **Example Response:**
  ```json
  3
  ```

### Increase quantity of an item in the cart

- **Endpoint:** `/increaseQuantity/{userId}`
- **Method:** `POST`
- **Description:** Increase the quantity of a specific item in the user's cart.
- **Example URL:** `/api/private/cart/increaseQuantity/user123`
- **Example Request Body:**
  ```json
  {
    "itemId":"6595536e2d22290bf5a2ad9a"
  }
  ```
- **Example Response:**
  ```json
  {
    "userId": "user123",
    "items": [
      {
        "itemId": "item123",
        "quantity": 3
      },
      {
        "itemId": "item456",
        "quantity": 1
      }
    ],
    "totalItems": 4
  }
  ```

### Reduce quantity of an item in the cart

- **Endpoint:** `/reduceQuantity/{userId}`
- **Method:** `POST`
- **Description:** Reduce the quantity of a specific item in the user's cart.
- **Example URL:** `/api/private/cart/reduceQuantity/user123`
- **Example Request Body:**
  ```json
  {
    "itemId":"6595536e2d22290bf5a2ad9a"
  }
  ```
- **Example Response:**
  ```json
  {
    "userId": "user123",
    "items": [
      {
        "itemId": "item123",
        "quantity": 2
      },
      {
        "itemId": "item456",
        "quantity": 1
      }
    ],
    "totalItems": 3
  }
  ```




















