CREATE TABLE Gracz (
    id_gracz INT PRIMARY KEY,
    imie VARCHAR(50) NOT NULL,
    zwyciestwa INT,
    przegrane INT
);  


CREATE TABLE Postacie ( 
    id_postac INT NOT NULL PRIMARY KEY,
    nazwa_postaci VARCHAR(50) NOT NULL,
    DMG INT NOT NULL,
    HP INT,
    zasieg_ruchu INT
);

CREATE TABLE Sklad ( 
    id_skladu INT NOT NULL PRIMARY KEY,
    id_gracz REFERENCES Gracz(id_gracz),
    id_postaci_1 REFERENCES Postacie(id_postac),
    id_postaci_2 REFERENCES Postacie(id_postac),
    id_postaci_3 REFERENCES Postacie(id_postac),
    id_postaci_4 REFERENCES Postacie(id_postac),
    CONSTRAINT unique_postacie_gracz UNIQUE (id_gracz, id_postaci_1, id_postaci_2, id_postaci_3, id_postaci_4)
);

CREATE TABLE Sklad_Postacie (
    id_skladu INT REFERENCES Sklad(id_skaldu),
    id_postac INT REFERENCES Postacie(id_postac),
    PRIMARY KEY (id_skladu, id_postac)
);

CREATE TABLE Mapa(
    id_mapa INT NOT NULL PRIMARY KEY,
    wspolrzedne_mapy_x INT,
    wspolrzedne_mapy_y INT 
);

CREATE TABLE Mecz(
    id_mecz INT NOT NULL PRIMARY KEY,
    id_gracz_1 INT REFERENCES Gracz(id_gracz),
    id_gracz_2 INT REFERENCES Gracz(id_gracz),
    wygrany INT REFERENCES Gracz(id_gracz),
    przegrany INT REFERENCES Gracz(id_gracz)
);

CREATE TABLE Tura(
    id_tury INT NOT NULL PRIMARY KEY,
    kto_sie_rusza INT REFERENCES Gracz(id_gracz), 
    id_mecz INT REFERENCES Mecz(id_mecz),
    akcja VARCHAR(50) CHECK (akcja IN ('RUCH', 'ATAK'))
);

CREATE TABLE Ruchy (
    id_ruchu INT NOT NULL PRIMARY KEY, 
    id_tury INT REFERENCES Tura(id_tury), 
    id_mapa INT REFERENCES Mapa(id_mapa),
    id_gracza_ruszajcego INT REFERENCES Gracz(id_gracz), 
    id_postaci_ruszajacej INT REFERENCES Postacie(id_postac), 
    poczatkowy_x INT, 
    poczatkowy_y INT, 
    koncowy_x INT, 
    koncowy_y INT
);

CREATE TABLE Ataki (
    id_ataku INT PRIMARY KEY,
    id_mecz INT REFERENCES Mecz(id_mecz),
    id_atakujacego INT REFERENCES Gracz(id_gracz),
    id_atakowanego INT REFERENCES Gracz(id_gracz),
    id_postaci_atakujacej INT REFERENCES Postacie(id_postac),
    id_postaci_atakowanej INT REFERENCES Postacie(id_postac)

);
CREATE TABLE Gracz_Atak (
    id_gracz INT REFERENCES Gracz(id_gracz),
    id_atak INT REFERENCES Ataki(id_ataku),
    PRIMARY KEY (id_gracz, id_atak)
);




