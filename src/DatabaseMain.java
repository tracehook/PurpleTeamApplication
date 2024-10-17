import java.io.IOException;
import java.util.Scanner;

// main methods
public class DatabaseMain {
    static Scanner scn = new Scanner(System.in);
    static User user = new User();
    static Account act = new Account();  
    public static void main(String[] args) throws IOException {   // main method; opens the database menu
        Scanner scn = new Scanner(System.in);
        int choice = 1;
     
        while (choice != 9) {
            choice = displayMenu();
            processChoice(choice);
        }
    }
    
    static void processChoice(int choice) throws IOException {    // database menu options
        switch (choice) {
                case 1:
                    user.openAccounts();
                    break;
                case 2:
                    act = user.login(act); 
                    break;
                case 3:
                    user.addNewAccount(act);
                    break;
                case 4:
                    user.displayAccount(act);
                    break;
                case 5:
                    user.displayAllAccounts(act);
                    break;
                case 6:  
                    user.editAccount(act);
                    break;
                case 7:
                    user.saveAccounts(act);
                    break;
                case 8:
                    user.logout(act);
                    break;
                case 9:
                    user.closeProgram(act);
                    break;                      
                default:  
                    System.out.println("Invalid Input");
                    break;
        }
    }
        
    static int displayMenu() {                                                      // displays the database menu; user enters 1-9 as the input
        String strChoice;
        int choice;
            System.out.println("\n1. Open Records");
            System.out.println("2. Log In");
            System.out.println("3. Add New Account");
            System.out.println("4. Display Employee Account");
            System.out.println("5. Display All Account Values");
            System.out.println("6. Edit Account"); 
            System.out.println("7. Save File");
            System.out.println("8. Log out");
            System.out.println("9. Close Program");
            strChoice = scn.nextLine();
            if (strChoice == "") {
                strChoice = "10";
            } 
            choice = Integer.parseInt(strChoice);
            
            return choice;
    }  
}
