/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tyler
 */
public class User {
private String username;
private String password;
public User(String username, String password)
{
    this.username = username;
    this.password = password;
    
}
public String getUserName()
{
    return username;
}
public String getPassWord()
{
    return password;
}
}

