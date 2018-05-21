/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPannel;

import UniversalHandler.DerbyConnect;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alexmasse
 */
public class Populate {
    public void populateCustomers() throws SQLException
    {
        DerbyConnect db=new DerbyConnect();
        try (Statement stmt = db.st()) {
            String sql0="DELETE FROM CUSTOMER";
            String sql1="INSERT INTO CUSTOMER(CUSTOMERID, CUSTOMERNAME, CUSTOMERADDRESS, CUSTOMERPHONENO) VALUES('0', 'Alberto Rodriguez', '12 Main St Chicopee MA 01013', '5558900')";
            String sql2="INSERT INTO CUSTOMER(CUSTOMERID, CUSTOMERNAME, CUSTOMERADDRESS, CUSTOMERPHONENO) VALUES('1', 'Tammy Bendz', '1 center street apt 4 lee MA 01011', '5897777')";
            String sql3="INSERT INTO CUSTOMER(CUSTOMERID, CUSTOMERNAME, CUSTOMERADDRESS, CUSTOMERPHONENO) VALUES('2', 'Vicky Mon ', '1930 Main St Chicopee MA 01013', '8990000')";
            String sql4="INSERT INTO CUSTOMER(CUSTOMERID, CUSTOMERNAME, CUSTOMERADDRESS, CUSTOMERPHONENO) VALUES('3', 'Sammy Ortiz', '1223 New Ludlow Road Gramby MA 01033', '2335555')";
            stmt.execute(sql0);
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4); 
        }
    }
        public void populateCars() throws SQLException
    {
        DerbyConnect db=new DerbyConnect();
        try (Statement stmt = db.st()) {
            String sql0="DELETE FROM cars";
            String sql1="INSERT INTO cars(VIN, Make, Model, Yearofmanu, price, engine, Transmission, EMPLOYEENO) values('qwertyfdfduiop132','Buick','Regal','2001','1000', '3800 SII','4SpeedAuto','0')";
            String sql2="INSERT INTO cars(vin, Make, Model, Yearofmanu, price, engine, Transmission, EMPLOYEENO) values('afdfsdfgdfhjkl123','Ford','ranger','2009','9300','5.7V8','6speedStick','0')";
            String sql3="INSERT INTO cars(vin, Make, Model, Yearofmanu, price, engine, Transmission, EMPLOYEENO) values('gwndma567896shst3','Honday','sonata','2010','9300','HellCat','Powerglide','1')";
            String sql4="INSERT INTO cars(vin, Make, Model, Yearofmanu, price, engine, Transmission, EMPLOYEENO) values('bm2463gsydhrnfhs3','BMW','3Series','2013','600','I4 2.0','4speedStick','1')";
            String sql5="INSERT INTO cars(vin, Make, Model, Yearofmanu, price, engine, Transmission, EMPLOYEENO) values('273yjddt2hn5k9900','Volkswagan','Jetta','1999','300','3800 SI','4speedAuto','3')";
            String sql6="INSERT INTO cars(vin, Make, Model, Yearofmanu, price, engine, Transmission, EMPLOYEENO) values('1231fdsafassdfdd2','Ford','F350','2003','2400','6.2 V8','5speedStick','1')";
            stmt.execute(sql0);
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);          
            stmt.execute(sql6);
            stmt.execute(sql5);
        }
    }
    public void populateEmployees() throws SQLException
    {
        DerbyConnect db=new DerbyConnect();
        try (Statement stmt = db.st()) {
            String sql0="DELETE FROM EMPLOYEE";
            String sql1="INSERT INTO EMPLOYEE(EMPLOYEEID, EMPLOYEENAME, EMPLOYEEADDRESS, EMPLOYEEPHONENO) VALUES('0', 'Ally Leeno', '12 Main St Chicopee MA 01013', '123123123')";
            String sql2="INSERT INTO EMPLOYEE(EMPLOYEEID, EMPLOYEENAME, EMPLOYEEADDRESS, EMPLOYEEPHONENO) VALUES('1', 'Scott Dreds', '1 center street apt 4 lee MA 01011', '4384347')";
            String sql3="INSERT INTO EMPLOYEE(EMPLOYEEID, EMPLOYEENAME, EMPLOYEEADDRESS, EMPLOYEEPHONENO) VALUES('2', 'Freddy Mendez ', '1930 Main St Chicopee MA 01013', '09876543')";
            String sql4="INSERT INTO EMPLOYEE(EMPLOYEEID, EMPLOYEENAME, EMPLOYEEADDRESS, EMPLOYEEPHONENO) VALUES('3', 'Tiffiny Turner', '1223 New Ludlow Road Gramby MA 01033', '555555555')";
            String[] sql={sql0,sql1,sql2,sql3,sql4};
            stmt.execute(sql0);
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
        }
    }
    public void populateParts() throws SQLException
    {
        DerbyConnect db=new DerbyConnect();
        try (Statement stmt = db.st()) {
            String sql0="DELETE FROM parts";
            String sql1="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('Chevy','Corvette','2002','3800 SII','null','500','0')";
            String sql2="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('Ford','F150','2006','null','6speedStick','9000','1')";
            String sql3="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('Dodge','Charger','2014','null','4SpdPwr','12000','2')";
            String sql4="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('Honda','Civic','1993','I4 2.0','null','300','3')";
            String sql5="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('Pontica','Firebird','1993','null','4speedAuto','200','4')";
            String sql6="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('Ford','F250','2003','6.2V8','null','8000','5')";
            stmt.execute(sql0);
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);          
            stmt.execute(sql6);
            stmt.execute(sql5);
        }
    }
    public void populateSold() throws SQLException
    {
        DerbyConnect db=new DerbyConnect();
        try (Statement stmt = db.st()) {
            String sql0="DELETE FROM SOLDCARS";
            String sql1="INSERT INTO SOLDCARS(VIN, Make, Model, Yearofmanu, askingprice, soldprice, engine, Transmission, EMPLOYEENOBUY, EMPLOYEENOsell, CUSTOMERID) values('jsyems789kshjdnf2','Chevy','Malibu','2001','1000','1000', '3800 Series II','4SpeedAuto','1','2','2')";
            String sql2="INSERT INTO SOLDCARS(vin, Make, Model, Yearofmanu, askingprice, soldprice, engine, Transmission, EMPLOYEENOBUY, EMPLOYEENOsell, CUSTOMERID) values('7dkspoilkjnkj2387','Ford','ranger','2009','9300','3300','5.7V8','6speedStick','3','2','2')";
            String sql3="INSERT INTO SOLDCARS(vin, Make, Model, Yearofmanu, askingprice, soldprice, engine, Transmission, EMPLOYEENOBUY, EMPLOYEENOsell, CUSTOMERID) values('rstdhfhmcdfdfdfe3','Honday','sonata','2010','9300','9300','HellCat','5SpeedPowerglide','3','0','1')";;
            String sql4="INSERT INTO SOLDCARS(vin, Make, Model, Yearofmanu, askingprice, soldprice, engine, Transmission, EMPLOYEENOBUY, EMPLOYEENOsell, CUSTOMERID) values('3456fhd78f6s6677d','BMW','3Series','2013','600','9300','Inline4 2.0','4speedStick','3','0','3')";
            String sql5="INSERT INTO SOLDCARS(vin, Make, Model, Yearofmanu, askingprice, soldprice, engine, Transmission, EMPLOYEENOBUY, EMPLOYEENOsell, CUSTOMERID) values('vwvf7ds7f7sdf7a7f','Volkswagan','Jetta','1999','300','300','3800 Series I','4speedAuto','3','3','2')";
            String sql6="INSERT INTO SOLDCARS(vin, Make, Model, Yearofmanu, askingprice, soldprice, engine, Transmission, EMPLOYEENOBUY, EMPLOYEENOsell, CUSTOMERID) values('fsda87dfjk3k3l3sy','Ford','F350','2003','2400','9300','6.2V8','5speedStick','1','3','0')";
            stmt.execute(sql0);
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);          
            stmt.execute(sql6);
            stmt.execute(sql5);
        }
    }
}
