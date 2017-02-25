

DELETE MovieCastMember;
DELETE Discount;
DELETE Distributor_Invoice;
DELETE Rental_Invoice;
DELETE MovieFormat;
DELETE Movie;
DELETE Catalog_T;
DELETE Distributor;
DELETE Actor;
DELETE Award;
DELETE Director;
DELETE Customer;

INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES 
(00001, 'Cesar Lopez', '3752 89th street', 'New York City', 'NY', '11372', '718-898-2868', 144, 12, 144);
INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00002, 'Efrain Lopez', '7925 Spiceberry Cir', 'Gaithersburg', 'MD','20877', '240-366-6076', 132, 11, 132);
INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00003, 'Bruce Banner', '1176 Prince Hall Ct', 'Gaithersburg', 'MD','20877', '240-366-1111', 120, 10, 120);
INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00004, 'Chris Kyle', '1673 SEAL St', 'Fredrick', 'MD','20704', '240-777-2376', 12, 1, 21);
INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00005, 'Tony Stark', '1777 Rich St', 'Potomac', 'MD','20422', '240-222-6776', 72, 6, 202);
INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00006, 'Clark Kent', '3432 Smallville Rd', 'Hagerstown', 'MD','20222', '240-911-6776', 24, 2, 42);
INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00007, 'Scott Kelly', '345 Astronaut way', 'Bethesda', 'MD','20890', '240-123-5676', 56, 9, 90);

INSERT INTO Director
(DirectorID, DirectorName, DirectorGender)
VALUES
(01,'Woody Allen','Male'); 
INSERT INTO Director
(DirectorID, DirectorName, DirectorGender)
VALUES
(02,'Ridley Scott' ,'Male');
INSERT INTO Director
(DirectorID, DirectorName, DirectorGender)
VALUES
(03,'Quentin Tarantino','Male');
INSERT INTO Director
(DirectorID, DirectorName, DirectorGender)
VALUES
(04,'Kathryn Bigelow','female');
INSERT INTO Director
(DirectorID, DirectorName, DirectorGender)
VALUES
(05,'Christopher Nolan','Male');
INSERT INTO Director
(DirectorID, DirectorName, DirectorGender)
VALUES
(06,'Zack Snyder','Male');

INSERT INTO Actor
(ActorID, ActorName, ActorGender)
VALUES
(001,'Matt Damon','Male');
INSERT INTO Actor
(ActorID, ActorName, ActorGender)
VALUES
(002,'Mark Wahlberg','Male');
INSERT INTO Actor
(ActorID, ActorName, ActorGender)
VALUES
(003,'Will Smith','Male');
INSERT INTO Actor
(ActorID, ActorName, ActorGender)
VALUES
(004,'Tina Fey','Female');
INSERT INTO Actor
(ActorID, ActorName, ActorGender)
VALUES
(005,'Jennifer Lawrence','Female');
INSERT INTO Actor
(ActorID, ActorName, ActorGender)
VALUES
(006,' Ben Affleck','Male');

INSERT INTO Award
(AwardID, AwardCatagory)
VALUES
(1011, 'Academy Award for Best Director');
INSERT INTO Award
(AwardID, AwardCatagory)
VALUES
(1012, 'Academy Award for Best Actor');
INSERT INTO Award
(AwardID, AwardCatagory)
VALUES
(1013, 'Academy Award for Best Actress');
INSERT INTO Award
(AwardID, AwardCatagory)
VALUES
(1014, 'Academy Award for Best Production Design');
INSERT INTO Award
(AwardID, AwardCatagory)
VALUES
(1015, 'Academy Award for Best Animated Feature');
INSERT INTO Award
(AwardID, AwardCatagory)
VALUES
(1016, 'Best Documentary Feature');

INSERT INTO Distributor
(DistributorID, DistributorName, NumberOf_Shipments)
VALUES
(5001, 'The MovieMan inc.', 57);
INSERT INTO Distributor
(DistributorID, DistributorName, NumberOf_Shipments)
VALUES
(5002, 'ABC Movie Districts', 237);
INSERT INTO Distributor
(DistributorID, DistributorName, NumberOf_Shipments)
VALUES
(5003, 'The Middle Man Corporation', 10);
INSERT INTO Distributor
(DistributorID, DistributorName, NumberOf_Shipments)
VALUES
(5004, 'Columbia House Inc', 352);
INSERT INTO Distributor
(DistributorID, DistributorName, NumberOf_Shipments)
VALUES
(5005, 'Film Delivery Boys LLC', 1052);

