INSERT INTO uporabnik (ime, priimek, username, email) VALUES ('Petra', 'Kos', 'petrakos', 'petra.kos@hotmail.com');
INSERT INTO uporabnik (ime, priimek, username, email) VALUES ('Miha', 'Novak', 'mihanovak', 'miha.novak@gmail.com');
INSERT INTO uporabnik (ime, priimek, username, email) VALUES ('Janez', 'Skrapap', 'bumpao', 'frrrr@gmail.com');
INSERT INTO termin (id_uporabnik, prosto, dan, ura_od, ura_do) VALUES (2, false, "1000-01-01", "00:10:01", "00:11:01");
INSERT INTO termin (id_uporabnik, prosto, dan, ura_od, ura_do) VALUES (null, true, "1000-01-01", "00:00:01", "00:01:01");
INSERT INTO postaja (id_lastnik, id_termin, ime, specifikacije, lokacija, cena_polnjenja, obratovalni_cas) VALUES (1, null, "TeslaVegas", null, "Las Vegas", 5, "med tednom");
INSERT INTO postaja (id_lastnik, id_termin, ime, specifikacije, lokacija, cena_polnjenja, obratovalni_cas) VALUES (3, null, "ljelektrika", null, "Ljubljana", 1000, "med tednom");
