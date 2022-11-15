CREATE SEQUENCE id_seq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE alcohol_id_seq
	INCREMENT BY 1
	START WITH 10001;

CREATE SEQUENCE preference_id_seq
	INCREMENT BY 1
	START WITH 20001;

CREATE SEQUENCE diary_id_seq
	INCREMENT BY 1
	START WITH 30001;

CREATE SEQUENCE drink_id_seq
	INCREMENT BY 1
	START WITH 40001;

CREATE SEQUENCE review_id_seq
	INCREMENT BY 1
	START WITH 50001;

CREATE TABLE Alcohol
(
	alcohol_id           NUMBER  NOT NULL ,
	name                 VARCHAR2(40)  NOT NULL ,
	type                 VARCHAR2(20)  NOT NULL ,
	info                 VARCHAR2(1000)  NOT NULL ,
	rate                 DECIMAL(2,1)  DEFAULT 0  NOT NULL ,
	alcohol_level        DECIMAL(3,1)  NOT NULL ,
	image                VARCHAR2(255)  NOT NULL ,
	taste                INTEGER  NULL ,
	flavor               INTEGER  NULL ,
	corps                INTEGER  NULL 
);

CREATE UNIQUE INDEX XPKAlcohol ON Alcohol
(alcohol_id   ASC);

ALTER TABLE Alcohol
	ADD CONSTRAINT  XPKAlcohol PRIMARY KEY (alcohol_id);

CREATE TABLE Member
(
	id                   NUMBER  NOT NULL ,
	user_id              VARCHAR2(20)  NOT NULL ,
	password             VARCHAR2(20)  NOT NULL ,
	nickname             VARCHAR2(60)  NOT NULL ,
	email                VARCHAR2(50)  NULL ,
	birth                DATE  NULL ,
	gender               CHAR(1)  NULL ,
	test_type            CHAR(4)  NULL ,
	drinking_capacity    FLOAT  NULL 
);

CREATE UNIQUE INDEX XPKMember ON Member
(id   ASC);

ALTER TABLE Member
	ADD CONSTRAINT  XPKMember PRIMARY KEY (id);

CREATE TABLE Diary
(
	diary_id             NUMBER  NOT NULL ,
	member_id            NUMBER  NOT NULL ,
	drinking_date        TIMESTAMP  NOT NULL ,
	condition            INTEGER  NOT NULL ,
	content              VARCHAR2(4000)  NOT NULL
);

CREATE UNIQUE INDEX XPKDiary ON Diary
(diary_id   ASC);

ALTER TABLE Diary
	ADD CONSTRAINT  XPKDiary PRIMARY KEY (diary_id);

CREATE TABLE Drink
(
	drink_id             NUMBER  NOT NULL ,
	diary_id             NUMBER  NOT NULL ,
	alcohol_id           NUMBER  NOT NULL ,
	amount               INTEGER  DEFAULT 0  NULL
);

CREATE UNIQUE INDEX XPKDrink ON Drink
(drink_id   ASC);

ALTER TABLE Drink
	ADD CONSTRAINT  XPKDrink PRIMARY KEY (drink_id);

CREATE TABLE Preference
(
	preference_id        NUMBER  NOT NULL ,
	member_id            NUMBER  NOT NULL ,
	alcohol_id           NUMBER  NOT NULL ,
	rate                 DECIMAL(2,1)  NULL  CONSTRAINT  check1 CHECK (rate IN (0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5)),
	totalAmount          INTEGER  DEFAULT 0  NULL 
);

CREATE UNIQUE INDEX XPKPreference ON Preference
(preference_id   ASC);

ALTER TABLE Preference
	ADD CONSTRAINT  XPKPreference PRIMARY KEY (preference_id);

CREATE TABLE Review
(
	review_id            NUMBER  NOT NULL ,
	preference_id        NUMBER  NOT NULL ,
	reg_date             DATE  NOT NULL ,
	taste                INTEGER  NOT NULL ,
	flavor               INTEGER  NOT NULL ,
	corps                INTEGER  NOT NULL ,
	content              VARCHAR2(4000)  NOT NULL
);

CREATE UNIQUE INDEX XPKReview ON Review
(review_id   ASC);

ALTER TABLE Review
	ADD CONSTRAINT  XPKReview PRIMARY KEY (review_id);

ALTER TABLE Diary
	ADD (
CONSTRAINT 소유_4 FOREIGN KEY (member_id) REFERENCES Member (id));

ALTER TABLE Drink
	ADD (
CONSTRAINT 소유_5 FOREIGN KEY (alcohol_id) REFERENCES Alcohol (alcohol_id));

ALTER TABLE Drink
	ADD (
CONSTRAINT 소유_1 FOREIGN KEY (diary_id) REFERENCES Diary (diary_id));

ALTER TABLE Preference
	ADD (
CONSTRAINT 소유_2 FOREIGN KEY (member_id) REFERENCES Member (id));

ALTER TABLE Preference
	ADD (
CONSTRAINT 소유_3 FOREIGN KEY (alcohol_id) REFERENCES Alcohol (alcohol_id));

ALTER TABLE Review
	ADD (
CONSTRAINT 확장 FOREIGN KEY (preference_id) REFERENCES Preference (preference_id) ON DELETE SET NULL);