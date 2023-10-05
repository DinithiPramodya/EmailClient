package emailclient;
import java.io.*;

public class Email implements Serializable  {
    private String recipient;
    private String subject;
    private String content;

    public Email(String recipient, String subject, String content){
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }
    
    public String getRecipient(){
        return this.recipient;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getContent(){
        return this.content;
    }

    public void serialize(Email email){
        try{
            FileOutputStream fileStream = new FileOutputStream("sentmails.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(email);
            os.flush();
            os.close();
            
        }
        catch(Exception e)
            {System.out.println(e);};
    }
    

    public void deser(){
        try{
            ObjectInputStream in  = new ObjectInputStream(new FileInputStream("sentmails.ser"));
            
            
            in.close();
            
        }
        catch(Exception e){System.out.println(e);}
    }
}
