-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lanac_prodavnica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lanac_prodavnica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lanac_prodavnica` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `lanac_prodavnica` ;

-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`MJESTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`MJESTO` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`MJESTO` (
  `BrojPoste` VARCHAR(5) NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BrojPoste`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`PROIZVODJAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`PROIZVODJAC` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`PROIZVODJAC` (
  `JIB_Proizvodjaca` VARCHAR(13) NOT NULL,
  `NazivP` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefon` VARCHAR(20) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `MJESTO_BrojPoste` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`JIB_Proizvodjaca`),
  INDEX `fk_PROIZVODJAC_MJESTO1_idx` (`MJESTO_BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_PROIZVODJAC_MJESTO1`
    FOREIGN KEY (`MJESTO_BrojPoste`)
    REFERENCES `lanac_prodavnica`.`MJESTO` (`BrojPoste`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`DOBAVLJAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`DOBAVLJAC` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`DOBAVLJAC` (
  `JIB_Dobavljaca` VARCHAR(13) NOT NULL,
  `NazivD` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefon` VARCHAR(20) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `MJESTO_BrojPoste` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`JIB_Dobavljaca`),
  INDEX `fk_DOBAVLJAC_MJESTO1_idx` (`MJESTO_BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_DOBAVLJAC_MJESTO1`
    FOREIGN KEY (`MJESTO_BrojPoste`)
    REFERENCES `lanac_prodavnica`.`MJESTO` (`BrojPoste`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`PROIZVOD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`PROIZVOD` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`PROIZVOD` (
  `Barkod` VARCHAR(12) NOT NULL,
  `NazivPr` VARCHAR(45) NOT NULL,
  `Cijena` DECIMAL(12,2) NOT NULL,
  `PROIZVODJAC_JIB` VARCHAR(13) NOT NULL,
  `DOBAVLJAC_JIB_Dobavljaca` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`Barkod`),
  INDEX `fk_PROIZVOD_PROIZVODJAC1_idx` (`PROIZVODJAC_JIB` ASC) VISIBLE,
  INDEX `fk_PROIZVOD_DOBAVLJAC1_idx` (`DOBAVLJAC_JIB_Dobavljaca` ASC) VISIBLE,
  CONSTRAINT `fk_PROIZVOD_PROIZVODJAC1`
    FOREIGN KEY (`PROIZVODJAC_JIB`)
    REFERENCES `lanac_prodavnica`.`PROIZVODJAC` (`JIB_Proizvodjaca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROIZVOD_DOBAVLJAC1`
    FOREIGN KEY (`DOBAVLJAC_JIB_Dobavljaca`)
    REFERENCES `lanac_prodavnica`.`DOBAVLJAC` (`JIB_Dobavljaca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`FAKTURA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`FAKTURA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`FAKTURA` (
  `IdFakture` INT NOT NULL AUTO_INCREMENT,
  `UkupnaCijena` DECIMAL(12,2) NOT NULL,
  `DatumIzdavanja` DATETIME NOT NULL,
  PRIMARY KEY (`IdFakture`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`STAVKA_FAKTURE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`STAVKA_FAKTURE` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`STAVKA_FAKTURE` (
  `Id_Stavke` VARCHAR(45) NOT NULL,
  `FAKTURA_IdFakture` INT NOT NULL,
  `PROIZVOD_Barkod` VARCHAR(12) NOT NULL,
  `Kolicina` DECIMAL(6,2) NOT NULL,
  `Cijena` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`Id_Stavke`, `FAKTURA_IdFakture`, `PROIZVOD_Barkod`),
  INDEX `fk_FAKTURA_has_PROIZVOD_PROIZVOD1_idx` (`PROIZVOD_Barkod` ASC) VISIBLE,
  INDEX `fk_FAKTURA_has_PROIZVOD_FAKTURA_idx` (`FAKTURA_IdFakture` ASC) VISIBLE,
  CONSTRAINT `fk_FAKTURA_has_PROIZVOD_FAKTURA`
    FOREIGN KEY (`FAKTURA_IdFakture`)
    REFERENCES `lanac_prodavnica`.`FAKTURA` (`IdFakture`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FAKTURA_has_PROIZVOD_PROIZVOD1`
    FOREIGN KEY (`PROIZVOD_Barkod`)
    REFERENCES `lanac_prodavnica`.`PROIZVOD` (`Barkod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`BANKA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`BANKA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`BANKA` (
  `IdBanke` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Telefon` VARCHAR(20) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `MJESTO_BrojPoste` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`IdBanke`),
  INDEX `fk_BANKA_MJESTO1_idx` (`MJESTO_BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_BANKA_MJESTO1`
    FOREIGN KEY (`MJESTO_BrojPoste`)
    REFERENCES `lanac_prodavnica`.`MJESTO` (`BrojPoste`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`RACUN_DOBAVLJACA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`RACUN_DOBAVLJACA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`RACUN_DOBAVLJACA` (
  `BrojRacuna` VARCHAR(13) NOT NULL,
  `DOBAVLJAC_JIB_Dobavljaca` VARCHAR(13) NOT NULL,
  `BANKA_IdBanke` INT NOT NULL,
  PRIMARY KEY (`BrojRacuna`),
  INDEX `fk_RACUN_DOBAVLJACA_DOBAVLJAC1_idx` (`DOBAVLJAC_JIB_Dobavljaca` ASC) VISIBLE,
  INDEX `fk_RACUN_DOBAVLJACA_BANKA1_idx` (`BANKA_IdBanke` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_DOBAVLJACA_DOBAVLJAC1`
    FOREIGN KEY (`DOBAVLJAC_JIB_Dobavljaca`)
    REFERENCES `lanac_prodavnica`.`DOBAVLJAC` (`JIB_Dobavljaca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RACUN_DOBAVLJACA_BANKA1`
    FOREIGN KEY (`BANKA_IdBanke`)
    REFERENCES `lanac_prodavnica`.`BANKA` (`IdBanke`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (
  `Id_OrganizacioneJedinice` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `MJESTO_BrojPoste` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`Id_OrganizacioneJedinice`),
  INDEX `fk_ORGANIZACIONA_JEDINICA_MJESTO1_idx` (`MJESTO_BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_ORGANIZACIONA_JEDINICA_MJESTO1`
    FOREIGN KEY (`MJESTO_BrojPoste`)
    REFERENCES `lanac_prodavnica`.`MJESTO` (`BrojPoste`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`ZAPOSLENI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`ZAPOSLENI` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`ZAPOSLENI` (
  `JMB_Zaposlenog` VARCHAR(13) NOT NULL,
  `Ime` VARCHAR(20) NOT NULL,
  `Prezime` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefon` VARCHAR(20) NOT NULL,
  `Plata` DECIMAL(7,2) NOT NULL,
  `DatumOd` DATE NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `MJESTO_BrojPoste` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`JMB_Zaposlenog`),
  INDEX `fk_ZAPOSLENI_MJESTO1_idx` (`MJESTO_BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_MJESTO1`
    FOREIGN KEY (`MJESTO_BrojPoste`)
    REFERENCES `lanac_prodavnica`.`MJESTO` (`BrojPoste`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`ADMINISTRATOVNI_RADNIK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`ADMINISTRATOVNI_RADNIK` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`ADMINISTRATOVNI_RADNIK` (
  `ZAPOSLENI_JMB_Zaposlenog` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`ZAPOSLENI_JMB_Zaposlenog`),
  CONSTRAINT `fk_ADMINISTRATOVNI_RADNIK_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_JMB_Zaposlenog`)
    REFERENCES `lanac_prodavnica`.`ZAPOSLENI` (`JMB_Zaposlenog`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`ULAZNA_FAKTURA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`ULAZNA_FAKTURA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`ULAZNA_FAKTURA` (
  `FAKTURA_IdFakture` INT NOT NULL,
  `DOBAVLJAC_JIB_Dobavljaca` VARCHAR(13) NOT NULL,
  `RACUN_DOBAVLJACA_BrojRacuna` VARCHAR(13) NOT NULL,
  `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `ADMINISTRATOVNI_RADNIK_ZAPOSLENI_JMB_Zaposlenog` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`FAKTURA_IdFakture`),
  INDEX `fk_ULAZNA_FAKTURA_RACUN_DOBAVLJACA1_idx` (`RACUN_DOBAVLJACA_BrojRacuna` ASC) VISIBLE,
  INDEX `fk_ULAZNA_FAKTURA_DOBAVLJAC1_idx` (`DOBAVLJAC_JIB_Dobavljaca` ASC) VISIBLE,
  INDEX `fk_ULAZNA_FAKTURA_ORGANIZACIONA_JEDINICA1_idx` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  INDEX `fk_ULAZNA_FAKTURA_ADMINISTRATOVNI_RADNIK1_idx` (`ADMINISTRATOVNI_RADNIK_ZAPOSLENI_JMB_Zaposlenog` ASC) VISIBLE,
  CONSTRAINT `fk_table1_FAKTURA1`
    FOREIGN KEY (`FAKTURA_IdFakture`)
    REFERENCES `lanac_prodavnica`.`FAKTURA` (`IdFakture`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ULAZNA_FAKTURA_RACUN_DOBAVLJACA1`
    FOREIGN KEY (`RACUN_DOBAVLJACA_BrojRacuna`)
    REFERENCES `lanac_prodavnica`.`RACUN_DOBAVLJACA` (`BrojRacuna`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ULAZNA_FAKTURA_DOBAVLJAC1`
    FOREIGN KEY (`DOBAVLJAC_JIB_Dobavljaca`)
    REFERENCES `lanac_prodavnica`.`DOBAVLJAC` (`JIB_Dobavljaca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ULAZNA_FAKTURA_ORGANIZACIONA_JEDINICA1`
    FOREIGN KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (`Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ULAZNA_FAKTURA_ADMINISTRATOVNI_RADNIK1`
    FOREIGN KEY (`ADMINISTRATOVNI_RADNIK_ZAPOSLENI_JMB_Zaposlenog`)
    REFERENCES `lanac_prodavnica`.`ADMINISTRATOVNI_RADNIK` (`ZAPOSLENI_JMB_Zaposlenog`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`PRODAVNICA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`PRODAVNICA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`PRODAVNICA` (
  `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`),
  INDEX `fk_PRODAVNICA_ORGANIZACIONA_JEDINICA1_idx` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  UNIQUE INDEX `Naziv_UNIQUE` (`Naziv` ASC) VISIBLE,
  CONSTRAINT `fk_PRODAVNICA_ORGANIZACIONA_JEDINICA1`
    FOREIGN KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (`Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`TRGOVAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`TRGOVAC` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`TRGOVAC` (
  `ZAPOSLENI_JMB_Zaposlenog` VARCHAR(13) NOT NULL,
  `PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  PRIMARY KEY (`ZAPOSLENI_JMB_Zaposlenog`),
  INDEX `fk_TRGOVAC_PRODAVNICA1_idx` (`PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  CONSTRAINT `fk_TRGOVAC_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_JMB_Zaposlenog`)
    REFERENCES `lanac_prodavnica`.`ZAPOSLENI` (`JMB_Zaposlenog`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TRGOVAC_PRODAVNICA1`
    FOREIGN KEY (`PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`PRODAVNICA` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`KUPAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`KUPAC` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`KUPAC` (
  `IdKupca` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `MJESTO_BrojPoste` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`IdKupca`),
  INDEX `fk_KUPAC_MJESTO1_idx` (`MJESTO_BrojPoste` ASC) VISIBLE,
  CONSTRAINT `fk_KUPAC_MJESTO1`
    FOREIGN KEY (`MJESTO_BrojPoste`)
    REFERENCES `lanac_prodavnica`.`MJESTO` (`BrojPoste`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`IZLAZNA_FAKTURA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`IZLAZNA_FAKTURA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`IZLAZNA_FAKTURA` (
  `FAKTURA_IdFakture` INT NOT NULL,
  `KUPAC_IdKupca` INT NULL,
  `PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `TRGOVAC_ZAPOSLENI_JMB_Zaposlenog` VARCHAR(13) NULL,
  PRIMARY KEY (`FAKTURA_IdFakture`),
  INDEX `fk_IZLAZNA_FAKTURA_TRGOVAC1_idx` (`TRGOVAC_ZAPOSLENI_JMB_Zaposlenog` ASC) VISIBLE,
  INDEX `fk_IZLAZNA_FAKTURA_PRODAVNICA1_idx` (`PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  INDEX `fk_IZLAZNA_FAKTURA_KUPAC1_idx` (`KUPAC_IdKupca` ASC) VISIBLE,
  CONSTRAINT `fk_table2_FAKTURA1`
    FOREIGN KEY (`FAKTURA_IdFakture`)
    REFERENCES `lanac_prodavnica`.`FAKTURA` (`IdFakture`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IZLAZNA_FAKTURA_TRGOVAC1`
    FOREIGN KEY (`TRGOVAC_ZAPOSLENI_JMB_Zaposlenog`)
    REFERENCES `lanac_prodavnica`.`TRGOVAC` (`ZAPOSLENI_JMB_Zaposlenog`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IZLAZNA_FAKTURA_PRODAVNICA1`
    FOREIGN KEY (`PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`PRODAVNICA` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IZLAZNA_FAKTURA_KUPAC1`
    FOREIGN KEY (`KUPAC_IdKupca`)
    REFERENCES `lanac_prodavnica`.`KUPAC` (`IdKupca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`INDIVIDUALNI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`INDIVIDUALNI` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`INDIVIDUALNI` (
  `KUPAC_IdKupca` INT NOT NULL,
  `JMB_FizickogLica` VARCHAR(13) NOT NULL,
  `Ime` VARCHAR(20) NOT NULL,
  `Prezime` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`KUPAC_IdKupca`),
  CONSTRAINT `fk_FIZICKO_LICE_KUPAC1`
    FOREIGN KEY (`KUPAC_IdKupca`)
    REFERENCES `lanac_prodavnica`.`KUPAC` (`IdKupca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`PREDUZECE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`PREDUZECE` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`PREDUZECE` (
  `KUPAC_IdKupca` INT NOT NULL,
  `JIB_PravnogLica` VARCHAR(13) NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`KUPAC_IdKupca`),
  CONSTRAINT `fk_table2_KUPAC1`
    FOREIGN KEY (`KUPAC_IdKupca`)
    REFERENCES `lanac_prodavnica`.`KUPAC` (`IdKupca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`MAGACIN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`MAGACIN` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`MAGACIN` (
  `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`),
  INDEX `fk_MAGACIN_ORGANIZACIONA_JEDINICA1_idx` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  UNIQUE INDEX `Naziv_UNIQUE` (`Naziv` ASC) VISIBLE,
  CONSTRAINT `fk_MAGACIN_ORGANIZACIONA_JEDINICA1`
    FOREIGN KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (`Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`MAGACIONER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`MAGACIONER` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`MAGACIONER` (
  `ZAPOSLENI_JMB_Zaposlenog` VARCHAR(13) NOT NULL,
  `MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  PRIMARY KEY (`ZAPOSLENI_JMB_Zaposlenog`),
  INDEX `fk_MAGACIONER_MAGACIN1_idx` (`MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  CONSTRAINT `fk_MAGACIONER_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_JMB_Zaposlenog`)
    REFERENCES `lanac_prodavnica`.`ZAPOSLENI` (`JMB_Zaposlenog`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MAGACIONER_MAGACIN1`
    FOREIGN KEY (`MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`MAGACIN` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`TELEFON_ORGANIZACIONE_JEDINICE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`TELEFON_ORGANIZACIONE_JEDINICE` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`TELEFON_ORGANIZACIONE_JEDINICE` (
  `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `BrojTelefona` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`, `BrojTelefona`),
  INDEX `fk_TELEFON_ORGANIZACIONA_JEDINICA1_idx` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  CONSTRAINT `fk_TELEFON_ORGANIZACIONA_JEDINICA1`
    FOREIGN KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (`Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`PROIZVOD_U_ORGANIZACIONOJ_JEDINICI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`PROIZVOD_U_ORGANIZACIONOJ_JEDINICI` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`PROIZVOD_U_ORGANIZACIONOJ_JEDINICI` (
  `PROIZVOD_Barkod` VARCHAR(12) NOT NULL,
  `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `Kolicina` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`PROIZVOD_Barkod`, `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`),
  INDEX `fk_ORGANIZACIONA_JEDINICA_has_PROIZVOD_ORGANIZACIONA_JEDINI_idx` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  INDEX `fk_PROIZVOD_U_ORGANIZACIONOJ_JEDINICI_PROIZVOD1_idx` (`PROIZVOD_Barkod` ASC) VISIBLE,
  CONSTRAINT `fk_ORGANIZACIONA_JEDINICA_has_PROIZVOD_ORGANIZACIONA_JEDINICA1`
    FOREIGN KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (`Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROIZVOD_U_ORGANIZACIONOJ_JEDINICI_PROIZVOD1`
    FOREIGN KEY (`PROIZVOD_Barkod`)
    REFERENCES `lanac_prodavnica`.`PROIZVOD` (`Barkod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`RACUN_ORGANIZACIONE_JEDINICE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`RACUN_ORGANIZACIONE_JEDINICE` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`RACUN_ORGANIZACIONE_JEDINICE` (
  `BrojRacuna` VARCHAR(13) NOT NULL,
  `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `BANKA_IdBanke` INT NOT NULL,
  PRIMARY KEY (`BrojRacuna`),
  INDEX `fk_RACUN_ORGANIZACIONA_JEDINICA1_idx` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  INDEX `fk_RACUN_BANKA1_idx` (`BANKA_IdBanke` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_ORGANIZACIONA_JEDINICA1`
    FOREIGN KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (`Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RACUN_BANKA1`
    FOREIGN KEY (`BANKA_IdBanke`)
    REFERENCES `lanac_prodavnica`.`BANKA` (`IdBanke`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA_IMA_ADMINISTRATOVNOG_RADNIKA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA_IMA_ADMINISTRATOVNOG_RADNIKA` ;

CREATE TABLE IF NOT EXISTS `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA_IMA_ADMINISTRATOVNOG_RADNIKA` (
  `ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` INT NOT NULL,
  `ADMINISTRATOVNI_RADNIK_ZAPOSLENI_JMB_Zaposlenog` VARCHAR(13) NOT NULL,
  INDEX `fk_ORGANIZACIONA_JEDINICA_has_ADMINISTRATOVNI_RADNIK_ADMINI_idx` (`ADMINISTRATOVNI_RADNIK_ZAPOSLENI_JMB_Zaposlenog` ASC) VISIBLE,
  INDEX `fk_ORGANIZACIONA_JEDINICA_has_ADMINISTRATOVNI_RADNIK_ORGANI_idx` (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice` ASC) VISIBLE,
  PRIMARY KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`, `ADMINISTRATOVNI_RADNIK_ZAPOSLENI_JMB_Zaposlenog`),
  CONSTRAINT `fk_ORGANIZACIONA_JEDINICA_has_ADMINISTRATOVNI_RADNIK_ORGANIZA1`
    FOREIGN KEY (`ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice`)
    REFERENCES `lanac_prodavnica`.`ORGANIZACIONA_JEDINICA` (`Id_OrganizacioneJedinice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ORGANIZACIONA_JEDINICA_has_ADMINISTRATOVNI_RADNIK_ADMINIST1`
    FOREIGN KEY (`ADMINISTRATOVNI_RADNIK_ZAPOSLENI_JMB_Zaposlenog`)
    REFERENCES `lanac_prodavnica`.`ADMINISTRATOVNI_RADNIK` (`ZAPOSLENI_JMB_Zaposlenog`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
