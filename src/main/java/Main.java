import Users.Account;
import Users.Assistant;
import Users.Professor;
import Users.Student;
import Hogwarts.Hogwarts;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu(){
        ClearScreen();
        System.out.println("Welcome To Hogwarts Account Manager!");
        System.out.println("Specify Your Role In School.")
        System.out.println("1- Student\n2- Teacher\n3- Assistant");
        int role = input.nextInt();
        ClearScreen();
        System.out.println("What do you wish to do?")
        System.out.println("1- Login\n2- SignUp\n3- Quit");
        int task = input.nextInt();
        if(task == 3)
            return;
        ClearScreen();
        System.out.print("Username: ");
        String username = input.next();
        System.out.print("Password: ");
        String password = input.next();
        if(task == 1)
        {
            switch (role)
            {
                case 1:
                {
                    for(Student student : Hogwarts.getStudents())
                    {
                        if(student.getUsername().equals(username))
                        {
                            if(student.validatePassword(password))
                                StudentMenu();
                            else
                            {
                                System.out.println("Password is not correct.");
                                break;
                            }
                        }
                    }
                    System.out.println("UsernameNotFound!");
                    break;
                }
                case 2:
                {
                    for(Teacher teacher : Hogwarts.getTeachers())
                    {
                        if(teacher.getUsername().equals(username))
                        {
                            if(teacher.validatePassword(password))
                                TeacherMenu();
                            else
                            {
                                System.out.println("Password is not correct.");
                                break;
                            }
                        }
                    }
                    System.out.println("UsernameNotFound!");
                    break;
                }
                case 3:
                {
                    for(Assistant assistant : Hogwarts.getAssistants())
                    {
                        if(assistant.getUsername().equals(username))
                        {
                            if(assistant.validatePassword(password))
                                AssistantMenu();
                            else
                            {
                                System.out.println("Password is not correct.");
                                break;
                            }
                        }
                    }
                    System.out.println("UsernameNotFound!");
                    break;
                }
            }
        } else if (task == 2) {
            if(IsUsernameInList(username))
            {
                System.out.println("This username has already signed up.");
                break;
            }
            switch (role)
            {
                case 1:
                {
                    Student student = new Student();
                    student.setUsername(username);
                    student.setPassword(password);
                    Assistant.getInbox.add(SignUpRequest("student"));
                    break;
                }
                case 2:
                {
                    Teacher teacher = new Teacher();
                    teacher.setUsername(username);
                    teacher.setPassword(password);
                    Assistant.getInbox.add(SignUpRequest("teacher"));
                    break;
                }
                case 3:
                {
                    Assistant assistant = new Assistant();
                    assistant.setUsername(username);
                    assistant.setPassword(password);
                    Assistant.getInbox.add(SignUpRequest("assistant"));
                }
            }
            System.out.println("Your signUp request is waiting for confirmation.");
        }
        return;
    }

    public static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
