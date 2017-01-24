CREATE TABLE inscricao(
payment VARCHAR(50) NOT NULL,
getToKnowEvent VARCHAR(200) NOT NULL,
descriptiveField VARCHAR(200) NOT NULL,
fk_eventid INT REFERENCES evento (eventid) ON DELETE RESTRICT,
fk_uid INT REFERENCES usuario (uid) ON DELETE RESTRICT,
CONSTRAINT pk_inscricao PRIMARY KEY (fk_eventid, fk_uid)
);