/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Robi
 * Created: 13-mag-2016
 */

create database amazondDB;

create table prodotti (
    cod INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR(128),
    tipo VARCHAR(128),
    pezzi INTEGER,
    prezzo FLOAT
);

create table utenti (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    tipo BOOLEAN,
    username VARCHAR(128),
    password VARCHAR(128),
    nome VARCHAR(128),
    cognome VARCHAR(128)
);

create table prodotti_esami (
    codProdotto INTEGER,
    idUtente INTEGER,
    FOREIGN KEY (codProdotto) REFERENCES prodotti(cod),
    FOREIGN KEY (idUtente) REFERENCES utenti(id),
    PRIMARY KEY (codProdotto,idUtente)
);

insert into prodotti(nome,tipo,pezzi,prezzo)
values ('Nexus4','Smartphone',12,200.0),
('Nexus5','Smartphone',93,250.0),
('Nexus6','Smartphone',134,300.0),
('Nexus5X','Smartphone',350,400.0),
('Nexus6P','Smartphone',450,500.0);

insert into utenti(tipo,username,password,nome,cognome)
values (0,'robertospiga','1234','Roberto','Spiga'),
(0,'nicolabissiri','1234','Nicola','Bissiri'),
(1,'alessiospiga','1234','Alessio','Spiga'),
(1,'paolocorpino','1234','Paolo','Corpino');

insert into prodotti_esami(codProdotto,idUtente)
values (1,1),
(2,1),
(3,2),
(5,2),
(1,3),
(2,3),
(3,4),
(4,4);


