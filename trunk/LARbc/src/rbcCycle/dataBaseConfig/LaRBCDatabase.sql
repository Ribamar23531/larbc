CREATE SCHEMA IF NOT EXISTS `larbc` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `larbc`;

-- -----------------------------------------------------
-- Table `larbc`.`Administradores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `larbc`.`Administradores` (
  `idAdministradores` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(20) NOT NULL ,
  `password` VARCHAR(20) NOT NULL ,
  `nome` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`idAdministradores`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `larbc`.`Casos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `larbc`.`Casos` (
  `idCasos` INT NOT NULL AUTO_INCREMENT ,
  `estado` CHAR(2) NOT NULL ,
  `cidade` VARCHAR(50) NOT NULL ,
  `bairro` VARCHAR(50) NOT NULL ,
  `rua` VARCHAR(60) NOT NULL ,
  `numero` INT NOT NULL DEFAULT 0 ,
  `nome` VARCHAR(40) NULL ,
  `areaConstruida` FLOAT NOT NULL DEFAULT 0 ,
  `areaTotal` FLOAT NOT NULL ,
  `vagasGaragem` INT NOT NULL DEFAULT 0 ,
  `quartos` INT NOT NULL DEFAULT 0 ,
  `suite` INT NOT NULL DEFAULT 0 ,
  `banheiros` INT NOT NULL ,
  `tipo` VARCHAR(45) NOT NULL ,
  `preco` FLOAT NOT NULL ,
  `tipoNegocio` BIT NOT NULL ,
  `inseridoPor` INT NOT NULL ,
  PRIMARY KEY (`idCasos`) ,
  INDEX `inseridoPor` (`inseridoPor` ASC) ,
  CONSTRAINT `inseridoPor`
    FOREIGN KEY (`inseridoPor` )
    REFERENCES `larbc`.`Administradores` (`idAdministradores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `larbc`.`Demandas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `larbc`.`Demandas` (
  `idDemanda` INT NOT NULL AUTO_INCREMENT ,
  `estado` CHAR(2) NULL ,
  `cidade` VARCHAR(50) NULL ,
  `bairro` VARCHAR(50) NULL ,
  `rua` VARCHAR(60) NULL ,
  `numero` INT NULL ,
  `nome` VARCHAR(40) NULL ,
  `areaConstruida` FLOAT NULL DEFAULT 0 ,
  `areaTotal` FLOAT NULL ,
  `vagasGaragem` INT NULL DEFAULT 0 ,
  `quartos` INT NULL DEFAULT 0 ,
  `suite` INT NULL DEFAULT 0 ,
  `banheiros` INT NULL ,
  `tipo` VARCHAR(45) NULL ,
  `preco` FLOAT NULL ,
  `nomeCliente` VARCHAR(45) NULL ,
  `emailCliente` VARCHAR(45) NULL ,
  `telefone` CHAR(10) NULL ,
  `jaModerado` BOOLEAN NOT NULL DEFAULT FALSE ,
  `tipoNegocio` INT NOT NULL ,
  PRIMARY KEY (`idDemanda`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `larbc`.`Fotos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `larbc`.`Fotos` (
  `idFotos` INT NOT NULL ,
  `caminhoArquivo` VARCHAR(80) NOT NULL ,
  PRIMARY KEY (`idFotos`, `caminhoArquivo`) ,
  INDEX `doCaso` (`idFotos` ASC) ,
  CONSTRAINT `doCaso`
    FOREIGN KEY (`idFotos` )
    REFERENCES `larbc`.`Casos` (`idCasos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;