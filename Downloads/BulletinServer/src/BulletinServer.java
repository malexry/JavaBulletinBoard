import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
 
public class BulletinServer
{
   // private Socket socket;
    private ServerSocket serverSocket;
    public ArrayList<Message> messageArray;
    private int count = 0;
    private int portNumber;
    private ArrayList<BulletinConnectionHandler> connections = null;
    private ArrayList<User> userList = null;
    public ArrayList<User> newUsers = null;
     
    public BulletinServer(int newPortNumber)
    {
        portNumber = newPortNumber;
        serverSocket = null;
        connections = new ArrayList<BulletinConnectionHandler>();
        messageArray = new ArrayList<Message>();
        userList = new ArrayList<User>();
        newUsers = new ArrayList<User>();
    }
     
    public static void main(String[] arg)
    {
        
        BulletinServer bs = new BulletinServer(Integer.parseInt(arg[0]));
        
        try {
                bs.start();
                bs.loadMessages();
                bs.loadUsers();

                while(true) {
                    bs.acceptConnection(bs);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    //bs.saveMessages();
                    //bs.saveUsers();
                    bs.terminate();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
                 
    }
     
    //initiates the server socket
    public void start() throws IOException
    {
        serverSocket = new ServerSocket(portNumber);
        System.out.println("Server succesfully created on " + portNumber);
    }
    
    //adds message object to arraylist then appends textfile
    public void updateMessages(Message m)
    {
        messageArray.add(m);
        //saveMessage(m);
    }
    
    //adds a user object onto arraylist then appends the textfile
    public void addUser(User u)
    {
        userList.add(u);
        //saveUser(u);
    }
    
    public ArrayList<User> getUserList()
    {
        return userList;
    }
    //accepts client, launches connection handler, adds them to a list, and then starts the thread
    public void acceptConnection(BulletinServer bs) throws IOException
    {
             
            if(checkActiveConnections() < 10)
            {
               Socket socket = serverSocket.accept();
               //BulletinConnectionHandler bch = new BulletinConnectionHandler(socket, messageArray, userList, connections); 
               BulletinConnectionHandler bch = new BulletinConnectionHandler(socket, bs);
               connections.add(bch);
               bch.start();
               System.out.println("Client found");
            }   
    }
      
    //loops through the array of threads and returns the number of still active threads
    public int checkActiveConnections()
    {
        int num = 0;
        for(int i = 0; i < connections.size(); i++)
        {
            if(connections.get(i).isAlive())
                num++;
        }return num;
    }
    
    public void terminate() throws IOException
    {
        serverSocket.close();
        System.out.println("Server terminated");
    }
    //sets the array of message objects (messageArray) equal to the contents of the textfile Messages.txt
    
    public void loadMessages()
    {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("messages.txt"));
            String line, tag, value;
            String[] words;
            StringTokenizer st;
            while((line = br.readLine()) != null)
            {
                st = new StringTokenizer(line, ":");
                tag = st.nextToken();
                value = st.nextToken();
                words = value.split("`");
                messageArray.add(new Message(tag, words[0], words[1], words[2]));
            }
            br.close();
            System.out.println("Message's loaded");
        }
        catch(IOException e)
        {
            System.out.println("Error loading messages");
        }
    }
    
    //saves the message array into a file called messages.txt located in the project folder
    public void saveMessages()
    {
        BufferedWriter bw;
        try
        {
            bw = new BufferedWriter(new FileWriter("messages.txt", false));
             
            for(int i = 1; i < messageArray.size(); i++)
            {
                bw.append(messageArray.get(i).getTitle() + ":" + messageArray.get(i).getMessage() + 
                        "`" + messageArray.get(i).getAuthor() + "`" + messageArray.get(i).getTopic());
                bw.newLine();
            }
            bw.close();
            System.out.println("Messages saved");
        }
        catch(IOException e)
        {
            System.out.println("error writing to memory");
        }
    }
    
    public void saveUsers()
    {
        BufferedWriter bw;
        try
        {   
            bw = new BufferedWriter(new FileWriter("users.txt", false));
             
            for(int i = 0; i < newUsers.size(); i++)
            {
                bw.newLine();
                bw.append(newUsers.get(i).getUserName() + ":" + newUsers.get(i).getPassWord());
                bw.newLine();
            }
            bw.close();
            System.out.println("User's saved");
        }
        catch(IOException e)
        {
             e.printStackTrace();
        }
    }
     
    public void loadUsers()
    {
        BufferedReader br;
        try
        {
            br = new BufferedReader(new FileReader("users.txt"));
            String line;
            StringTokenizer st;
            while((line = br.readLine()) != null)
            {
                st = new StringTokenizer(line, ":");
                userList.add( new User(st.nextToken(), st.nextToken()));
            }
            br.close();
            System.out.println("User's loaded");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }    
}
