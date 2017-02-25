

DELETE FROM Customer;
DELETE FROM Director;


INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00001, 'Cesar Lopez', '3752 89th street', 'New York City', 'NY', '11372', '718-898-2868', 144, 12, 144);
INSERT INTO Customer
(CustomerID, C_Name, Address, City, C_State, ZipCode, Telephone_Number, YearlyRental, MonthlyRental, TotalRental)
VALUES
(00001, 'Efrain Lopez', '7925 Spiceberry Cir', 'Gaithersburg', 'MD','20877', '240-366-6076', 132, 11, 132 );
/* 
This INSERT stament above failed the table integrity because the Customer object, Cesar and Efrain, both have 
the same primary key value and this fails the CONSTRAINT for CustomerID as primary key
I set up in the Customer Table
*/





INSERT INTO Director
(DirectorID, DirectorGender)
VALUES
(00012, 'Male'); 
/*
The above INSERT statement fails my Director Table integrity because I did not put 
DirectorName in the INSERT statement. The DirectorName column in the Director table was
declared NOT NULL, which means that there must always be a value in that column.
*/

