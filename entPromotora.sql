﻿CREATE TABLE entPromotora(
eid SERIAL NOT NULL,
entName VARCHAR(50) NOT NULL,
description VARCHAR(200) NOT NULL,
CONSTRAINT pk_entProm PRIMARY KEY(eid),
CONSTRAINT u_entName UNIQUE(entName)
);