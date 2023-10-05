package emailclient;

public abstract class Recipient {

    private static int recipientCount=0;
    
    public Recipient(){
        recipientCount+=1;
    }

    public static int getRecipientCount(){
        return recipientCount;
    }
}
