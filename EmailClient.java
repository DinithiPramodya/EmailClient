package emailclient;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;   
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.Month;
public class EmailClient {

    public static void main(String[] args) {

        //Creating objects for each email recipient and sending birthday greetings to the relevant recipients
        try{
            File f = new File("D:\\Documents\\Uni\\Sem 2\\MyApp\\src\\emailclient\\clientList.txt");
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()){
                String record = reader.nextLine();
                String[] temp = record.split("[:]",0);
                String[] recDetails = record.split("[,]", 0);
                
                if(temp[0].equals("Official")){
                    new OfficialRecipient();
                    
                }
                else {
                   
                    Month[] months = {Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER };
                    int birthDate = Integer.parseInt(recDetails[3].substring(8));
                    
                    int x = Integer.parseInt(recDetails[3].substring(5,7));
                    Month birthMonth = months[x-1];

                    LocalDate today = LocalDate.now();
                    int todaysDate = today.getDayOfMonth();
                    Month thisMonth = today.getMonth();

                    
                    
                    
                    if (temp[0].equals("Office_friend")){
                        
                        new OfficialFriend();
                        
                        if (thisMonth == birthMonth && todaysDate == birthDate){
                            String emailAddress = recDetails[1].trim();
                            SendMails.sendEmails(emailAddress, "Birthday Greetings", "Wish you a happy birthday.\nDinithi");
                        }
                    }
                    else {
                        new PersonalRecipient();

                        if (thisMonth == birthMonth && todaysDate == birthDate){
                            String emailAddress = recDetails[2].trim();
                            SendMails.sendEmails(emailAddress, "Birthday Greetings", "Hugs and love on your birthday.\nDinithi");
                        }
                    }

                }
            
                        
                 
                    
                }
                reader.close();
            }
            
        
        catch(FileNotFoundException e){
            System.out.println(" exception");

        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
            + "1 - Adding a new recipient\n"
            + "2 - Sending an email\n"
            + "3 - Printing out all the recipients who have birthdays\n"
            + "4 - Printing out details of all the emails sent\n"
            + "5 - Printing out the number of recipient objects in the application");


        int option = scanner.nextInt();
        scanner.nextLine();

        switch(option){
            case 1:
                // input format - Official: nimal,nimal@gmail.com,ceo
                // Use a single input to get all the details of a recipient
                // code to add a new recipient
                // store details in clientList.txt file
                // Hint: use methods for reading and writing files

                System.out.println("Input format : \n    For official recipients -  Official: <name>,<email>,<designation>\n    For Official friends - Office_friend: <name>,<email>,<designation>,<birthday>\n    For Personal recipients - Personal: <name>,<nickname>,<email>,<birthday>");
                
                String details = scanner.nextLine();
                try {
                    
                    
                    FileWriter writer = new FileWriter("D:\\Documents\\Uni\\Sem 2\\MyApp\\src\\emailclient\\clientList.txt", true);
                    writer.write(details+"\n");
                    
                    
                    writer.close();
                    
                    } catch (IOException e) {
                    
                    e.printStackTrace();
                    }

                
                break;
            case 2:
                // input format - email, subject, content
                // code to send an email
                System.out.println("Input format - Email address, Subject, Content");
                String mailDetails = scanner.nextLine();
                String[] detailsList = mailDetails.split("[,]",0);
                Email email = new Email(detailsList[0], detailsList[1], detailsList[2]);
                SendMails.sendEmails(detailsList[0], detailsList[1], detailsList[2]);
                email.serialize(email);
                

                break;
            case 3:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print recipients who have birthdays on the given date
                System.out.println("Input format - yyyy/mm/dd");
                String date = scanner.nextLine();
                try{
                    File f = new File("D:\\Documents\\Uni\\Sem 2\\MyApp\\src\\emailclient\\clientList.txt");
                    Scanner reader = new Scanner(f);
                    while ( reader.hasNextLine()){
                        String record = reader.nextLine();
                        String[] recDetails = record.split("[,]",0);
                        String[] temp = recDetails[0].split("[:]", 0);
                        String name = temp[1].trim();


                        if (recDetails.length == 4){
                            if (date.substring(5).equals(recDetails[3].substring(5)))
                                System.out.println(name);
                        }
                    }
                    reader.close();
                }

                catch(FileNotFoundException e){
                    System.out.println(" exception");
        
                }
                break;
            case 4:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print the details of all the emails sent on the input date
                


                break;
            case 5:
                // code to print the number of recipient objects in the application
                
                System.out.println("Number of recipient objects: " + Recipient.getRecipientCount());
                break;

        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes
        scanner.close();
        

          

      }
}
