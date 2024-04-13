package Course;

import Hogwarts.Hogwarts;
import Users.Assistant;
import Users.Teacher;
import Users.Student;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

public class Course {
    private List<Student> students = new ArrayList<>();
    private String title;
    private UUID courseID;
    private Teacher teacher;

    public Course(String title) {
        this.title = title;
        this.courseID = UUID.randomUUID();
    }

    //Getters and Setters
    public void setTeacher(String teacher)
    {
        for(Teacher t : Hogwarts.getTeachers())
        {
            if(t.getUsername().equals(teacher)){
                this.teacher = t;
                break;
            }
        }
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
        for(Student student : students)
        {
            System.out.println("- " + student.getUsername() + ", " + student.getHouse());
        }
    }





}
