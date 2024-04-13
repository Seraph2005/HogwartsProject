package Users;

import Course.Course;
import Hogwarts.Hogwarts;

import java.util.*;

public class Teacher extends Account{
    private List<Course> coursesTaken = new ArrayList<>();
    private Map<String, Double> scores = new HashMap<>();
    private String house;
    private int score;

    //Getters and setters
    public List<Course> getCoursesTaken()
    {
        return coursesTaken;
    }

    public void addScore(int score)
    {
        this.score = (this.score + score)/2;
    }

}
