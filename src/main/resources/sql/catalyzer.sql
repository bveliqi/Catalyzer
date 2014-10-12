-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 12. Okt 2014 um 05:52
-- Server Version: 5.5.25
-- PHP-Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Datenbank: `catalyzer`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `EVENTS`
--

CREATE TABLE `EVENTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `projectId` bigint(20) NOT NULL,
  `message` varchar(150) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `projectId_idx` (`projectId`),
  KEY `userId_idx` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `FOLLOWERS`
--

CREATE TABLE `FOLLOWERS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `followerId` bigint(20) NOT NULL,
  `followingId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `PROJECTS`
--

CREATE TABLE `PROJECTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authorId` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `motivation` varchar(4096) NOT NULL,
  `photoUrl` varchar(255) NOT NULL,
  `category` varchar(150) NOT NULL,
  `pointsThreshold` int(11) NOT NULL DEFAULT '50',
  `status` varchar(50) NOT NULL DEFAULT 'PENDING',
  `startDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endDate` timestamp NULL DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `author_idx` (`authorId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `USERS`
--

CREATE TABLE `USERS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `emailConfirmed` bit(1) DEFAULT NULL,
  `points` int(11) NOT NULL DEFAULT '10',
  `totalPoints` int(11) NOT NULL DEFAULT '10',
  `avatar` int(11) DEFAULT NULL,
  `longitude` varchar(45) DEFAULT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  `motivation` varchar(4096) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=100000007 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `USERS_PROJECTS`
--

CREATE TABLE `USERS_PROJECTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `projectId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `role` varchar(45) NOT NULL,
  `state` varchar(45) DEFAULT 'APPLYING',
  `reason` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_id_idx` (`projectId`),
  KEY `user_id_idx` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `EVENTS`
--
ALTER TABLE `EVENTS`
ADD CONSTRAINT `projectId` FOREIGN KEY (`projectId`) REFERENCES `PROJECTS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `USERS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `PROJECTS`
--
ALTER TABLE `PROJECTS`
ADD CONSTRAINT `author` FOREIGN KEY (`authorId`) REFERENCES `USERS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `USERS_PROJECTS`
--
ALTER TABLE `USERS_PROJECTS`
ADD CONSTRAINT `project_id` FOREIGN KEY (`projectId`) REFERENCES `PROJECTS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `user_id` FOREIGN KEY (`userId`) REFERENCES `USERS` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
