package Course;

import Users.Teacher;
import Users.Student;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final List<Student> students = new ArrayList<>();
    private final String title;
    private final Teacher teacher;

    public Course(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
    }

    //Getters and Setters
    public List<Student> getStudents() {
        return students;
    }
    public String getTitle()
    {
        return title;
    }
    public Teacher getTeacher()
    {
        return teacher;
    }

    //Functionalities
    public void ShowStudents()
    {
        for(Student student : students) {
            System.out.println("- " + student.getUsername() + ", " + student.getHouse());
        }
    }





}
