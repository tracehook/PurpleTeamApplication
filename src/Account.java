import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class Account {                              // private variables used within the class
    private Scanner scn = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String password;
    private char accessLevel;
    private boolean loggedIn = false;

    // constructors
    public Account() {}

    public Account(String firstName, String lastName, String emailAddress,
            String username, String password, char accessLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public Account(String firstName, String lastName, String emailAddress, String username, String password, String accessLevel) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmailAddress(emailAddress);
        setUsername(username);
        setPassword(password);
        setAccessLevel(accessLevel);
    }

    // mutators
    public void setFirstName(String firstName) {                          // checks if first name meets all the requirements, then assigns it
        while (!validName(firstName)) {
            System.out.println("Invalid. Enter first name again.");
            firstName = scn.nextLine();
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {                           // checks if last name meets all the requirements, then assigns it
        while (!validName(lastName)) {
            System.out.println("Invalid. Enter last name again.");
            lastName = scn.nextLine();
        }
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {                     // checks if email meets all the requirements, then assigns it
        while (!validEmailAddress(emailAddress)) {
            System.out.println("Invalid email address. Enter again.");
            emailAddress = scn.nextLine();
        }
        this.emailAddress = emailAddress;
    }

    public void setUsername(String username) {                   // converts the username to lowercase, then assigns it 
        this.username = convertToLower(username);
    }

    public void setPassword(String password) {                     // checks if password meets all the requirements, then assigns it
        while (!validPassword(password)) {
            System.out.println("Invalid password. Enter again.");
            password = scn.nextLine();
        }
        this.password = password;
    }

    public void setAccessLevel(String accessLevel) {                                    // checks if the given (string) access level is only one character, and only a or e
        while (!(accessLevel.length() == 1 && (accessLevel.charAt(0) == 'a' || accessLevel.charAt(0) == 'e'))) {
            System.out.println("Invalid. Enter access level again.");
            accessLevel = scn.nextLine();
        }
        this.accessLevel = accessLevel.charAt(0);
    }

    public void setAccessLevel(char accessLevel) {                            // checks if the given (character) access level is only a or e. if more than 1 character, its not a char
        boolean oneChar = true;
        String input;
        while (!((accessLevel == 'a' || accessLevel == 'e') && oneChar)) {
            System.out.println("Invalid. Enter access level again.");
            input = scn.nextLine();
            if (input.length() != 1) {
                oneChar = false;
            } else {
                oneChar = true;
                accessLevel = input.charAt(0);
            }
        }
        this.accessLevel = accessLevel;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    // accessors
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }

    public char getAccessLevel() {
        return accessLevel;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }
	
    // methods
    public void obtainAccountValues() {                           // used for adding/creating new employee data 
        System.out.print("Enter first name: ");
        setFirstName(scn.nextLine());
        System.out.print("Enter last name: ");
        setLastName(scn.nextLine());
        System.out.print("Enter email address: ");
        setEmailAddress(scn.nextLine());
        System.out.print("Enter username: ");
        setUsername(scn.nextLine());
        System.out.print("Enter password: ");
        System.out.println("Requirements:");
        System.out.println("  At least 8 characters, at least one special character, ");
        System.out.println("  lower and uppercase characters, and numeric characters.");
        setPassword(scn.nextLine());
        System.out.println("Enter Access Level: ");
        setAccessLevel(scn.nextLine());
    }

    public void displayAccount() {                                              // displays the entered employee credentials
        System.out.print("Name: " + firstName + " " + lastName);
        System.out.print("   Email: " + emailAddress);
        // can add other basic info like job title, etc. 
        System.out.println("");
    }

    public void displayAllAccountValues() {                                                 // displays all entered employee information besides password and access level
        System.out.print("\n" + lastName + ", " + firstName + "   Email: " + emailAddress);
        System.out.print("   UserName: " + username + "   LoggedIn: " + loggedIn);
        // can add other information like job title, work history, pay, etc. 
    }

    public void editAccountInformation() {                       // used to edit an employee's information
        int choice = 2;
        while (choice != 6) {                              // user enters 1-6 as the input to edit a certain field, or leave the menu
            System.out.println("1. Edit First Name");
            System.out.println("2. Edit Last Name");
            System.out.println("3. Edit Email Address");
            System.out.println("4. Change Password");
            System.out.println("5. Change Access Level");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scn.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter first name: ");
                    setFirstName(scn.nextLine());
                    break;
                case 2:
                    System.out.print("Enter last name: ");
                    setLastName(scn.nextLine());
                    break;
                case 3:
                    System.out.print("Enter email address: ");
                    setEmailAddress(scn.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new password: ");
                    setPassword(scn.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Access Level");
                    setAccessLevel(scn.nextLine());
                    break;
                case 6:
                    System.out.print("Exiting edit-menu...");
                    break;
                default:                                            // if user does not enter 1-6, tries again
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    protected void writeToFile(PrintWriter p) {                                             // writes the employee information to the database file saved locally
        p.println(firstName + " " + lastName + " " + emailAddress + " "
                + username + " " + password + " " + accessLevel);
    }

    protected String convertToLower(String username) {                             // converts the username to all lowercase 
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

    private boolean validEmailAddress(String emailCheck) {                   // checks if the email address meets the requirements of a real email address
        boolean passes = true;
        int i = 0;
        char c;
        int numberOfAtSigns = 0;
        boolean numberOfCharactersAfterDotPasses = false;
        int dotLocation = 0;

        while (i < emailCheck.length() && passes) {         
            c = emailCheck.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '@' || c == '.')) {  // invalid if there arent characters/numbers + @ + period
                passes = false;
            }
            if (c == '@') {         // unless there is only one @ symbol, it is invalid
                numberOfAtSigns++;
                if (i == 0) {
                    passes = false;
                }
            } else if (c == '.') {  // invalid if period is the first character
                dotLocation = i;
                if (i == 0) {
                    passes = false;
                }
            }
            i++;
        }
        int numberOfCharactersAfterLastDot = (emailCheck.length() - 1) - dotLocation;      // gets number of characters after the period

        if (numberOfCharactersAfterLastDot == 3 || numberOfCharactersAfterLastDot == 2) {        // makes sure there is only 2 or 3 characters after the period
            numberOfCharactersAfterDotPasses = true;
        }

        if (!(numberOfAtSigns == 1 && numberOfCharactersAfterDotPasses == true)) {           // invalid if @ symbol and after-the-period requirements are not met
            passes = false;
        }
        return passes;
    }

    private boolean validPassword(String checkPassword) {                          // checks if the password meets all the requirements of a real password 
        boolean uppercaseExists = false, lowercaseExists = false,
                numericCharacterExists = false, specialCharacterExists = false;
        boolean pass = false;
        char c = ' ';
        int ascii;
        int i = 0;

        if (checkPassword.length() < 8) {                                  // at least 8 characters
            pass = false;
        } else {
            while ((i < checkPassword.length()) &&
                    !(specialCharacterExists && uppercaseExists && 
                    numericCharacterExists && lowercaseExists)) {                     // at least one special character, uppercase, lowercase, and number
                c = checkPassword.charAt(i);
                ascii = (int)c;
                if ((ascii > 122 && ascii < 127) || (ascii > 90 && ascii < 97)
                        || (ascii > 57 && ascii < 65) || (ascii > 31 && ascii < 48)) {              // checks special character
                    specialCharacterExists = true;
                } else if (ascii > 64 && ascii < 91) {                      // checks uppercase
                    uppercaseExists = true;
                } else if (ascii > 96 && ascii < 123) {                     // checks lowercase
                    lowercaseExists = true;
                } else if (ascii > 47 && ascii < 58) {                      // checks number 
                    numericCharacterExists = true; 
                }
                i++;
            }
        }
        if (specialCharacterExists && uppercaseExists 
                && lowercaseExists && numericCharacterExists) {            // invalid unless all are true
            pass = true;
        }
        return pass;
    }

    private boolean validName(String nameToCheck) {                                  // checks if the name is an actual name 
        boolean passes = true;
        char c;   
        for (int i = 0; i < nameToCheck.length(); i++) {
            c = nameToCheck.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                    || c == '-' || c == ' ' || (int) c == 39)) {
                passes = false;
            }
        }
        return passes;
    }
}
