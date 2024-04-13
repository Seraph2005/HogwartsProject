package Users;

import Course.Course;
import Hogwarts.Hogwarts;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static Hogwarts.Hogwarts.getUsers;

public abstract class Account implements AccountManagement {
    private String username;
    private String password;
    private String house;

    //Getters and Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }
    public void setHouse(String house)
    {
        this.house = house;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
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
        this.password = newPassword;
    }

    public void SignUpRequest(String role, String username, String password)
    {
        List<String> req = new ArrayList<>();
        req.add(role);
        req.add("SignUp");
        req.add(username);
        req.add(password);
        Assistant.getInbox().add(req);
    }

    public void NewCourseRequest(String role, String course)
    {
        List<String> req = new ArrayList<>();
        req.add(role);
        req.add("NewCourse");
        req.add(course);
        Assistant.getInbox().add(req);
    }

    public static boolean IsUsernameInList(String username)
    {
        for(Account user : getUsers())
        {
            if(user.getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }

}
