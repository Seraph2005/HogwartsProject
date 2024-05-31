import Course.Course;
import Users.Assistant;
import Users.Teacher;
import Users.Student;
import Hogwarts.Hogwarts;

import java.util.List;
import java.util.Scanner;

import static Users.Account.IsUsernameInList;
import static Users.Account.SignUpRequest;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Scanner in = new Scanner(System.in);

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
            switch (role) {
                case 1 -> {
                    for (Student student : Hogwarts.getStudents()) {
                        if (student.getUsername().equals(username)) {
                            if (student.validatePassword(password))
                                StudentMenu(student);
                            else {
                                System.out.println("Password is not correct.");
                                Holder();
                                break;
                            }
                        }
                    }
                    System.out.println("UsernameNotFound!");
                    Holder();
                }
                case 2 -> {
                    for (Teacher teacher : Hogwarts.getTeachers()) {
                        if (teacher.getUsername().equals(username)) {
                            if (teacher.validatePassword(password))
                                TeacherMenu(teacher);
                            else {
                                System.out.println("Password is not correct.");
                                Holder();
                                break;
                            }
                        }
                    }
                    System.out.println("UsernameNotFound!");
                    Holder();
                }
                case 3 -> {
                    for (Assistant assistant : Hogwarts.getAssistants()) {
                        if (assistant.getUsername().equals(username)) {
                            if (assistant.validatePassword(password))
                                AssistantMenu(assistant);
                            else {
                                System.out.println("Password is not correct.");
                                Holder();
                                break;
                            }
                        }
                    }
                    System.out.println("UsernameNotFound!");
                    Holder();
                }
            }
        } else if (task == 2) {
            if(IsUsernameInList(username))
            {
                System.out.println("This username has already signed up.");
                Holder();
            }
            switch (role) {
                case 1 ->
                    SignUpRequest("student", username, password);
                case 2 ->
                    SignUpRequest("teacher", username, password);
                case 3 ->
                    SignUpRequest("assistant", username, password);
            }
            System.out.println("Your signUp request is waiting for confirmation.");
            Holder();
        }
    }

    public static void StudentMenu(Student student)
    {
        int task = 0;
        while(task != 5)
        {
            ClearScreen();
            System.out.println("Welcome to Your Dashboard " + student.getUsername() + ".\n" +
                    "What do you wish to do here?\n" +
                    "1- Take course\n" +
                    "2- View your courses and scores\n" +
                    "3- Change username\n" +
                    "4- Change password\n" +
                    "5- Logout");
            task = input.nextInt();
            ClearScreen();
            switch (task) {
                case 1 ->//Taking a new course
                {
                    Hogwarts.viewAllCourses();
                    System.out.print("Enter the title: ");
                    String title = input.nextLine();
                    boolean taken = false;
                    boolean inList = false;
                    for (Course course : Hogwarts.getCourses()) {
                        if (title.equals(course.getTitle())) {
                            inList = true;
                            for (Course c : student.getCourses()) {
                                if (c.getTitle().equals(title)) {
                                    taken = true;
                                    System.out.println("This course is already taken.");
                                }
                            }
                            if (!taken) {
                                student.getCourses().add(course);
                                student.getScores().put(course.getTitle(), 0.0);
                                System.out.println("Course successfully added.");
                            }
                            break;
                        }
                    }
                    if (!inList)
                        System.out.println("CourseNotFound.");
                }
                case 2 ->//View courses taken and the score
                {
                    for (Course course : student.getCourses()) {
                        System.out.println("- " + course.getTitle() + " with "
                                + course.getTeacher().getUsername() + "\nScore: ");
                        student.getScores().get(course.getTitle());
                    }
                }
                case 3 ->//Changing username
                {
                    System.out.print("Enter the new username: ");
                    String username = input.nextLine();
                    student.changeUsername(username);
                }
                case 4 ->//Changing password
                {
                    System.out.print("Enter new password: ");
                    String newPassword = input.nextLine();
                    student.changePassword(newPassword);
                }
            }
            Holder();
            return;
        }
    }

    public static void TeacherMenu(Teacher teacher) {
        int task = 0;
        while (task != 6) {
            ClearScreen();
            System.out.println("Welcome to your dashboard, " + teacher.getUsername() + ".\n" +
                    "Your score: " + teacher.getScore() + "\n" +
                    "What do you wish to do here?\n" +
                    "1- View courses\n" +
                    "2- Take course\n" +
                    "3- Score students\n" +
                    "4- Change username\n" +
                    "5- Change password\n" +
                    "6- Logout\n");
            task = input.nextInt();
            ClearScreen();
            switch (task) {
                case 1://View all courses taken and students in each class
                {
                    for(Course course : teacher.getCoursesTaken())
                    {
                        System.out.println(course.getTitle() + "\nStudents list: \n");
                        course.ShowStudents();
                    }
                }
                case 2://Take new course
                {
                    System.out.print("Enter your desired course: ");
                    String title = input.next();
                    teacher.addCourse(title, teacher);
                }
                case 3://Score students
                {
                    for(Course course: teacher.getCoursesTaken()) {
                        System.out.println(course.getTitle());
                        course.ShowStudents();
                        System.out.print("Enter Student's name: ");
                        String name = input.nextLine();
                        boolean inlist = false;
                        for(Student student: course.getStudents())
                        {
                            if(name.equals(student.getUsername()))
                            {
                                inlist = true;
                                System.out.print("Enter the score: ");
                                double score = input.nextDouble();
                                student.getScores().put(course.getTitle(), score);
                                break;
                            }
                        }
                        if(!inlist){
                            System.out.println("Student doesn't exist.");
                            Holder();
                        }
                    }
                }
                case 4://Changing username
                {
                    System.out.print("Enter the new username: ");
                    String username = input.nextLine();
                    teacher.changeUsername(username);
                    break;
                }
                case 5://Changing password
                {
                    System.out.print("Enter new password: ");
                    String newPassword = input.nextLine();
                    teacher.changePassword(newPassword);
                    break;
                }
            }
        }
    }

    public static void AssistantMenu(Assistant assistant)
    {
        int task = 0;
        while (task != 8) {
            ClearScreen();
            System.out.println("Welcome to your dashboard, " + assistant.getUsername() + ".\n" +
                    "What do you wish to do here?\n" +
                    "1- View courses\n" +
                    "2- View Teachers\n" +
                    "3- View Students\n" +
                    "4- View Assistants\n" +
                    "5- Accept signup requests\n" +
                    "6- Change username\n" +
                    "7- Change password\n" +
                    "8- Logout\n");
            task = input.nextInt();
            ClearScreen();
            switch (task) {
                case 1://view courses
                {
                    Hogwarts.viewAllCourses();
                    break;
                }
                case 2://view teachers
                {
                    Hogwarts.viewAllTeachers();
                    break;
                }
                case 3://view students
                {
                    Hogwarts.viewAllStudents();
                    break;
                }
                case 4://view Assistants
                {
                    Hogwarts.viewAllAssistants();
                    break;
                }
                case 5://accept signup requests
                {
                    for(List<String> req : Assistant.getSignups()) {
                        ClearScreen();
                        System.out.println("User " + req.get(1) + " wants to signup for " + req.get(0) + " role here, will you accept this request?(y/n)");
                        String c = input.nextLine();
                        if(c.equals("y"))
                        {
                            System.out.println("Choose this user's house.\n-Gryffindor\n-Hufflepuff\n-Ravenclaw\n-Slytherin");
                            String house = in.nextLine();
                            switch (req.get(0)) {
                                case "assistant" -> Hogwarts.addAssistant(req.get(1), req.get(2), house);
                                case "teacher" -> Hogwarts.addTeacher(req.get(1), req.get(2), house);
                                case "student" -> Hogwarts.addStudent(req.get(1), req.get(2), house);
                            }
                        }
                    }
                    Assistant.getSignups().clear();
                }
                case 6://Changing username
                {
                    System.out.print("Enter the new username: ");
                    String username = input.nextLine();
                    assistant.changeUsername(username);
                    break;
                }
                case 7://Changing password
                {
                    System.out.print("Enter new password: ");
                    String newPassword = input.nextLine();
                    assistant.changePassword(newPassword);
                    break;
                }
            }
        }
    }

    public static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Holder()
    {
        System.out.println("Enter a key to continue.");
        input.next();
    }
}
