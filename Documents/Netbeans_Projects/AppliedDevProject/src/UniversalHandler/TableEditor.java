/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author alexmasse
 */
public class TableEditor {
    public int CcolumnsNo;
    public int EcolumnsNo;
    public int PcolumnsNo;
    public int getCustomerTableLenght() throws SQLException
    {
        String sql="SELECT * FROM CUSTOMER";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        try
        {
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                CcolumnsNo++;
                }
        }
        catch(SQLException ex)
        {
           System.out.println("SQLException(ListAll): " + ex);
        }
        return CcolumnsNo;
    }
    public int getEmployeeTableLenght() throws SQLException
    {
        String sql="SELECT * FROM EMPLOYEE";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        try
        {
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                EcolumnsNo++;
            }
        }
        catch(SQLException ex)
        {
           System.out.println("SQLException(ListAll): " + ex);
        }
        return EcolumnsNo;
    }
    public int getPartsTableLenght()
    {
        String sql="SELECT * FROM Parts";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        try
        {
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                PcolumnsNo++;
            }
        }
        catch(SQLException ex)
        {
           System.out.println("SQLException(ListAll): " + ex);
        }
        return PcolumnsNo;
    }
    
}