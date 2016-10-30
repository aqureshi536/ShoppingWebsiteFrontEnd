# E-Commerce Website
 This an ecommerce website which is built using spring and hibernate which makes it database independent. It is divided into two parts frontend and backend which makes it scalable.
 
 It has a role based access mechanism i.e Customer and Admin and also has a stock managemaent system for products. It is secured using Spring Security which satisfies all the modern security needs for a website.
 
 Admin - He will be able to add, delete, update any of the product, category, supplier.
 
 Customer -They will be able to browse all the products, perform cart operations ( add a product, remove or update their product quantity ) and buy as well.
 
### Prerequisites
 - JDK 1.8
 - Web Server supporting Servlets
- Apache Maven
- H2 database
- Modern web browser supporting HTML 5, CSS 3 and ECMAScript 6


 Clone its backend project or else it will not work  ! Important

[Shopping website Backend ]    <https://github.com/aqureshi536/ShoppingWebsiteBackEnd.git>

### Configuration
1. Switch to the directory location where you want to clone the repository and type  command
    ```sh
    $ git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY
    ```
    
 2. Right click on project  -> Maven -> Update Project -> check force update snapshots/releases -> Ok  , if using eclipse.

3. Run all the create table queries present in databaseQueries.sql file in backend
<https://github.com/aqureshi536/ShoppingWebsiteBackEnd/blob/master/databaseQueries.sql>


4.Add the project to server and run the server.
5. After running server please run following query in h2 console to access admin account with username 'admin' and password 'admin'.
 ```sh
 insert into users (username,password,enabled,customerId) values
 ('admin',admin,true,'admin001');
  ``` 
  ```sh
 insert into user_authorities (username,authority) values ('admin','ROLE_ADMIN');
 
 ``` 
 
6. Enter the ip address of your machine ( 'localhost' if running locally) and port on which your web server runs.

Example --
 ```sh
  http://localhost:8080/ShoppingWebsite/
```
###### Note - Configurations may differ for different Operating Systems.
### Technologies used 
- Spring framework
- Hibernate ORM tool
- Angular JS
- Bootstrap 3
- HTML 5
- CSS 3

### Tests
###### Junit Test cases

### Contributors
###### All roles were played alone 

### License
###### Qureshi Ahmad



 
 
 