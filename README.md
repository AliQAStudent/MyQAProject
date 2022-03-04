# This is my final project for my QA Course

I have chosen to create a Spring Boot REST API project that offers endpoints that manage an ice cream shop. 

The ice cream shop sells three different type of ice creams: cone, lollie, and tub. Suppliers can load more stock to the shop or alter the selling prices, shop admins can check stock levels and query today's total earnings, and customers can order ice creams. All these operations are done via API

The backend connects and persists data to a MySQL database. Below are the required SQL scripts to initialize the DB:

```
CREATE SCHEMA `icecream_shop` ;
CREATE TABLE `icecream_shop`.`stock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `icecream_shop`.`prices` (
  `type` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL);

CREATE TABLE `icecream_shop`.`sales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `item_price` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`));
insert into icecream_shop.prices (type,price) values ('cone',4);
insert into icecream_shop.prices (type,price) values ('tub',3);
insert into icecream_shop.prices (type,price) values ('lollie',2);
insert into icecream_shop.stock (type,amount) values('cone',0);
insert into icecream_shop.stock (type,amount) values('tub',0);
insert into icecream_shop.stock (type,amount) values('lollie',0);
```

The Jira project can be found on https://example2022.atlassian.net/jira/software/projects/MYQ/boards/3 which uses a Kanban Board to manage all tasks. A link to each commit is seen in each task and an estimate is mentioned in each task

A self-executable Jar and "Fat Jar" can be found in the target directory

# To run the program, use the following endpoints:

## Supplier API: Load Stock (POST)

This API lets suppliers unload new stock to the shop inventory. It allows to add a set amount of new ice creams of a single type. The response includes a list with all the current updated stock.

`localhost:8080/loadStock`

Sample body:
```
{
    "type": "cone",
    "amount":5
}
```

## Supplier API: Update Prices (POST)

This API allows suppliers to update the shop's selling prices for a given type of ice cream. The response includes a list with all the current ice creams and their corresponding price

`localhost:8080/updatePrices`

Sample body:
```
{
    "type": "cone",
    "newPrice":5
}
```

## Admin API: Get All Stock (GET)

`localhost:8080/getAllStock`

This API returns a full list with all existing current stock

## Admin API: Get Today's earnings (GET)

`localhost:8080/getTodaysEarnings`

This API returns an integer representing today's total earnings

## Admin API: Get All Sales (GET)

`localhost:8080/getAllSales`

This API returns a list containing all sales (for all time) 

