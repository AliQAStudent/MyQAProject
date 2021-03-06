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

This API lets suppliers unload new stock to the shop inventory. It allows to add a set amount of new ice creams of a single type. 

`localhost:8080/loadStock`

Sample body:
```
{
    "type": "cone",
    "amount":5
}
```
The response includes a list with all the current updated stock.

## Supplier API: Update Prices (POST)

This API allows suppliers to update the shop's selling prices for a given type of ice cream. 

`localhost:8080/updatePrices`

Sample body:
```
{
    "type": "cone",
    "newPrice":5
}
```
The response includes a list with all the current ice creams and their corresponding price

## Admin API: Get All Stock (GET)

`localhost:8080/getAllStock`

This API returns a full list with all existing current stock

## Admin API: Get All Prices (GET)

`localhost:8080/getAllPrices`

This API returns a full list with all existing current prices

## Admin API: Get Today's earnings (GET)

`localhost:8080/getTodaysEarnings`

This API returns an integer representing today's total earnings

## Admin API: Get All Sales (GET)

`localhost:8080/getAllSales`

This API returns a list containing all sales (for all time) 

## Customer API: Purchase Ice Cream (POST)

This API lets customers purchase a single ice cream. It updates the stock in the backend by decreasing by 1 the amount of ice creams in stock with the given type. It also creates a sales record so that admins can see the sale with their APIs. 

`localhost:8080/loadStock`

Sample body:
```
{
    "type": "cone"
}
```

Response: a string (SUCCESS/NO STOCK)

# Answers to Project's Questions

## Why are we doing this?

We are creating a project to demonstrate we have learned the required skills taught during this course

## How I expected the challenge to go

I expected to have to remember key parts of my training. 

## What went well? / What didn't go as planned?

Being able to go back to my lessons was a big help. Setting up the project took some effort... had to re-create several times. Eclipse also threw some strange errors that I solved with the help of Google

## Possible improvements for future revisions of the project.

More variations of ice cream... perhaps flavours. I would also look deeper into JPA

## Screenshots showing your postman requests and the output from the API.

https://imgur.com/T19TX0b

https://imgur.com/gxX86V3

https://imgur.com/zwGHoxJ

https://imgur.com/UYupbJ2

https://imgur.com/OrKAeD5

https://imgur.com/nlVk05Q

https://imgur.com/SXZJyva

## Screenshots of your database to prove that data is being persisted.

https://imgur.com/vv6qk5m

## Screenshot of your test results, including coverage report.

https://imgur.com/K548HUR

https://imgur.com/4GOkhMS

## Link to Jira Board - You must add your trainer(s) as collaborators also.

https://example2022.atlassian.net/jira/software/projects/MYQ/boards/3