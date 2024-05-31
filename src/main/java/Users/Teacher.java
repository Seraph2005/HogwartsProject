package Users;

import Course.Course;
import Hogwarts.Hogwarts;

import java.util.*;

public class Teacher extends Account{
    private final List<Course> courses = new ArrayList<>();
    private final Map<String, Double> scores = new HashMap<>();
    private int score;

    public Teacher(String username, String password, String house)
    {
        super(username, password, house);
    }

    //Getters and setters
    public List<Course> getCoursesTaken()
    {
        return courses;
    }
    public int getScore()
    {
        return score;
    }

    public void addScore(int score)
    {
        this.score = (this.score + score)/2;
    }

    public void addCourse(String title, Teacher teacher) {
        Course course = new Course(title, teacher);
        courses.add(course);
        Hogwarts.getCourses().add(course);
    }

}
