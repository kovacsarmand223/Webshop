-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Nov 13. 17:52
-- Kiszolgáló verziója: 10.4.28-MariaDB
-- PHP verzió: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `webshop`
--
CREATE DATABASE IF NOT EXISTS `webshop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;
USE `webshop`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `felhasznalonev` varchar(50) NOT NULL,
  `modositasdatuma` date DEFAULT NULL,
  `modositasneve` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `belekerul`
--

DROP TABLE IF EXISTS `belekerul`;
CREATE TABLE `belekerul` (
  `kosarid` int(10) NOT NULL,
  `termekazonosito` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felhasznalo`
--

DROP TABLE IF EXISTS `felhasznalo`;
CREATE TABLE `felhasznalo` (
  `felhasznalonev` varchar(50) NOT NULL,
  `jelszo` varchar(64) NOT NULL,
  `vezeteknev` varchar(50) NOT NULL,
  `keresztnev` varchar(50) NOT NULL,
  `jogosultsag` int(1) NOT NULL DEFAULT 0,
  `telepules` varchar(50) NOT NULL,
  `utca` varchar(50) NOT NULL,
  `hazszam` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `feltolt`
--

DROP TABLE IF EXISTS `feltolt`;
CREATE TABLE `feltolt` (
  `felhasznalonev` varchar(50) NOT NULL,
  `termekazonosito` int(10) NOT NULL,
  `mikor` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kosar`
--

DROP TABLE IF EXISTS `kosar`;
CREATE TABLE `kosar` (
  `kosarid` int(10) NOT NULL,
  `modositasdatuma` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `megrendel`
--

DROP TABLE IF EXISTS `megrendel`;
CREATE TABLE `megrendel` (
  `felhasznalonev` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci NOT NULL,
  `termekid` int(50) DEFAULT NULL,
  `mikor` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `termek`
--

DROP TABLE IF EXISTS `termek`;
CREATE TABLE `termek` (
  `termekazonosito` int(10) NOT NULL,
  `nev` varchar(50) NOT NULL,
  `gyarto` varchar(50) DEFAULT NULL,
  `leiras` varchar(100) DEFAULT NULL,
  `ar` int(10) NOT NULL,
  `elerhetoe` int(1) NOT NULL,
  `szarmazasiorszag` varchar(50) DEFAULT NULL,
  `osztalyzas` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `vasarlo`
--

DROP TABLE IF EXISTS `vasarlo`;
CREATE TABLE `vasarlo` (
  `felhasznalonev` varchar(50) NOT NULL,
  `kosarid` int(10) NOT NULL,
  `mikor` date DEFAULT NULL,
  `utolsobejelentkezes` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `admin`
--
ALTER TABLE `admin`
  ADD KEY `felhasznalonev` (`felhasznalonev`);

--
-- A tábla indexei `belekerul`
--
ALTER TABLE `belekerul`
  ADD KEY `kosarid` (`kosarid`),
  ADD KEY `termekazonosito` (`termekazonosito`);

--
-- A tábla indexei `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`felhasznalonev`);

--
-- A tábla indexei `feltolt`
--
ALTER TABLE `feltolt`
  ADD KEY `termekazonosito` (`termekazonosito`),
  ADD KEY `index` (`felhasznalonev`);

--
-- A tábla indexei `kosar`
--
ALTER TABLE `kosar`
  ADD PRIMARY KEY (`kosarid`);

--
-- A tábla indexei `megrendel`
--
ALTER TABLE `megrendel`
  ADD KEY `felhasznalonev` (`felhasznalonev`,`termekid`),
  ADD KEY `megrendel_ibfk_2` (`termekid`);

--
-- A tábla indexei `termek`
--
ALTER TABLE `termek`
  ADD PRIMARY KEY (`termekazonosito`);

--
-- A tábla indexei `vasarlo`
--
ALTER TABLE `vasarlo`
  ADD KEY `felhasznalonev` (`felhasznalonev`),
  ADD KEY `kosarid` (`kosarid`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `termek`
--
ALTER TABLE `termek`
  MODIFY `termekazonosito` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `belekerul`
--
ALTER TABLE `belekerul`
  ADD CONSTRAINT `belekerul_ibfk_1` FOREIGN KEY (`kosarid`) REFERENCES `kosar` (`kosarid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `belekerul_ibfk_2` FOREIGN KEY (`termekazonosito`) REFERENCES `termek` (`termekazonosito`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `feltolt`
--
ALTER TABLE `feltolt`
  ADD CONSTRAINT `feltolt_ibfk_1` FOREIGN KEY (`termekazonosito`) REFERENCES `termek` (`termekazonosito`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `feltolt_ibfk_2` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `megrendel`
--
ALTER TABLE `megrendel`
  ADD CONSTRAINT `megrendel_ibfk_1` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `megrendel_ibfk_2` FOREIGN KEY (`termekid`) REFERENCES `termek` (`termekazonosito`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Megkötések a táblához `vasarlo`
--
ALTER TABLE `vasarlo`
  ADD CONSTRAINT `vasarlo_ibfk_1` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vasarlo_ibfk_2` FOREIGN KEY (`kosarid`) REFERENCES `kosar` (`kosarid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
