/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeControllers;

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
public class CarToParts {
    public void partCar(String searchField) throws SQLException
    {
        TableEditor p = new TableEditor();
        int tj=p.getPartsTableLenght();
        String pj = Integer.toString(tj);
        String pj2 = Integer.toString(tj+1);
        String sql="SELECT * FROM CARS WHERE VIN = '" + searchField + "'";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        Statement stmt2=db.st();
        Statement stmt3=db.st();
        try{
            String[] loadCar=new String[8];
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                for(int i=0;i<loadCar.length;i++)
                {
                    loadCar[i]=rs.getString(i+1);
                }
                String enginePrice=JOptionPane.showInputDialog("Engine Price:"); 
                String transPrice=JOptionPane.showInputDialog("Transmission Price:"); 
                String sql3="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('" + loadCar[1] + "','" + loadCar[2] + "','" + loadCar[3] + "','" + loadCar[5] + "','null','" + enginePrice + "','" + pj + "')";
                String sql4="INSERT INTO parts(Make, Model, Yearofmanu, engine, Transmission, price, number) values('" + loadCar[1] + "','" + loadCar[2] + "','" + loadCar[3] + "','null','" + loadCar[6] +"','" + transPrice + "','" + pj2 + "')";
                System.out.println(sql3);
                System.out.println(sql4);
                stmt3.execute(sql3);
                stmt3.execute(sql4);
                String sql2="DELETE FROM CARS WHERE VIN='"+loadCar[0]+"'";
                stmt2.execute(sql2);
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
    public void delParts(String search) throws SQLException
    {
        String sql="DELETE FROM PARTS WHERE NUMBER='"+search+"'";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        stmt.execute(sql);
    }
}
