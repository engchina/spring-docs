CREATE DATABASE IF NOT EXISTS chapter15;

USE chapter15;

CREATE TABLE IF NOT EXISTS chapter15.people (
    name        VARCHAR(100),
    title       VARCHAR(10),
    description VARCHAR(100),
    PRIMARY KEY (name)
);

DELETE FROM chapter15.people;

INSERT INTO chapter15.people VALUES ('Gru', 'Felonius', 'Where are the minions?');
INSERT INTO chapter15.people VALUES ('Nefario', 'Dr.', 'Why ... why are you so old?');
INSERT INTO chapter15.people VALUES ('Agnes', '', 'Your unicorn is so fluffy!');
INSERT INTO chapter15.people VALUES ('Edith', '', "Don't touch anything!");
INSERT INTO chapter15.people VALUES ('Vector', '', 'Committing crimes with both direction and magnitude!');
INSERT INTO chapter15.people VALUES ('Dave', 'Minion', 'Ngaaahaaa! Patalaki patalaku Big Boss!!');

COMMIT;
