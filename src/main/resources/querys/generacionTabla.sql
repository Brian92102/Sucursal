CREATE TABLE SUCURSAL ( ID_SUCURSAL NUMBER NOT NULL,
DIRECCION VARCHAR2(50),
LATITUD NUMBER NOT NULL,
LONGITUD NUMBER NOT NULL)

---PK
ALTER TABLE SUCURSAL ADD PRIMARY KEY ( ID_SUCURSAL ) 
