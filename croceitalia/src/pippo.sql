
CREATE TABLE MEZZO (
                id_mezzo INT AUTO_INCREMENT NOT NULL,
                targa VARCHAR(20) NOT NULL,
                tipo_veicolo VARCHAR(40) NOT NULL,
                num_posti INT,
                descrizione VARCHAR(200) NOT NULL,
                QF DECIMAL(2) DEFAULT 0 NOT NULL,
                costo_km DECIMAL(2) NOT NULL,
                franchigia_km DECIMAL(2) NOT NULL,
                distretto VARCHAR(40),
                PRIMARY KEY (id_mezzo)
);


CREATE TABLE CLIENTE (
                id_cliente INT AUTO_INCREMENT NOT NULL,
                ragione_sociale VARCHAR(40) NOT NULL,
                P_IVA VARCHAR(20) NOT NULL,
                via VARCHAR(40) NOT NULL,
                comune VARCHAR(40) NOT NULL,
                cap VARCHAR(5) NOT NULL,
                provincia VARCHAR(40) NOT NULL,
                CF VARCHAR(16),
                QF DECIMAL(2) DEFAULT 0 NOT NULL,
                PRIMARY KEY (id_cliente)
);


CREATE TABLE DOCUMENTO_TESTATA (
                id_documento_testata INT AUTO_INCREMENT NOT NULL,
                fk_id_cliente INT NOT NULL,
                num_documento INT NOT NULL,
                anno_documento VARCHAR(20) NOT NULL,
                mese_documento VARCHAR(20) NOT NULL,
                data_documento DATE NOT NULL,
                imponibile DECIMAL(7,2) DEFAULT 0 NOT NULL,
                IVA DECIMAL(7,2) DEFAULT 0 NOT NULL,
                esente_IVA CHAR DEFAULT 'S' NOT NULL,
                esente_bollo CHAR DEFAULT 'S' NOT NULL,
                importo_esente DECIMAL(7,2) DEFAULT 0 NOT NULL,
                totale DECIMAL(7,2) DEFAULT 0 NOT NULL,
                PRIMARY KEY (id_documento_testata)
);


CREATE TABLE DOCUMENTO_RIGHE (
                id_documento_righe INT AUTO_INCREMENT NOT NULL,
                fk_id_documento_testata INT NOT NULL,
                num_sedute INT DEFAULT 0 NOT NULL,
                mese VARCHAR(20) DEFAULT 0 NOT NULL,
                km_totali INT DEFAULT 0 NOT NULL,
                km_percorso INT DEFAULT 0 NOT NULL,
                nome_trasporto VARCHAR(40) DEFAULT 0 NOT NULL,
                percorso VARCHAR(40) DEFAULT 0 NOT NULL,
                p_partenza VARCHAR(40) DEFAULT 0 NOT NULL,
                p_arrivo VARCHAR(40) DEFAULT 0 NOT NULL,
                ora_sosta INT DEFAULT 0 NOT NULL,
                QF DECIMAL(2) DEFAULT 0 NOT NULL,
                diritto_uscita VARCHAR(40) DEFAULT 0 NOT NULL,
                importo_doc DECIMAL(7,2) DEFAULT 0 NOT NULL,
                PRIMARY KEY (id_documento_righe)
);


CREATE TABLE BANCA_APPOGGIO (
                id_banca INT AUTO_INCREMENT NOT NULL,
                nome VARCHAR(40) NOT NULL,
                via VARCHAR(40),
                comune VARCHAR(40),
                cap VARCHAR(5),
                provincia VARCHAR(40),
                BIC_SWIFT VARCHAR(20),
                IBAN VARCHAR(27) NOT NULL,
                PRIMARY KEY (id_banca)
);


CREATE TABLE TIPOLOGIA_STRUTTURA (
                id_tipologia_struttura INT NOT NULL,
                descrizione VARCHAR(200) NOT NULL,
                PRIMARY KEY (id_tipologia_struttura)
);


CREATE TABLE STRUTTURA (
                id_struttura INT AUTO_INCREMENT NOT NULL,
                fk_id_tipologia_struttura INT NOT NULL,
                nome VARCHAR(40) NOT NULL,
                via VARCHAR(40),
                comune VARCHAR(40),
                cap VARCHAR(5),
                provincia VARCHAR(40),
                descrizione_breve VARCHAR(50) NOT NULL,
                cod_regione CHAR(3),
                cod_asl CHAR(3),
                cod_struttura CHAR(6),
                telefono VARCHAR(20),
                email VARCHAR(40),
                tipologia_struttura VARCHAR(40),
                PRIMARY KEY (id_struttura)
);


CREATE TABLE PATOLOGIA (
                id_patologia INT AUTO_INCREMENT NOT NULL,
                descrizione VARCHAR(200) NOT NULL,
                PRIMARY KEY (id_patologia)
);


CREATE TABLE PAZIENTE (
                id_paziente INT AUTO_INCREMENT NOT NULL,
                fk_id_patologia INT NOT NULL,
                nome VARCHAR(40) NOT NULL,
                cognome VARCHAR(40) NOT NULL,
                telefono1 VARCHAR(20),
                telefono2 VARCHAR(20),
                sesso CHAR(1) NOT NULL,
                data_nascita DATE NOT NULL,
                via VARCHAR(40),
                comune VARCHAR(40) NOT NULL,
                cap VARCHAR(5),
                provincia VARCHAR(40),
                PRIMARY KEY (id_paziente)
);


ALTER TABLE DOCUMENTO_TESTATA ADD CONSTRAINT cliente_documento_testata_fk
FOREIGN KEY (fk_id_cliente)
REFERENCES CLIENTE (id_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE DOCUMENTO_RIGHE ADD CONSTRAINT documento_testata_documento_righe_fk
FOREIGN KEY (fk_id_documento_testata)
REFERENCES DOCUMENTO_TESTATA (id_documento_testata)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE STRUTTURA ADD CONSTRAINT tipologia_struttura_struttura_fk
FOREIGN KEY (fk_id_tipologia_struttura)
REFERENCES TIPOLOGIA_STRUTTURA (id_tipologia_struttura)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE PAZIENTE ADD CONSTRAINT patologia_paziente_fk
FOREIGN KEY (fk_id_patologia)
REFERENCES PATOLOGIA (id_patologia)
ON DELETE NO ACTION
ON UPDATE NO ACTION;