create database Larbc;
use Larbc;
drop table Imoveis;
create table Imoveis(
					idCasos int,
					estado char(2),
					cidade varchar(50),
					bairro varchar(50),
					rua varchar(60),
					numero int,
					nome varchar(40),
					areaConstruida float,
					areaTotal float,
					vagasGaragem int,
					quartos int,
					suite int,
					banheiros int,
					tipo varchar(45),
					preco float,
					tipoNegocio int,
					inseridoPor int,
			primary key (idCasos)
);