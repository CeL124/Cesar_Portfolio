

--Retrieve all of your customers' names, account numbers,
--and addresses (street and zip code only), sorted by account number.
SELECT C_Name, CustomerID, Address, ZipCode
FROM Customer
ORDER BY CustomerID;


--Retrieve all of the videos rented in the last 30 days and sort in chronological rental date order.
SELECT *
FROM Rental_Invoice
WHERE RentalDate >=(SYSDATE-30)
ORDER BY RentalDate;

--Produce a list of your distributors and all their information sorted in order by company name.
SELECT DistributorName, DistributorID, NumberOf_Shipments
FROM Distributor
ORDER BY DistributorName;

 
--Update a customer name to change their maiden names to married names. You can 
--choose which row to update.  Make sure that you use the primary key column in your
--WHERE clause to affect only a specific row.
UPDATE Customer
SET C_Name = 'Bruce Wayne'
WHERE CustomerID = 00003;


--Delete customers from the database. You can choose which row to delete.
--Make sure that you use the primary key column in your WHERE clause to affect only a specific row.
DELETE FROM Customer
WHERE CustomerID = 00006;
 
SPOOL OFF