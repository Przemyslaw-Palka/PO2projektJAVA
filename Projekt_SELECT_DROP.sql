SELECT 'DROP TABLE ' || TABLE_NAME || ' CASCADE CONSTRAINTS;'
FROM USER_TABLES; 
DROP TABLE GRACZ CASCADE CONSTRAINTS;
DROP TABLE MAPA CASCADE CONSTRAINTS;
DROP TABLE MECZ CASCADE CONSTRAINTS;
DROP TABLE POSTACIE CASCADE CONSTRAINTS;
DROP TABLE SKLAD CASCADE CONSTRAINTS;
DROP TABLE SKLAD_POSTACIE CASCADE CONSTRAINTS;
DROP TABLE TURA CASCADE CONSTRAINTS;
DROP TABLE ATAKI CASCADE CONSTRAINTS;
DROP TABLE RUCHY CASCADE CONSTRAINTS;
--Pobranie informacji o wszystkich postaciach:

SELECT * FROM Postacie;

--Pobranie informacji o składach wraz z postaciami w tych składach:

SELECT
    id_postac,
    nazwa_postaci,
    DMG,
    CASE
        WHEN DMG < 11 THEN 'Słaby'
        WHEN DMG >= 11 AND DMG <= 29 THEN 'Przeciętny'
        WHEN DMG > 29 THEN 'Mocny'
        ELSE 'Nieznany'
    END AS ocena_obrazen
FROM Postacie;
--Pobranie informacji o meczach wraz z informacjami o wygranym i przegranym:

SELECT Mecz.id_mecz, Gracz1.imie AS gracz_1, Gracz2.imie AS gracz_2, Wygrany.imie AS wygrany, Przegrany.imie AS przegrany
FROM Mecz
JOIN Gracz Gracz1 ON Mecz.id_gracz_1 = Gracz1.id_gracz
JOIN Gracz Gracz2 ON Mecz.id_gracz_2 = Gracz2.id_gracz
JOIN Gracz Wygrany ON Mecz.wygrany = Wygrany.id_gracz
JOIN Gracz Przegrany ON Mecz.przegrany = Przegrany.id_gracz;

--Pobranie informacji o graczu posiadającym daną postać (na przykład postać o id_postac=1):

SELECT Gracz.*
FROM Gracz
JOIN Sklad ON Gracz.id_gracz = Sklad.id_gracz
JOIN Sklad_Postacie ON Sklad.id_skladu = Sklad_Postacie.id_skladu
WHERE Sklad_Postacie.id_postac = 1;


--Pobranie informacji o atakach w meczach:

SELECT Ataki.id_ataku, Mecz.id_mecz, Gracz1.imie AS atakujacy, Gracz2.imie AS atakowany, Postacie1.nazwa_postaci AS postac_atakujaca, Postacie2.nazwa_postaci AS postac_atakowana
FROM Ataki
JOIN Mecz ON Ataki.id_mecz = Mecz.id_mecz
JOIN Gracz Gracz1 ON Ataki.id_atakujacego = Gracz1.id_gracz
JOIN Gracz Gracz2 ON Ataki.id_atakowanego = Gracz2.id_gracz
JOIN Postacie Postacie1 ON Ataki.id_postaci_atakujacej = Postacie1.id_postac
JOIN Postacie Postacie2 ON Ataki.id_postaci_atakowanej = Postacie2.id_postac
ORDER BY Ataki.id_ataku;

--Pobranie informacji o turach w meczach:

SELECT Tura.id_tury, Gracz.imie AS gracz, Mecz.id_mecz, Tura.akcja
FROM Tura
JOIN Gracz ON Tura.kto_sie_rusza = Gracz.id_gracz
JOIN Mecz ON Tura.id_mecz = Mecz.id_mecz
ORDER BY Tura.id_tury;