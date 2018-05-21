/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeControllers;

import AdminPannel.AddCus;
import UniversalHandler.DerbyConnect;
import UniversalHandler.TableEditor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author alexmasse
 */
public class CarsToSoldCars {
    public String cNp;
    
    public void soldCar(String searchField) throws SQLException
    {
        String sql="SELECT * FROM CARS WHERE VIN = '" + searchField + "'";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        Statement stmt2=db.st();
        Statement stmt3=db.st();
        
        try{
            String[] loadCar=new String[8];
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                for(int i=0;i<loadCar.length;i++)
                {
                    loadCar[i]=rs.getString(i+1);
                }
                String soldPrice=JOptionPane.showInputDialog("Enter Sold Price:"); 
                String empSold=JOptionPane.showInputDialog("Enter Employee ID:"); 
                //make employee log-in
                String custID=JOptionPane.showInputDialog("Enter Customer ID:"); 
                //*****
                if(custID.equals("ADD"))
                {
                    AddCus.add();
                    TableEditor t = new TableEditor();
                    int j =t.getCustomerTableLenght();
                    custID=Integer.toString(j);
                    System.out.println(custID);
                }
                //*****
                String sql3="INSERT INTO SOLDCARS(VIN, MAKE, MODEL, YEAROFMANU, ASKINGPRICE, SOLDPRICE, ENGINE, TRANSMISSION, EMPLOYEENOBUY, EMPLOYEENOSELL, CUSTOMERID) VALUES('" + loadCar[0] + "' , '" + loadCar[1] + "','" + loadCar[2] + "','" + loadCar[3] + "','" + loadCar[4] + "','" + soldPrice + "','" + loadCar[5] + "','" + loadCar[6] + "','" + loadCar[7] + "','" + empSold + "','"+custID+"')";
                System.out.println(sql3);
                String sql2="DELETE FROM CARS WHERE VIN='"+loadCar[0]+"'";
                stmt3.execute(sql2);
                stmt2.execute(sql3);
            }
            rs.close();
        }
        catch(SQLException ex)
        {
           System.out.println("SQLException(ListAll): " + ex);
        }
        stmt.close();
        stmt2.close();
        stmt3.close();
        
    }

    /**
     * @return the cNp
     */
    public String getcNp() {
        return cNp;
    }

    /**
     * @param cNp the cNp to set
     */
    public void setcNp(String cNp) {
        this.cNp = cNp;
    }
}
