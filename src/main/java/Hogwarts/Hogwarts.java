package Hogwarts;

import Course.Course;
import Users.Account;
import Users.Assistant;
import Users.Student;
import Users.Teacher;

import java.util.ArrayList;
import java.util.List;

public class   Hogwarts {
    private static final List<Account> users = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Assistant> assistants = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();

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
    public static List<Course> getCourses()
    {
        return courses;
    }

    // Functionalities
    public static void viewAllStudents() {
        for (Student s : students) {
            System.out.println("- " + s.getUsername() + " (" + s.getHouse() + ")");
        }
    }

    public static void viewAllTeachers() {
        for (Teacher t : teachers) {
            System.out.println("- " + t.getUsername());
        }
    }

    public static void viewAllAssistants() {
        for (Assistant a : assistants) {
            System.out.println("- " + a.getUsername() + " (" + a.getHouse() + ")");
        }
    }

    public static void viewAllCourses() {
        for(Course c : courses)
            System.out.println("- " + c.getTitle());
    }

    public static void addTeacher(String username, String password, String house) {
        Teacher teacher = new Teacher(username, password, house);
        getUsers().add(teacher);
        getTeachers().add(teacher);
    }

    public static void addStudent(String username, String password, String house) {
        Student student = new Student(username, password, house);
        getUsers().add(student);
        getStudents().add(student);
    }

    public static void addAssistant(String username, String password, String house) {
        Assistant assistant = new Assistant(username, password, house);
        getUsers().add(assistant);
        getAssistants().add(assistant);
    }

}