INSERT INTO Catalog_T
(CatalogID, DistributorID, DistributorMovie_ID)
VALUES
(60001, 5005, 'D00001');
INSERT INTO Catalog_T
(CatalogID, DistributorID, DistributorMovie_ID)
VALUES
(60002, 5004, 'D00002');
INSERT INTO Catalog_T
(CatalogID, DistributorID, DistributorMovie_ID)
VALUES
(60003, 5003, 'D00003');
INSERT INTO Catalog_T
(CatalogID, DistributorID, DistributorMovie_ID)
VALUES
(60004, 5002, 'D00004');
INSERT INTO Catalog_T
(CatalogID, DistributorID, DistributorMovie_ID)
VALUES
(60005, 5001, 'D00005');

INSERT INTO Distributor_Invoice
(InvoiceNumber, DistributorID, Price, Quantity)
VALUES
(9000001, 5001, 532.98, 240);
INSERT INTO Distributor_Invoice
(InvoiceNumber, DistributorID, Price, Quantity)
VALUES
(9000002, 5002, 932.98, 423);
INSERT INTO Distributor_Invoice
(InvoiceNumber, DistributorID, Price, Quantity)
VALUES
(9000003, 5003, 123.45, 67);
INSERT INTO Distributor_Invoice
(InvoiceNumber, DistributorID, Price, Quantity)
VALUES
(9000004, 5004, 84.98, 24);
INSERT INTO Distributor_Invoice
(InvoiceNumber, DistributorID, Price, Quantity)
VALUES
(9000005, 5005, 5032.98, 850);

--For the Movie table, the rating scale is 01 worst --> 10 best
INSERT INTO Movie
(StoreMovie_ID, ActorID, DirectorID, AwardID, CatalogID, MovieTitle, Genre, YearReleased, MovieLength, Rating)
VALUES
(100000001, 004, 05, 1013, 60001, 'Whiskey Foxtrot Tango', 'Comedy', TO_DATE('2016','YYYY'), '96 Minutes', 07);
INSERT INTO Movie
(StoreMovie_ID, ActorID, DirectorID, AwardID, CatalogID, MovieTitle, Genre, YearReleased, MovieLength, Rating)
VALUES
(100000002, 002, 04, 1016, 60002, 'Lone Survivor', 'Action', TO_DATE('2013','YYYY'), '121 Minutes', 08);
INSERT INTO Movie
(StoreMovie_ID, ActorID, DirectorID, AwardID, CatalogID, MovieTitle, Genre, YearReleased, MovieLength, Rating)
VALUES
(100000003, 003, 03, 1011, 60003, 'I AM LEGEND', 'Horror', TO_DATE('2007','YYYY'), '101 Minutes', 06);
INSERT INTO Movie
(StoreMovie_ID, ActorID, DirectorID, AwardID, CatalogID, MovieTitle, Genre, YearReleased, MovieLength, Rating)
VALUES
(100000004, 001, 02, 1014, 60004, 'The Martian', ' Adventure', TO_DATE('2015','YYYY'), '144 Minutes', 10);
INSERT INTO Movie
(StoreMovie_ID, ActorID, DirectorID, AwardID, CatalogID, MovieTitle, Genre, YearReleased, MovieLength, Rating)
VALUES
(100000005, 006, 06, 1012, 60005, 'Batman v Superman: Dawn of Justice', 'Adventure', TO_DATE('2016','YYYY'), '151 Minutes', 01);
INSERT INTO Movie
(StoreMovie_ID, ActorID, DirectorID, AwardID, CatalogID, MovieTitle, Genre, YearReleased, MovieLength, Rating)
VALUES
(100000006, 005, 01, 1015, 60004, 'X-Men: Days of Future Past', 'Fantasy', TO_DATE('2014','YYYY'), '132 Minutes', 05);

INSERT INTO MovieCastMember
(StoreMovie_ID, ActorID)
VALUES
(100000001, 004);
INSERT INTO MovieCastMember
(StoreMovie_ID, ActorID)
VALUES
(100000002, 002);
INSERT INTO MovieCastMember
(StoreMovie_ID, ActorID)
VALUES
(100000003, 003);
INSERT INTO MovieCastMember
(StoreMovie_ID, ActorID)
VALUES
(100000004, 001);
INSERT INTO MovieCastMember
(StoreMovie_ID, ActorID)
VALUES
(100000005, 006);
INSERT INTO MovieCastMember
(StoreMovie_ID, ActorID)
VALUES
(100000006, 005);

