
# Cart Endpoints

## 1:Create cart

Endpoint for creating a new shopping cart.



`POST /api/private/cart/create`



### Request Body

- **Type**: JSON Object
- **Content-Type**: application/json

#### Example

```json
{
  "userId":"<userId>"
}
```
## 2: Add Item to Cart

Endpoint for adding an item to a user's shopping cart.

## Endpoint

`POST /api/private/cart/addItem/<userId>`

- **{userId}**: ID of the user to whom the item will be added.

## Request

### Request Body

- **Type**: JSON Object
- **Content-Type**: application/json

#### Example

```json
{
  "sushi": {
    "name": "California Roll",
    "price": 8.99,
    "description": "A delicious roll with crab, avocado, and cucumber.",
    "stars": 4,
    "category": "Sushi",
    "imageUrl": "https://example.com/california-roll.jpg"
  },
  "quantity": 2
}
