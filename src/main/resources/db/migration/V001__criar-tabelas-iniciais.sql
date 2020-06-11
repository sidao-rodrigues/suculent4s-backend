-- MySQL Script generated by MySQL Workbench
-- ter 09 jun 2020 22:05:46
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema suculentas
-- -----------------------------------------------------
-- DROP SCHEMA IF EXISTS `suculentas` ;

-- -----------------------------------------------------
-- Schema suculentas
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `suculentas` ;
-- USE `suculentas` ;

-- -----------------------------------------------------
-- Table `suculentas`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `cpf_ou_cnpj` VARCHAR(20) NOT NULL,
  `tipo_pessoa` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpfOuCnpj_UNIQUE` (`cpf_ou_cnpj` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`tipo_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_usuario` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `tipo_usuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estado` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `estado` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Cidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cidade` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `cidade` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `id_estado` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `id_estado`),
  INDEX `fk_Cidade_Estado1_idx` (`id_estado` ASC) VISIBLE,
  CONSTRAINT `fk_Cidade_Estado1`
    FOREIGN KEY (`id_estado`)
    REFERENCES `estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `endereco` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `endereco` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(160) NOT NULL,
  `numero` VARCHAR(15) NOT NULL,
  `complemento` VARCHAR(100) NULL,
  `bairro` VARCHAR(60) NOT NULL,
  `cep` VARCHAR(10) NOT NULL,
  `latitude` BIGINT(20) NULL,
  `longitude` BIGINT(20) NULL,
  `id_cidade` BIGINT(20) NOT NULL,
  `id_usuario` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `id_cidade`, `id_usuario`),
  INDEX `fk_Endereco_Cidade1_idx` (`id_cidade` ASC) VISIBLE,
  INDEX `fk_Endereco_Usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Endereco_Cidade1`
    FOREIGN KEY (`id_cidade`)
    REFERENCES `cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Endereco_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pedido` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `pedido` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data_pedido` DATETIME NOT NULL,
  `total` DECIMAL(20,2) NOT NULL,
  `id_endereco` BIGINT(20) NOT NULL,
  `id_usuario` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `id_usuario`, `id_endereco`),
  INDEX `fk_Pedido_Endereco1_idx` (`id_endereco` ASC) VISIBLE,
  INDEX `fk_Pedido_Usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Endereco1`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoria` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `categoria` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Especie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `especie` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `especie` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `produto` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `produto` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `preco` DECIMAL(20,2) NOT NULL,
  `id_categoria` BIGINT(20) NOT NULL,
  `id_especie` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `id_especie`, `id_categoria`),
  INDEX `fk_Produto_Categoria_produto1_idx` (`id_categoria` ASC) VISIBLE,
  INDEX `fk_Produto_Especie1_idx` (`id_especie` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Categoria_produto1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Especie1`
    FOREIGN KEY (`id_especie`)
    REFERENCES `especie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`Item_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item_pedido` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `item_pedido` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `desconto` DECIMAL(20,2) NOT NULL,
  `quantidade` INT NOT NULL,
  `id_pedido` BIGINT(20) NOT NULL,
  `id_produto` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `id_produto`, `id_pedido`),
  INDEX `fk_Item_pedido_Pedido1_idx` (`id_pedido` ASC) VISIBLE,
  INDEX `fk_Item_pedido_Produto1_idx` (`id_produto` ASC) VISIBLE,
  CONSTRAINT `fk_Item_pedido_Pedido1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_pedido_Produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suculentas`.`acesso_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acesso_usuario` ; --`suculentas`.

CREATE TABLE IF NOT EXISTS `acesso_usuario` (
  `id_usuario` BIGINT(20) NOT NULL,
  `id_acesso_usuario` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_acesso_usuario`),
  INDEX `fk_Usuario_has_acesso_usuario_acesso_usuario1_idx` (`id_acesso_usuario` ASC) VISIBLE,
  INDEX `fk_Usuario_has_acesso_usuario_Usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_acesso_usuario_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_acesso_usuario_acesso_usuario1`
    FOREIGN KEY (`id_acesso_usuario`)
    REFERENCES `tipo_usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;