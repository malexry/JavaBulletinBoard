/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeControllers;

import UniversalHandler.DerbyConnect;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author alexmasse
 */
public class numberGenerators {
    
    public int custNoGenerator() throws SQLException
    {
        String sql="SELECT COUNT(*) FROM CUSTOMER";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        int cNo=stmt.executeUpdate(sql);
        System.out.println(cNo);
        return cNo;
    }
    public int partNoGenerator() throws SQLException
    {
        String sql="SELECT COUNT(*) FROM CUSTOMER";
        DerbyConnect db=new DerbyConnect();
        Statement stmt=db.st();
        int pNo = 0;
        pNo = stmt.executeUpdate(sql);
        System.out.println(pNo);
        return pNo;
    }
}