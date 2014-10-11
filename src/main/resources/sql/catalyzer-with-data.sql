-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 11. Okt 2014 um 19:56
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
  `motivation` varchar(500) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Daten für Tabelle `PROJECTS`
--

INSERT INTO `PROJECTS` (`id`, `authorId`, `name`, `motivation`, `photoUrl`, `category`, `pointsThreshold`, `status`, `startDate`, `endDate`, `longitude`, `latitude`, `points`) VALUES
(14, 13, 'Helping ppl in neighbourhood', 'Helping ppl', '/a/b/c.jpg', 'Whatever', 30, 'OK', '2014-10-11 12:25:19', NULL, NULL, NULL, 0),
(15, 13, 'Helping ppl in neighbourhood', 'Helping ppl', '/a/b/c.jpg', 'Whatever', 30, 'OK', '2014-10-11 12:32:36', NULL, NULL, NULL, 0),
(16, 13, 'Helping ppl in neighbourhood', 'Helping ppl', '/a/b/c.jpg', 'Whatever', 30, 'OK', '2014-10-11 13:12:40', NULL, NULL, NULL, 0),
(17, 13, 'Helping ppl in neighbourhood', 'Helping ppl', '/a/b/c.jpg', 'Whatever', 30, 'OK', '2014-10-11 13:13:06', NULL, 0, 0, 0),
(18, 13, 'Helping ppl in neighbourhood', 'Helping ppl', '/a/b/c.jpg', 'Whatever', 30, 'OK', '2014-10-11 17:24:27', '2014-10-11 13:13:09', 0, 0, 555),
(19, 13, 'NEW NAME', 'huhuuuuu', '/a/b/c.jpg', 'Whatever', 30, 'OK', '2014-10-11 17:53:49', '2014-10-11 13:13:09', 0, 0, 12);

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
  `longitude` varchar(45) DEFAULT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Daten für Tabelle `USERS`
--

INSERT INTO `USERS` (`id`, `name`, `email`, `password`, `emailConfirmed`, `points`, `totalPoints`, `avatar`, `longitude`, `latitude`) VALUES
(13, 'Behar Veliqi', 'behar@veliqi.de', 'klfrhekfhkshfkjd', '\0', 7, 0, 0, NULL, NULL),
(14, 'Alej Andro', 'behar@veliqi.de', 'klfrhekfhkshfkjd', '\0', 0, 0, 0, NULL, NULL),
(15, 'Alej Andro', 'behar@veliqi.de', 'klfrhekfhkshfkjd', '', 0, 0, 0, NULL, NULL),
(16, 'Alej Andro', 'behar@veliqi.de', 'klfrhekfhkshfkjd', '', 0, 0, 0, NULL, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Daten für Tabelle `USERS_PROJECTS`
--

INSERT INTO `USERS_PROJECTS` (`id`, `projectId`, `userId`, `role`, `state`) VALUES
(5, 14, 13, 'MENTOR', 'ACCEPTED'),
(6, 15, 13, 'DEVELOPER', 'APPLYING'),
(7, 14, 13, 'WHATEVER', 'APPLYING'),
(8, 15, 13, 'SOMETHINGELSE', 'ACCEPTED');

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
