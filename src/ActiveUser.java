import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ActiveUser class represents a user with a username and password,
 * and provides methods to verify the user and check their access level.
 */
public class ActiveUser {
    private final String username;
    private final String password;
    private boolean loggedIn; // Variable to keep track of the login status

    /**
     * Constructs an ActiveUser with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public ActiveUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false; // Initialize loggedIn to false
    }

    /**
     * Verifies the user by checking the username and password against the data in the Employees.txt file.
     *
     * @return true if the user is verified, false otherwise
     */
    public boolean verifyUser() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 6) {
                    String fileUsername = data[4].trim();
                    String filePassword = data[5].trim();
                    if (fileUsername.equalsIgnoreCase(username) && filePassword.equals(password)) {
                        loggedIn = true; // Set loggedIn to true if verified
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Retrieves the access level of the user from the Employees.txt file.
     *
     * @return the access level of the user, or 0 if the access level is not found
     */
    public int getAccessLevel() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 7) {
                    return Integer.parseInt(data[6].trim()); // Return the access level as an integer
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}