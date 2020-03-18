import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  
/**
 *
 * @author Alex
 */
public class BulletinConnectionHandler extends Thread{
    private Socket connection;
    private InputStream clientInput;
    private OutputStream clientOutput;
    private Scanner sc;
    private OutputStreamWriter osw;
    
    String currentUser;
    BulletinServer server;
      
    public BulletinConnectionHandler(Socket sock, BulletinServer bs)
    {
        connection = sock;
        server = bs;
        try
        {
            clientInput = connection.getInputStream();
            clientOutput = connection.getOutputStream();
            sc = new Scanner(clientInput);
            osw = new OutputStreamWriter(clientOutput);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
      
      
    public void closeAll() throws IOException
    {
         osw.close();
         clientInput.close();
         connection.close();
         sc.close();
    }
    public void logon()
    {
        boolean bool = true;
        //int attempts = 0;
        String user;
        String pass;
        try {
            while(bool)
            {
                osw.write("Enter 0 to Login or 1 to create new user.\r\n");
                osw.flush();
                int num = 0;
                if(sc.hasNextInt())
                {
                    String temp = sc.nextLine();
                    num = Integer.parseInt(temp);
                    if(num == 0)
                    {
                        while(bool)
                        {
                            osw.write("Enter username:\r\n");
                            osw.flush();
                            user = sc.nextLine();
                            osw.write("Enter password:\r\n");
                            osw.flush();
                            pass = sc.nextLine();
                            ArrayList<User> users = server.getUserList();
                            for(int i = 0; i < users.size(); i++)
                            {
                                if(users.get(i).getUserName().equalsIgnoreCase(user) && users.get(i).getPassWord().equals(pass))
                                {
                                    bool = false;
                                    currentUser = user;
                                }
                            }
                            if (bool == false)
                            {
                                osw.write("Logged in!\r\n");
                                osw.flush();
                            }
                              else
                            {
                                osw.write("Logged in FAILED!\r\n");
                                osw.flush();
                            }
                        }
                    }
                    else if(num == 1)
                    {
                        boolean check = false;
                        while(true)
                        {
                          osw.write("Enter desired username.\r\n");
                          osw.flush();
                          user = sc.nextLine();
                          ArrayList<User> users = server.getUserList();

                          for(int i = 0; i < users.size(); i++)
                          {
                              if(users.get(i).getUserName().equalsIgnoreCase(user))
                                  check = true;
                          }
                          check = validString(user);
                          if(!check)
                          {
                              osw.write("Username already taken or invalid name.\r\n");
                              osw.flush();
                          }
                          else
                          {
                              break;
                          }
                        }
                        while(true)
                        {
                            osw.write("Enter the password for your username.\r\n");
                            osw.flush();
                            pass = sc.nextLine();
                            if(validString(pass))
                                break;
                            else{
                                osw.write("Invalid password.\r\n");
                                osw.flush();
                            }
                        }
                        saveUser(new User(user, pass));
                        server.addUser(new User(user, pass));
                        osw.write("Logged in!\r\n");
                        osw.flush();
                        currentUser = user;
                        break;
                    }
                }
                else{
                    osw.write("Not a valid choice.\r\n");
                    osw.flush();
                }
            }
        } 
          
        catch (IOException ex) {
            Logger.getLogger(BulletinConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void run()
    {
        boolean b=true;
        try
        {
            logon();
            osw.write("Welcome to the Bulletin Board " + currentUser + "!\r\n");
            osw.flush();
            while(b)
            {
                osw.write("\r\n");
                osw.flush();
                b=options();
            }
             
        } catch (IOException ex) {
            Logger.getLogger(BulletinConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public boolean options() throws IOException
    {
           boolean done=true;
           int choice;
            osw.write("Enter the number equal to your choice.\r\n");
            osw.flush();
            osw.write("1) Display Latest\r\n");
            osw.flush();
            osw.write("2) Post Message\r\n");
            osw.flush();
            osw.write("3) Display All\r\n");
            osw.flush();
            osw.write("4) Author Search\r\n");
            osw.flush();
            osw.write("5) Topic Search\r\n");
            osw.flush();
            osw.write("6) logout\r\n");
            osw.flush();
            try{
                choice = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e)
            {
                choice = 0;
            }
            switch(choice)
            {
                case 1:
                    displayLatest();
                    break;
                case 2:
                    postMessage();
                    break;
                case 3:
                    displayAll();
                    break;
                case 4:
                    searchAuthor();
                    break;
                case 5:
                    searchTopic();
                    break;
                case 6:
                    logout();
                    done = false;
                    break;
                default:
                    osw.write("Invalid choice who are you Jarjar Binks?");
                    osw.flush();
                    break;
            }
    return done;
    }
    
    public void postMessage() throws IOException
    {
        osw.write("The ':' and '`' character are invalid.\r\n");
        osw.write("Enter the title of your new message.\r\n");
        osw.flush();
        String title = sc.nextLine();
        osw.write("Enter the topic of your new message.\r\n");
        osw.flush();
        String topic = sc.nextLine();
        osw.write("Enter the message.\r\n");
        osw.flush();
        String content = sc.nextLine();
        if(validString(title) && validString(topic) && validString(content))
        {
            try
            {
                Message m = new Message(title, content, currentUser, topic);
                saveMessage(m);
                server.updateMessages(m);
                osw.write("Message has been added sucessfully.\r\n");
                osw.flush();
            }catch(IOException e)
            {
                
            }
        }
        else{
            osw.write("Invalid characters used, message not saved.\r\n");
            osw.write("Would you like to try again? (y/n)\r\n");
            osw.flush();
            String answer = sc.nextLine();
            if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"))
            {
                postMessage();
            }
        }
    }
     
    public void displayAll() throws IOException
    {
        ArrayList<Message> list = server.messageArray;
        osw.write("Format == [ #) Title, Topic, Author ]\r\n");
        osw.write("\r\n");
        osw.flush();
        for(int i = 0; i < list.size(); i++)
        {
            osw.write(i + ") " + list.get(i).getDetails() + "\r\n");
            osw.flush();
        }
        while(true && list.size() > 0)
        {
            osw.write("\r\n");
            osw.write("Please enter the number of the message you want to see or -1 to quit.\r\n");
            osw.flush();
            if(sc.hasNextInt())
            {
                int num = Integer.parseInt(sc.nextLine());
                if(num == -1)
                {
                    break;
                }
                if(num < list.size() && num > -2)
                {
                    osw.write(list.get(num).getMessage() + "\r\n");
                    osw.flush();
                }
                else {
                    osw.write("Invalid selection.\r\n");
                }
            }
            else {
                    osw.write("Invalid selection.\r\n");
                }
        }
         
    }
     
     public void searchAuthor() throws IOException
     {
            osw.write("Please enter the author name.\r\n");
            osw.flush();
            String name = sc.nextLine();
            ArrayList<Message> list = server.messageArray;
           for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i).getAuthor().equalsIgnoreCase(name))
                {
                    osw.write(list.get(i).getDetails() + " " + list.get(i).getMessage() + "\r\n");
                    osw.write("\r\n");
                    osw.flush();
                }
            }
     }
      
     public void searchTopic() throws IOException
     {
            osw.write("Please enter the topic .\r\n");
            osw.flush();
            String name = sc.nextLine();
            ArrayList<Message> list = server.messageArray;
            
           for(int i = 0; i < list.size(); i++)
            {                       
                if(list.get(i).getTopic().toLowerCase().contains(name.toLowerCase()))
                {
                    osw.write(list.get(i).getDetails() + " " + list.get(i).getMessage() + "\r\n");
                    osw.write("\r\n");
                    osw.flush();
                }
            }
     }
      
     public void logout() throws IOException
     {
         osw.write("Your are now logged out.\r\n");
         osw.flush();
         closeAll();        
     }
      
     public void displayLatest() throws IOException
     {
         ArrayList<Message> latest = server.messageArray;
         if(latest.size() > 10)
         {
             for(int i = latest.size()-10; i < latest.size(); i++)
             {
                 osw.write(i + ") " + latest.get(i).getDetails());
                 osw.write("\r\n");
                 osw.flush();
             }
         }
         else{
             for(int i = 0; i < latest.size(); i++)
             {
                 osw.write(i + ") " + latest.get(i).getDetails());
                 osw.write("\r\n");
                 osw.flush();
             }
         }
         
     }
     
     //returns false if invalid string
     public boolean validString(String x)
     {
         boolean check = true;
         if(x.contains(":") || x.contains("`"))
             check = false;
         return check;
     }
     
     public void saveMessage(Message m)
    {
        BufferedWriter bw;
        try
        {
            bw = new BufferedWriter(new FileWriter("messages.txt", true));
            bw.append(m.getTitle() + ":" + m.getMessage() + "`" + m.getAuthor() + "`" + m.getTopic());
            bw.newLine();
            bw.close();
        }
        catch(IOException e)
        {
            System.out.println("Error writing message to file.");
        }
    }
     
     public void saveUser(User u)
    {
        BufferedWriter bw;
        try
        {
            bw = new BufferedWriter(new FileWriter("users.txt", true));
            //bw.newLine();
            bw.append(u.getUserName() + ":" + u.getPassWord());
            bw.newLine();
            bw.flush();
            bw.close();
            System.out.println("New User");
        }
        catch(IOException e)
        {
             System.out.println("Error saving user to file.");
        }
    }
}
