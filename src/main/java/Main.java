import Course.Course;
import Users.Assistant;
import Users.Teacher;
import Users.Student;
import Hogwarts.Hogwarts;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static Users.Account.IsUsernameInList;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu(){
        ClearScreen();
        System.out.println("Welcome To Hogwarts Account Manager!");
        System.out.println("Specify Your Role In School.");
        System.out.println("1- Student\n2- Teacher\n3- Assistant");
        int role = input.nextInt();
        ClearScreen();
        System.out.println("What do you wish to do?");
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
                                StudentMenu(student);
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
                                TeacherMenu(teacher);
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
                                AssistantMenu(assistant);
                            else
                            {
                                System.out.println("Password is not correct.");
                                break;
                            }
                        }
                    }
                    System.out.println("UsernameNotFound!");
                }
            }
        } else if (task == 2) {
            if(IsUsernameInList(username))
            {
                System.out.println("This username has already signed up.");
            }
            switch (role)
            {
                case 1:
                {
                    Student student = new Student();
                    Hogwarts.getUsers().add(student);
                    Hogwarts.getStudents().add(student);
                    student.SignUpRequest("student", username, password);
                    break;
                }
                case 2:
                {
                    Teacher teacher = new Teacher();
                    Hogwarts.getUsers().add(teacher);
                    Hogwarts.getTeachers().add(teacher);
                    teacher.SignUpRequest("teacher", username, password);
                    break;
                }
                case 3:
                {
                    Assistant assistant = new Assistant();
                    Hogwarts.getUsers().add(assistant);
                    Hogwarts.getAssistants().add(assistant);
                    assistant.SignUpRequest("assistant", username, password);
                    break;
                }
            }
            System.out.println("Your signUp request is waiting for confirmation.");
        }
        return;
    }

    public static void StudentMenu(Student student)
    {
        int task = 0;
        while(task != 7)
        {
            ClearScreen();
            System.out.println("Welcome to Your Dashboard " + student.getUsername() + ".\n" +
                    "What do you wish to do here?\n" +
                    "1- Take course\n" +
                    "2- View your courses and scores\n" +
                    "3- Request course\n" +
                    "4- Score teacher\n" +
                    "5- Change username\n" +
                    "6- Change password\n" +
                    "7- Logout");
            task = input.nextInt();
            ClearScreen();
            switch (task)
            {
                case 1://Taking a new course
                {
                    Hogwarts.viewAllCourses();
                    System.out.print("Enter the title: ");
                    String title = input.nextLine();
                    Boolean taken = false;
                    Boolean inList = false;
                    for (Course course : Hogwarts.getCoures()) {
                        if (title.equals(course.getTitle())) {
                            inList = true;
                            for(Course c : student.getCoursesTaken())
                            {
                                if(c.getTitle().equals(title))
                                {
                                    taken = true;
                                    System.out.println("This course is already taken.");
                                }
                            }
                            if(!taken)
                            {
                                student.getCoursesTaken().add(course);
                                System.out.println("Course succesfully added.");
                            }
                            break;
                        }
                    }
                    if(!inList)
                        System.out.println("CourseNotFound.");
                    break;
                }
                case 2://View courses taken and the score
                {
                    for(Course course : student.getCoursesTaken())
                    {
                        System.out.println("- " + course.getTitle() + " with "
                                + course.getTeacher().getUsername() + "\nScore: ");
                                student.showScore(course.getTitle());
                    }
                    break;
                }
                case 3://Requesting new course from admin
                {
                    System.out.print("Enter the title of your desired course: ");
                    String title = input.nextLine();
                    Boolean inList = false;
                    for (Course course : Hogwarts.getCoures()) {
                        if (title.equals(course.getTitle())) {
                            inList = true;
                            System.out.println("This Course Already Exists.");
                            break;
                        }
                    }
                    if(!inList) {
                        student.NewCourseRequest("student", title);
                        System.out.println("Request sent to admin.");
                    }
                    break;
                }
                case 4://Score a teacher
                {
                    for (Course course : student.getCoursesTaken())
                    {
                        System.out.println("- " + course.getTeacher().getUsername() + " for " + course.getTitle());
                    }
                    System.out.print("Enter teacher's name: ");
                    String name = input.nextLine();
                    Boolean inList = false;
                    for(Course course : student.getCoursesTaken())
                    {
                        if(course.getTeacher().getUsername().equals(name))
                        {
                            inList = true;
                            System.out.print("Enter a number from 1 to 100: ");
                            int score = input.nextInt();
                            if(score > 100)
                                score = 100;
                            if(score < 1)
                                score = 1;
                            course.getTeacher().addScore(score);
                        }
                    }
                    if(!inList)
                        System.out.println("TeacherNotFound.");
                    break;
                }
                case 5://Changing username
                {
                    System.out.print("Enter the new username: ");
                    String username = input.nextLine();
                    student.changeUsername(username);
                    break;
                }
                case 6://Changing password
                {
                    System.out.print("Enter new password: ");
                    String newPassword = input.nextLine();
                    student.changePassword(newPassword);
                    break;
                }
            }
            System.out.println("Enter a key to quit.");
            input.nextLine();
        }
    }

    public static void TeacherMenu(Teacher teacher)
    {

    }

    public static void AssistantMenu(Assistant assistant)
    {

    }

    public static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
