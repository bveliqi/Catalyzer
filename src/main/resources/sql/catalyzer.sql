-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 11. Okt 2014 um 12:22
-- Server Version: 5.5.25
-- PHP-Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Datenbank: `catalyzer`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `PROJECTS`
--

CREATE TABLE `PROJECTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authorId` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `motivation` longtext NOT NULL,
  `photoUrl` varchar(255) NOT NULL,
  `category` varchar(150) NOT NULL,
  `pointsThreshold` int(11) NOT NULL DEFAULT '50',
  `status` varchar(50) NOT NULL DEFAULT 'PENDING',
  `startDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_idx` (`authorId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `USERS`
--

CREATE TABLE `USERS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `emailConfirmed` bit(1) DEFAULT NULL,
  `points` int(11) NOT NULL DEFAULT '10',
  `totalPoints` int(11) NOT NULL DEFAULT '10',
  `avatar` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `USERS_PROJECTS`
--

CREATE TABLE `USERS_PROJECTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `projectId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `role` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL DEFAULT 'APPLYING',
  PRIMARY KEY (`id`),
  KEY `project_id_idx` (`projectId`),
  KEY `user_id_idx` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Constraints der exportierten Tabellen
--

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

