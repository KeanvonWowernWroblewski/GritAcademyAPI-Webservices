-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Värd: 127.0.0.1:1337
-- Tid vid skapande: 29 mars 2024 kl 10:39
-- Serverversion: 10.4.32-MariaDB
-- PHP-version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `gritacademywebservices`
--

-- --------------------------------------------------------

--
-- Tabellstruktur `courses`
--

CREATE TABLE `courses` (
  `id` bigint(20) NOT NULL,
  `name` text NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `courses`
--

INSERT INTO `courses` (`id`, `name`, `description`) VALUES
(1, 'konsultrollen', 'lär dig bli konsult'),
(2, 'javaprogrammering', 'grunderna i java'),
(3, 'javascript', 'grunderna i javascript'),
(4, 'databaser', 'grunderna i databaser'),
(5, 'java webservices', 'grunderna i java webservices'),
(6, 'test', 'test');

-- --------------------------------------------------------

--
-- Tabellstruktur `students`
--

CREATE TABLE `students` (
  `id` bigint(20) NOT NULL,
  `fName` text NOT NULL,
  `lName` text NOT NULL,
  `town` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `students`
--

INSERT INTO `students` (`id`, `fName`, `lName`, `town`) VALUES
(1, 'Bobby', 'Grönlid', 'malmö'),
(2, 'Henrik', 'Dorsin', 'stockholm'),
(3, 'karlssons', 'klister', 'borås'),
(4, 'Gurkan', 'hansson', 'skellefteå'),
(5, 'Coco', 'persson', 'lund'),
(6, 'per', 'alfons', 'tomelilla'),
(7, 'harry', 'potter', 'london'),
(8, 'sirius', 'black', 'london'),
(9, 'ceasar ', 'sallad', 'rom'),
(10, 'lisbeth', 'salander', 'stockholm'),
(11, 'test', 'test', 'test');

-- --------------------------------------------------------

--
-- Tabellstruktur `students_courses`
--

CREATE TABLE `students_courses` (
  `id` bigint(20) NOT NULL,
  `students_id` bigint(20) NOT NULL,
  `courses_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `students_courses`
--

INSERT INTO `students_courses` (`id`, `students_id`, `courses_id`) VALUES
(1, 1, 4),
(2, 9, 2),
(3, 5, 3),
(4, 4, 5),
(5, 10, 2),
(6, 6, 3),
(7, 8, 5),
(8, 7, 1),
(9, 11, 6);

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `students_courses`
--
ALTER TABLE `students_courses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `courses_id` (`courses_id`),
  ADD KEY `students_id` (`students_id`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `courses`
--
ALTER TABLE `courses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT för tabell `students`
--
ALTER TABLE `students`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT för tabell `students_courses`
--
ALTER TABLE `students_courses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restriktioner för dumpade tabeller
--

--
-- Restriktioner för tabell `students_courses`
--
ALTER TABLE `students_courses`
  ADD CONSTRAINT `students_courses_ibfk_1` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `students_courses_ibfk_2` FOREIGN KEY (`students_id`) REFERENCES `students` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
