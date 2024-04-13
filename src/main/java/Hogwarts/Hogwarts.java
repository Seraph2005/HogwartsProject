package Hogwarts;

import Course.Course;
import Users.Account;
import Users.Assistant;
import Users.Student;
import Users.Teacher;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hogwarts {
    private static List<Account> users = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Assistant> assistants = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    // Getters ans Setters
    public static List<Account> getUsers() {
        return users;
    }
    public static List<Student> getStudents() {
        return students;
    }
    public static List<Teacher> getTeachers(){
        return teachers;
    }
    public static List<Assistant> getAssistants() {
        return assistants;
    }
    public static List<Course> getCoures()
    {
        return courses;
    }

    // Functionalities
    public static void viewAllStudents() {
        System.out.println("> Here's list of all students:");
        for (Student s : students) {
            System.out.println("- " + s.getUsername() + " (" + s.getHouse() + ")");
        }
    }

    public static void viewAllTeachers() {
        System.out.println("> Here's list of all teachers:");
        for (Teacher t : teachers) {
            System.out.println("- " + t.getUsername());
        }
    }

    public static void viewAllCourses() {
        for(Course c : courses)
            System.out.println("- " + c.getTitle());
    }

    public static void addTeacher(String username, String password, String house) {
        Teacher teacher = new Teacher();
        teacher.setUsername(username);
        teacher.setPassword(password);
        teacher.setHouse(house);
    }

    public static void addStudent(String username, String password, String house) {
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.setHouse(house);
    }

    public static void addAssistant(String username, String password, String house) {
        Assistant assistant = new Assistant();
        assistant.setUsername(username);
        assistant.setPassword(password);
        assistant.setHouse(house);
    }

    public static void addCourse(String title) {
        Course course = new Course(title);
        courses.add(course);
    }

}
