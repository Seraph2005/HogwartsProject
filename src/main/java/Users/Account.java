package Users;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

import static Hogwarts.Hogwarts.getUsers;

public abstract class Account implements AccountManagement {
    private String password;
    private final String house;
    String username;

    public Account(String username, String password, String house) {
        this.password = DigestUtils.sha256Hex(password);
        this.house = house;
        this.username = username;
    }

    //Getters and Setters
    public String getUsername() {
        return username;
    }
    public String getHouse()
    {
        return house;
    }


    // Functionalities
    @Override
    public boolean validatePassword(String enteredPassword) {
        return DigestUtils.sha256Hex(this.password).equals(enteredPassword);
    }

    @Override
    public void changeUsername(String newUsername) {
        if(IsUsernameInList(newUsername))
        {
            System.out.println("This username is already taken.");
            return;
        }
        this.username = newUsername;
        System.out.println("Your username changed to " + newUsername);
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = DigestUtils.sha256Hex(newPassword);
    }

    public static void SignUpRequest(String role, String username, String password)
    {
        List<String> req = new ArrayList<>();
        req.add(role);
        req.add(username);
        req.add(password);
        Assistant.getSignups().add(req);
    }

    public static boolean IsUsernameInList(String username)
    {
        for(Account user : getUsers()) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
