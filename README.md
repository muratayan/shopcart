shopcart / online pizza store application
========

Software Architecture with Java EE 6 in Uppsala University


Description and Usage

As line of business we chose to design a website for local Flogsta pizza place. So that customers are only the students who accommodates in Flogsta. Shop is currently offering four different types of pizzas with the combination of four different toppings (Tomato, Cheese, Pepperoni and Mushroom). 
Since we use JDBC Realm for user authentication, we needed to save passwords as SHA-256 hashed. Because of we couldn’t find a suitable library to perform this hash, we currently cannot accept new customers.
For using our website, users must login into system first. After this login screen, user will be forwarded to menu page. In this page, they can examine the pizzas; add them to their cart as many as they want. However, if the shop doesn’t have enough toppings for all the toppings, users will see a warning that their pizzas will be preparing soon (after shop owner buys some more toppings). This process on the background however, records the order no matter what, prepares the pizzas which they can and puts ‘sent’ status to them, ‘not sent’ status to pizzas waiting for new toppings.
User can also change his/her name and address in the profile section but not their username.

If an admin logs into the system, they will see one more additional menu which they can see the current status of toppings and they can order more toppings from the dealer. This admin section is protected by JDBC realm, only the users which have ‘admin’ role can enter this part of the site.







Technical Details
Servlets:
-	LoginServ: This servlet is called from login.jsp checks the username/password combination and forwards to user either to index.jsp or back to login.jsp with an error message. If login succeeds, it puts the username in the session scope.

-	LogoutServ: Logs out the user and removes username from session scope.

-	welcomeServ: 	This is the dispatcher in our application all requests will go through it. It handles this requests by checking the ‘action’ hidden parameter.

Beans: 
-	PizzaBean: Represents an individual pizza.

-	PizzaListBean: Consisted of PizzaBeans. Used for constructing the menu. First creates PizzaBeans with the data it retrieves from the MySQL database.

-	ShoppingBean: Consisted of Object type array. This array represents a PizzaBean and the count of that pizza’s order.

-	ProfileBean: Represents the current user. Retrieves name and address data from database. It is also used for updating profiles.

-	OrderBean:   Prepares and order using ShoppingBean and the ProfileBean to put an order to the database. If there is not enough toppings, tells the shop wait for them.

-	StuffBean: Represents the toppings in the stock. Also used for ordering toppings by the admin.

JSP’s:
-	On the view side JSTL and EL are used for iterating over beans or reaching session/request scope attributes.

