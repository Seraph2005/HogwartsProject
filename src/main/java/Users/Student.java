package Users;

import Course.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Student extends Account{
    private final List<Course> courses = new ArrayList<>();
    Map<String, Double> scores = new HashMap<>();

    public Student(String username, String password, String house)
    {
        super(username, password, house);
    }

    //Getters
    public List<Course> getCourses()
    {
        return courses;
    }
    public Map<String, Double> getScores()
    {
        return scores;
    }

}
