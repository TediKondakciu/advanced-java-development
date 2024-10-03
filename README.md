# Sporty Shoes E-commerce Web Application

This is a web application developed using Java, Spring framework, and Thymeleaf for the view, where a company called Sporty Shoes wishes to launch their e-commerce. This project uses MySQL database to store the data and has two types of users: an admin and customers/normal users.

## Admin Functionality

Admin is initialized at the beginning when the application first starts up and has an email address and a password with which they can log into the home page of the webapp. At the home page, all users can find the shoes which are for sale, and two options: either to log in if they are already registered, or to register if they want to sign up and use the page to purchase products.

After logging in, admin is directed to the dashboard, which is personalized for admin only. There, the admin can:
- View the products or the orders with their respective details.
- Add or delete products.
- Place orders.

## Customer/Normal User Functionality

Normal users or customers have their own dashboard, which appears after they log in. Customers can:
- View available products.
- Place orders.
- View their own orders.

After placing an order, a confirmation page appears to confirm the successful orders.

## Database Design

Each action is stored in the MySQL database, which has three tables:
1. **Login**: Stores user details.
2. **Orders**: Stores each order detail.
3. **Shoe**: Stores the product details.
