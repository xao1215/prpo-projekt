INSERT INTO uporabnik (ime, priimek, username, email, password) VALUES ('Petra', 'Kos', 'petrakos', 'petra.kos@hotmail.com', 'koskoskos99');
INSERT INTO uporabnik (ime, priimek, username, email, password) VALUES ('Miha', 'Novak', 'mihanovak', 'miha.novak@gmail.com', 'mn12345');
INSERT INTO uporabnik (ime, priimek, username, email, password) VALUES ('Janez', 'Skrapap', 'bumpao', 'frrrr@gmail.com', 'js123');
INSERT INTO postaja (uporabnik_id, ime, specifikacije, lokacija, cena_polnjenja, obratovalni_cas) VALUES (1, 'TeslaMaribor', 'null', 'Maribor', 0.25, 'med tednom');
INSERT INTO postaja (uporabnik_id, ime, specifikacije, lokacija, cena_polnjenja, obratovalni_cas) VALUES (3, 'TeslaLjubljana', 'null', 'Ljubljana', 0.15, 'cel teden');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (2, 1, '2022-01-22', '11:10:00', '12:10:00');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (null, 2, '2022-01-22', '14:10:00', '14:30:00');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (3, 1, '2022-01-22', '17:25:00', '17:45:00');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (null, 2, '2022-01-23', '14:20:00', '15:30:00');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (null, 1, '2022-01-22', '21:50:00', '22:10:00');
INSERT INTO termin (uporabnik_id, postaja_id, dan, od_ura, do_ura) VALUES (3, 1, '2022-01-21', '22:15:00', '23:15:00');