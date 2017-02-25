SPOOL CesarLopezOutputSPOOL.txt

DROP TABLE MovieCastMember CASCADE CONSTRAINTS;
DROP TABLE Actor CASCADE CONSTRAINTS;
DROP TABLE Award CASCADE CONSTRAINTS;
DROP TABLE Director CASCADE CONSTRAINTS;
DROP TABLE Discount CASCADE CONSTRAINTS;
DROP TABLE Distributor_Invoice CASCADE CONSTRAINTS;
DROP TABLE Distributor CASCADE CONSTRAINTS;
DROP TABLE Catalog_T CASCADE CONSTRAINTS;
DROP TABLE Movie CASCADE CONSTRAINTS; 
DROP TABLE Rental_Invoice CASCADE CONSTRAINTS;
DROP TABLE MovieFormat CASCADE CONSTRAINTS;
DROP TABLE Customer CASCADE CONSTRAINTS;

CREATE TABLE Customer
		(CustomerID				NUMBER(11)	NOT NULL,
		C_Name					VARCHAR(25) NOT NULL,
		Address					VARCHAR(30) NOT NULL,
		City					VARCHAR(20) NOT NULL,
		C_State					CHAR(2)     NOT NULL,
		ZipCode					VARCHAR(9)  NOT NULL,
		Telephone_Number		VARCHAR(13) NOT NULL,
		YearlyRental			NUMBER(4,0) NOT NULL,
		MonthlyRental			NUMBER(3,0) NOT NULL,
		TotalRental				NUMBER(5,0) NOT NULL,
CONSTRAINT PK_Customer PRIMARY KEY (CustomerID));


CREATE TABLE Director
		(DirectorID				NUMBER(11)	NOT NULL,
		DirectorName			VARCHAR(25) NOT NULL,
		DirectorGender			VARCHAR(6),
CONSTRAINT Director_PK PRIMARY KEY (DirectorID));


CREATE TABLE Actor
		(ActorID				NUMBER(11)	NOT NULL,
		ActorName				VARCHAR(30) NOT NULL,
		ActorGender				VARCHAR(6),
CONSTRAINT Actor_PK PRIMARY 	KEY (ActorID));


CREATE TABLE Award 
		(AwardID				NUMBER(11)  NOT NULL,
		AwardCatagory			VARCHAR(40) NOT NULL,
CONSTRAINT Award_PK PRIMARY KEY (AwardID));


CREATE TABLE Movie
		(StoreMovie_ID			NUMBER(11) NOT NULL,
		ActorID					NUMBER(11) NOT NULL,
		DirectorID				NUMBER(11) NOT NULL,
		AwardID					NUMBER(11),
		CatalogID 				NUMBER(11) NOT NULL,
		MovieTitle				VARCHAR(50),
		Genre					VARCHAR(10) NOT NULL,
		YearReleased			DATE NOT NULL,
		MovieLength				VARCHAR(12),
		Rating					NUMBER(2,0),
CONSTRAINT Movie_PK  PRIMARY KEY (StoreMovie_ID),
CONSTRAINT Movie_FK1 FOREIGN KEY (ActorID) REFERENCES Actor (ActorID),
CONSTRAINT Movie_FK2 FOREIGN KEY (DirectorID) REFERENCES Director (DirectorID),
CONSTRAINT Movie_FK3 FOREIGN KEY (AwardID) REFERENCES Award (AwardID));


CREATE TABLE MovieCastMember 
		(StoreMovie_ID			NUMBER(11) NOT NULL,
		ActorID					NUMBER(11) NOT NULL,
CONSTRAINT MovieCastMember_FK1  FOREIGN KEY (StoreMovie_ID) REFERENCES Movie (StoreMovie_ID),
CONSTRAINT MovieCastMember_FK2  FOREIGN KEY (ActorID) REFERENCES Actor (ActorID));


CREATE TABLE MovieFormat
		(Store_SerialNumber			NUMBER(11) NOT NULL,
		StoreMovie_ID				NUMBER(11) NOT NULL,
		Distributor_SerialNumeber	NUMBER(11) NOT NULL,
		DVDorVHS					VARCHAR(4),
CONSTRAINT MovieFormat_PK PRIMARY KEY (Store_SerialNumber),
CONSTRAINT MovieFormat_FK FOREIGN KEY (StoreMovie_ID) REFERENCES Movie (StoreMovie_ID));


CREATE TABLE Rental_Invoice
		(RentalID				NUMBER(11) NOT NULL,
		CustomerID				NUMBER(11)	NOT NULL,
		Store_SerialNumber		NUMBER(11) NOT NULL,
		RentalDate				DATE,
		RentalReturn			DATE,
		LateFees				NUMBER(5,2),
		DamageFees				NUMBER(5,2),
		NotRewinded_FEE			NUMBER(5,2),
		Taxes					NUMBER(3,2),
CONSTRAINT Rental_Invoice_PK PRIMARY KEY (RentalID),
CONSTRAINT Rental_Invoice_FK1 FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID),
CONSTRAINT Rental_Invoice_FK2 FOREIGN KEY (Store_SerialNumber) REFERENCES MovieFormat (Store_SerialNumber));


CREATE TABLE Distributor
		(DistributorID			NUMBER(11) NOT NULL,
		DIstributorName			VARCHAR(50),
		NumberOf_Shipments		NUMBER(8),
CONSTRAINT Distributor_PK PRIMARY KEY (DistributorID));


CREATE TABLE Distributor_Invoice
		(InvoiceNumber			NUMBER(11) NOT NULL,
		DistributorID			NUMBER(11) NOT NULL,
		Price					NUMBER(9,2),
		Quantity				NUMBER(5,0),
CONSTRAINT Distributor_Invoice_PK PRIMARY KEY (InvoiceNumber),
CONSTRAINT Distributor_Invoice_FK FOREIGN KEY (DistributorID) REFERENCES Distributor (DistributorID));

CREATE TABLE Catalog_T
		(CatalogID				NUMBER(11) NOT NULL,
		DistributorID			NUMBER(11) NOT NULL,
		DistributorMovie_ID		VARCHAR(9),
CONSTRAINT Catalog_T_PK PRIMARY KEY (CatalogID),
CONSTRAINT Catalog_T_FK FOREIGN KEY (DistributorID) REFERENCES Distributor (DistributorID));


CREATE TABLE Discount
		(DiscountID			NUMBER(11) NOT NULL,
		StoreMovie_ID		NUMBER(11) NOT NULL,
		Genre				VARCHAR(15),
		DiscountPrice		NUMBER(4,2) NOT NULL,
CONSTRAINT Discount_PK PRIMARY KEY (DiscountID),
CONSTRAINT Discount_FK FOREIGN KEY (StoreMovie_ID) REFERENCES Movie (StoreMovie_ID));

ALTER TABLE Movie ADD CONSTRAINT Movie_FK4 FOREIGN KEY (CatalogID) REFERENCES Catalog_T (CatalogID);

