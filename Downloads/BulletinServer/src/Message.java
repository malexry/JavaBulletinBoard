/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cevans0971
 */
public class Message {
    private String message;
    private String topic;
    private String author;
    private String title;
    
    public Message(String message)
    {
        this.message = message;
    }
    
    public Message(String title, String message,String author,String topic)
    {
        this.message = message;
        this.author = author;
        this.topic= topic;
        this.title = title;
    }
    public String getTopic()
    {
        return this.topic;
    }
    public String getMessage()
    {
        return this.message;
    }
    public String getAuthor()
    {
        return this.author;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getDetails()
    {
        return title + ", " + topic + ", " + author;
    }
}