INSERT INTO Discount
(DiscountID, StoreMovie_ID, Genre, DiscountPrice)
VALUES
(560001, 100000006, 'Fantasy', 5.00);
INSERT INTO Discount
(DiscountID, StoreMovie_ID, Genre, DiscountPrice)
VALUES
(560002, 100000002, 'Action', 3.50);
INSERT INTO Discount
(DiscountID, StoreMovie_ID, Genre, DiscountPrice)
VALUES
(560003, 100000003, 'Horror', 4.25);
INSERT INTO Discount
(DiscountID, StoreMovie_ID, Genre, DiscountPrice)
VALUES
(560004, 100000005, 'Adventure', 4.75);
INSERT INTO Discount
(DiscountID, StoreMovie_ID, Genre, DiscountPrice)
VALUES
(560005,100000001, 'Comedy', 3.99);

INSERT INTO MovieFormat
(Store_SerialNumber, StoreMovie_ID, Distributor_SerialNumeber, DVDorVHS)
VALUES
(34001, 100000001, 089001, 'DVD');
INSERT INTO MovieFormat
(Store_SerialNumber, StoreMovie_ID, Distributor_SerialNumeber, DVDorVHS)
VALUES
(34002, 100000002, 089002, 'DVD');
INSERT INTO MovieFormat
(Store_SerialNumber, StoreMovie_ID, Distributor_SerialNumeber, DVDorVHS)
VALUES
(34003, 100000003, 089003, 'VHS');
INSERT INTO MovieFormat
(Store_SerialNumber, StoreMovie_ID, Distributor_SerialNumeber, DVDorVHS)
VALUES
(34004, 100000004, 089004, 'VHS');
INSERT INTO MovieFormat
(Store_SerialNumber, StoreMovie_ID, Distributor_SerialNumeber, DVDorVHS)
VALUES
(34005, 100000005, 089005, 'DVD');

INSERT INTO Rental_Invoice
(RentalID, CustomerID, Store_SerialNumber, RentalDate, RentalReturn, LateFees, DamageFees, NotRewinded_FEE, Taxes)
VALUES
(200001, 00001, 34001, TO_DATE('2016/06/27', 'yyyy/mm/dd'), TO_DATE('2016/07/07', 'yyyy/mm/dd'), 1.02, 2.00, 1.50, 2.50);
INSERT INTO Rental_Invoice
(RentalID, CustomerID, Store_SerialNumber, RentalDate, RentalReturn, LateFees, DamageFees, NotRewinded_FEE, Taxes)
VALUES
(200002, 00002, 34002, TO_DATE('2016/04/06', 'yyyy/mm/dd'), TO_DATE('2016/04/13', 'yyyy/mm/dd'), 1.50, 2.50, 1.00, 2.20);
INSERT INTO Rental_Invoice
(RentalID, CustomerID, Store_SerialNumber, RentalDate, RentalReturn, LateFees, DamageFees, NotRewinded_FEE, Taxes)
VALUES
(200003, 00003, 34003, TO_DATE('2016/06/30', 'yyyy/mm/dd'), TO_DATE('2016/07/12', 'yyyy/mm/dd'), 1.62, 2.40, 1.50, 2.50);
INSERT INTO Rental_Invoice
(RentalID, CustomerID, Store_SerialNumber, RentalDate, RentalReturn, LateFees, DamageFees, NotRewinded_FEE, Taxes)
VALUES
(200004, 00004, 34004, TO_DATE('2016/07/01', 'yyyy/mm/dd'), TO_DATE('2016/07/10', 'yyyy/mm/dd'), 3.02, 5.00, 6.50, 5.50);
INSERT INTO Rental_Invoice
(RentalID, CustomerID, Store_SerialNumber, RentalDate, RentalReturn, LateFees, DamageFees, NotRewinded_FEE, Taxes)
VALUES
(200005, 00005, 34005, TO_DATE('2016/01/16', 'yyyy/mm/dd'), TO_DATE('2016/01/23', 'yyyy/mm/dd'), 1.34, 3.00, 2.50, 3.50);



