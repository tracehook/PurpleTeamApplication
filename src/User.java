import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;

// used by the user. if permitted, can view, edit, add, delete employee information
public class User {
    private ArrayList<Account> allAccounts = new ArrayList<>();
    Scanner scn = new Scanner(System.in);
    boolean fileOpen = false;
    boolean saved = true;

    public void openAccounts() throws IOException {                                     // opens the database files within the program 
        if (!fileOpen) {
            FileInputStream fileIn = new FileInputStream("accounts.txt");
            Scanner fileInput = new Scanner(fileIn);
            Account act;
            String fName, lName, email, uName, pw;
            char ac;
            while (fileInput.hasNext()) {
                fName = fileInput.next();
                lName = fileInput.next();
                email = fileInput.next();
                uName = fileInput.next();
                pw = fileInput.next();
                ac = fileInput.next().charAt(0);
                act = new Account(fName, lName, email, uName, pw, ac);
                allAccounts.add(act);
                act.displayAccount();
            }
            fileInput.close();
            fileOpen = true;
            saved = true;
        } else {
            System.out.println("File is already open.");
        }
    }

    public Account login(Account anAcct) {                             // logging in permits the possibility of full access to the program; different levels of access
        Account tmpAccount = new Account();
        if (!anAcct.getLoggedIn()) {
            String un, pw;
            int i = 0;
            boolean found = false;
            System.out.print("Enter username: ");
            un = scn.nextLine();
            un = convertToLowercase(un);
            System.out.print("Enter password: ");
            pw = scn.nextLine();

            while (i < allAccounts.size() && !found) {                    // checks database for matching credentials
                if (allAccounts.get(i).getUsername().equals(un)
                        && allAccounts.get(i).getPassword().equals(pw)) {
                    tmpAccount = allAccounts.get(i);
                    tmpAccount.setLoggedIn(true);
                    found = true;
                    System.out.println("You are now logged in.");
                }
                i++;
            }
            if (!found) {
                System.out.println("Invalid Login Credentials");
            }
        } else {
            System.out.println("You are already logged in.");
            tmpAccount = anAcct;
        }
        return tmpAccount;
    }

    public void addNewAccount(Account requestingAcct) {                                // if permitted, can add/create new employee information
        if (requestingAcct.getLoggedIn() && requestingAcct.getAccessLevel() == 'a') {
            Account act = new Account();
            act.obtainAccountValues();
            while (!uniqueUsername(act.getUsername())) {                        // checks if username is already used in database
                System.out.println("Username is already taken. Enter again.");
                act.setUsername(scn.nextLine());
            }
            allAccounts.add(act);
            System.out.println("New Account Added");
            saved = false;
        } else {
            System.out.println("To add a new account, you must be logged in as an administrator.");
        }
    }

    public void displayAccount(Account requestingAcct) {                      // if permitted, displays the information of a specific employee
        if (requestingAcct.getLoggedIn()) {
            if (requestingAcct.getAccessLevel() == 'a') {
                System.out.println("Enter the username of the account: ");
                String uname = scn.nextLine();
                int index = findAccount(uname);
                if (index != -1) {
                    allAccounts.get(index).displayAllAccountValues();
                } else {
                System.out.println("Username Not Found.");
                }
            } else if (requestingAcct.getAccessLevel() == 'e') {
                requestingAcct.displayAccount();
            }
        } else {
            System.out.println("Must be logged in to display an account.");
        }
    }

    public void displayAllAccounts(Account requestingAcct) {                            // if permitted, displays the information of every employee in the database
        if (requestingAcct.getLoggedIn() && requestingAcct.getAccessLevel() == 'a') {
            for (int i = 0; i < allAccounts.size(); i++) {
                allAccounts.get(i).displayAllAccountValues();
            }
        } else {
            System.out.println("Must be logged in as admin.");
        }
    }

    public void editAccount(Account requestingAccount) {                                       // if permitted, allows the user to edit an employee's information 
        if (requestingAccount.getLoggedIn() && requestingAccount.getAccessLevel() == 'a') {
            int i;
            String un;
            System.out.println("Enter username of account you wish to edit.");
            un = scn.nextLine();
            i = findAccount(un);
            if (i != -1) {
                allAccounts.get(i).editAccountInformation();
                saved = false;
            } else {
                System.out.println("Username does not exist.");
            }
        } else if (requestingAccount.getLoggedIn()) {
            requestingAccount.editAccountInformation();
        } else {
            System.out.println("Must be logged in to edit an account.");
        }
    }

    public void saveAccounts(Account currentAcct) throws IOException {                   // if permitted, saves the information to the database 
        if (currentAcct.getLoggedIn() && fileOpen && !saved) {
            PrintWriter pw = new PrintWriter("accounts.txt");
            for (int i = 0; i < allAccounts.size(); i++) {
                allAccounts.get(i).writeToFile(pw);
            }
            pw.close();
            saved = true;
        } else if (saved) {
            System.out.println("File is already saved.");
        } else if (!currentAcct.getLoggedIn()) {
            System.out.println("Must be logged in.");
        }
    }

    public void logout(Account currentAcct) {                                        // logs out the current logged in user 
        if (!currentAcct.getLoggedIn()) {
            System.out.println("You were never logged in.");
        } else {
            currentAcct.setLoggedIn(false);
            System.out.println("You are logged out.");
        }
    }

    public void closeProgram(Account requestingAccount) throws IOException {          // asks the user if they want to save changes to the database, then closes the program
        if (!saved) {
            System.out.println("Would you like to save your changes? yes or no");
            String answer = scn.nextLine();
            if (answer.equals("yes")) {  // saves changes to database
                saveAccounts(requestingAccount);
                System.out.println("File Saved.");
                saved = true;
            } else {                             // clears all changes
                allAccounts.clear();
                System.out.println("Changes were not saved.");
            }
        } else if (!fileOpen) {
            System.out.println("Exiting program...");
        } else {                                      // closes the database 
            allAccounts.clear();
            fileOpen = false;
            System.out.println("File is now closed."); 
        }
    }

    // private methods 
    private String convertToLowercase(String username) {        // converts all usernames to lowercase; prevents repeated usernames with different casings
        String nStr = "";
        char c;
        for (int i = 0; i < username.length(); i++) {
            c = username.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) ((int) c + 32);
            }
            nStr += c;
        }
        return nStr;
    }

    private int findAccount(String userName) {                           // converts the inputted username to lowercase, then searches the database for a match
        userName = convertToLowercase(userName);
        int i = 0;
        int returnIndex = -1;
        boolean found = false;
        while (i < allAccounts.size() && !found) {
            if (allAccounts.get(i).getUsername().equals(userName)) {
                found = true;
                returnIndex = i;
            }
            i++;
        }
        return returnIndex;
    }

    private boolean uniqueUsername(String testUsername) {               // checks database for a matching username already saved 
        int i = 0;
        boolean unique = true;
        boolean found = false;
        while (i < allAccounts.size() && !found) {
            if (allAccounts.get(i).getUsername().equals(testUsername)) {
                unique = false;
                found = true;
            }
            i++;
        }
        return unique;
    } 
}    
