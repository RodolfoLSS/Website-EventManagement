CREATE TABLE evento(
eventid SERIAL NOT NULL,
eventName VARCHAR(50) NOT NULL,
description VARCHAR(200) NOT NULL,
impInformation VARCHAR(200) NOT NULL,
price INT NOT NULL,
beginDate VARCHAR(20) NOT NULL,
endDate VARCHAR(20) NOT NULL,
beginTime VARCHAR(8) NOT NULL,
endTime VARCHAR(8) NOT NULL,
weekdays VARCHAR(100),
eventType INT NOT NULL,
CONSTRAINT pk_event PRIMARY KEY (eventid,beginDate,endDate),
CONSTRAINT u_event UNIQUE (eventid),
fk_usuario_evento INT REFERENCES usuario (uid) ON DELETE CASCADE,
fk_local_evento INT REFERENCES local (lid) ON DELETE RESTRICT,
fk_entPromotora_evento INT REFERENCES entPromotora (eid) ON DELETE RESTRICT
);
