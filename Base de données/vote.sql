-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 15 fév. 2019 à 15:38
-- Version du serveur :  5.7.21
-- Version de PHP :  7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de données :  vote
--
CREATE DATABASE IF NOT EXISTS vote DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE vote;

-- --------------------------------------------------------

--
-- Structure de la table administrateur
--

CREATE TABLE IF NOT EXISTS administrateur (
  username varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  password varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY username (username),
  UNIQUE KEY password (password)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table candidat
--

CREATE TABLE IF NOT EXISTS candidat (
  id int(2) NOT NULL,
  photo varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table personnes
--

CREATE TABLE IF NOT EXISTS personnes (
  id int(11) NOT NULL AUTO_INCREMENT,
  noms varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  prenoms varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  sexe varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  email varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  cni varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  contact varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  candidat int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY email (email),
  UNIQUE KEY cni (cni),
  UNIQUE KEY contact (contact)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
COMMIT;