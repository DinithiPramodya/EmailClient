package emailclient;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class AddRecipient {
    public static void writeToFile(String details){
        try {
            FileWriter myWr = new FileWriter("clientList.txt");
            myWr.write(details);
            myWr.close();
            
          } catch (IOException e) {
           
            e.printStackTrace();
          }
    }

}
