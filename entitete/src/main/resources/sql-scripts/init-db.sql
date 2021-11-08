INSERT INTO uporabnik (ime, priimek, username, email, password) VALUES ('Petra', 'Kos', 'petrakos', 'petra.kos@hotmail.com', 'koskoskos99');
INSERT INTO uporabnik (ime, priimek, username, email, password) VALUES ('Miha', 'Novak', 'mihanovak', 'miha.novak@gmail.com', 'mn12345');
INSERT INTO uporabnik (ime, priimek, username, email, password) VALUES ('Janez', 'Skrapap', 'bumpao', 'frrrr@gmail.com', 'js123');
INSERT INTO postaja (uporabnik_id, ime, specifikacije, lokacija, cena_polnjenja, obratovalni_cas) VALUES (1, 'TeslaVegas', 'null', 'Las Vegas', 5, 'med tednom');
INSERT INTO postaja (uporabnik_id, ime, specifikacije, lokacija, cena_polnjenja, obratovalni_cas) VALUES (3, 'ljelektrika', 'null', 'Ljubljana', 1000, 'med tednom');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (2, 1, '1000-01-01', '00:10:01', '00:11:01');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (null, 2, '1000-01-01', '00:00:01', '00:01:01');
