# WISHLISTAPP
 Spring Boot Backend Application for adding products to Wishlist

## Getting Started
### -> Clone repo in local
### -> Run
### -> NO Database installation needed (Mysql database is hosted)

### Use Postman(Preferred)

---
### 1. REGISTER USER (POST-METHOD)

 The userName and password would be used ahead , decide wisely


```
 localhost:8080/register
```

![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/1a3cc0b6-1722-4a2c-80ea-68b362192f90)

---

### 2. ADD PRODUCTS  (POST-METHOD)


```
 localHost:8080/item/addItem
```

#### Authentication (BASIC-AUTH)

![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/edb07f85-31fa-4bdc-8f9d-16392aac13c7)

#### Feed productName And ProductCategory 
![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/1a67b38a-6230-4d8e-8fc3-a0529b78c7f3)


---

### 3. GET ALL PRODUCTS (GET-METHOD)

```
localHost:8080/item/getAll
```
#### Authentication (BASIC-AUTH)

![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/d82fe84b-b8b2-4b0f-9052-da6007ddec5e)

![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/ecab5dd1-d959-47b0-bd97-3c8fb1096e6c)


---


### 4. DELETE PRODUCT BY ID (DELETE-METHOD)

```
localHost:8080/item/deleteItem
```

#### Authentication (BASIC-AUTH)

![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/6c57c7fd-12e9-4e81-9226-b9078ef1eb68)

#### Feed id param

![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/cb534e78-f7a9-4995-b165-ee7896d68a12)


---
## Access Database

```
https://railway.app/project/826c45ef-b3de-46c7-92db-549b987f759d
```
![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/01732515-3ba8-457f-93f6-d66b420ad181)

---
## Unit Testing

### 4 unit tests are added , each testing an Api's Controller , service and Repo functions alltogether.

### To run Test

#### From CMD

```
mvn test

```

#### Manually

```
File -> com/example/wishlistApp/WishlistAppApplicationTests.java
```

![image](https://github.com/Misbahrahman/WishListApp/assets/98620184/7af5d048-0e68-480d-8a9b-866eb64844d3)

Or Click on Test Run Option 

![Screenshot 2024-02-14 140530](https://github.com/Misbahrahman/WishListApp/assets/98620184/4d118b7c-9492-4126-b10b-b6399fb83a91)













