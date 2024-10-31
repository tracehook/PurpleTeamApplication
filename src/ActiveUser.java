import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ActiveUser {
    private String username;
    private String password;
    private boolean loggedIn; // Variable to keep track of the login status

    public ActiveUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false; // Initialize loggedIn to false
    }

    public boolean verifyUser() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                // Check if there are enough elements in the array
                if (data.length >= 6) { // Adjusting index based on your data structure
                    String fileUsername = data[4].trim();
                    String filePassword = data[5].trim();
                    if (fileUsername.equalsIgnoreCase(username) && filePassword.equals(password)) {
                        loggedIn = true; // Set loggedIn to true if verified
                        return true; // Return true if the user is verified
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; 
    }

    public boolean isLoggedIn() {
        return loggedIn; // Method to access the loggedIn status
    }

    public int getAccessLevel() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                // Check if there are enough elements in the array
                if (data.length >= 7) { // Adjusting index based on your data structure
                        return Integer.parseInt(data[6].trim()); // Return the access level as an integer
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0; // Return -1 if the access level is not found
    }
}
