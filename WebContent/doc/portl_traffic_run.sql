CREATE TABLE portl_traffic_run
(
	runnId              VARCHAR2(200) NOT NULL,
	runnTitle           VARCHAR2(200) NOT NULL,
	runnContent         clob NOT NULL,
	runnDel             NUMBER(8,2) DEFAULT 0 NOT NULL,
	runnTop             DATE,
	runnCreateDate      DATE NOT NULL,
	runnEditDate        DATE,
	runnCreateUserName  VARCHAR2(50)
)
;


ALTER TABLE portl_traffic_run ADD CONSTRAINT PK_portl_traffic_run 
	PRIMARY KEY (runnId)
;

