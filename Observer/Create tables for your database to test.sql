
/**
 * Author:  Mees Buschman
 * Created: 28-jan-2018
 * Copy and paste the follow query to create your tables.
 * In Database class you can change the username, password and databasename
 * for your own database.
 */


CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastLogin` datetime DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `addonName` varchar(45) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `user_userId` int(11) NOT NULL,
  PRIMARY KEY (`accountId`),
  KEY `fk_account_user_idx` (`user_userId`),
  CONSTRAINT `fk_account_user` FOREIGN KEY (`user_userId`) REFERENCES `test`.`user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;