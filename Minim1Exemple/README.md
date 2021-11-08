Implementation façade about Minimo1 using Singleton pattern. I also create a REST service that implements the functions delcared in the interface ProductManager

Explanation about API:

Services:

1.Text /text
Methods:
	1. GET:
		- Return "Got it!" /basic
		- Return An Exception /exception
		- Return Hello "username" given a username /users/:username

2.Orders /orders
Methods:
	1. GET:
		- Get all products in the list /products
		- Get all products sorted by price /sortedbyprice
		- Get all products sorted by number of sales /sortedbysales
		- Get all orders of a user /:user
	2. POST:
		- Place an order /placeanorder/:user
	3. DELETE:
		- Serve an order /serveanorder

Models:

1. Usuario:
	- String username

2. Producto:
	- String name
	- Double price
	- Integer sales

3. Pedido:
	1. LProducto:
		- Integer q
		- String producto
	2. Usuario:
		- String username

4. LProducto:
	- Integer q
	- String username	
