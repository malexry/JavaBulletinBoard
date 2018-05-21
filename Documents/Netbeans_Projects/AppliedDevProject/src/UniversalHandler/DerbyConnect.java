/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alexmasse
 */
public class DerbyConnect {
    public String host = "jdbc:derby://localhost:1527/SmartJunkInvy";
    public String user = "root";
    public String pass = "toor";

    public Connection DBConn()
    {
        Connection con=null;
        try 
        {
            con = DriverManager.getConnection(host, user, pass);
            System.out.println("DerbyConnect~ Connection has been established");
        }
        catch (SQLException ex) 
        {
            System.out.println("DerbyConnect~ DBConn: Error$ "+ex+";");
        }
        return(con);//might be an issue calling the reconnection everytime... 
        //try to assign to an var so only needs to be caleld once in parent class
    }

    public Statement st()
    {          
        DerbyConnect c = new DerbyConnect();
        Connection conn=c.DBConn();
        try 
        {
            conn=DriverManager.getConnection(host,user,pass);
            Statement stmt=conn.createStatement();
            return stmt;
        }
        catch (SQLException ex) 
        {
            System.out.println("DerbyConnect~ st(): Error$ "+ex+";");
            return null;
        }
    }
}//2355 lines of code!!! 