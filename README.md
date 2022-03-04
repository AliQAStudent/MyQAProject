# This is my final project for my QA Course

I have chosen to create a Spring Boot REST API project that offers endpoints that manage an ice cream shop. The backend connects and persists data to a MySQL database. Below are the required SQL scripts to initialize the DB:

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

//TODO Explain API Specs