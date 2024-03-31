package Users;

import Course.Course;
import Hogwarts.Hogwarts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

public class Student extends Account{
    private List<Course> coursesTaken = new ArrayList<>();
    private Map<String, Double> scores = new HashMap<>();
    private String house;

    //Getters
    public List<Course> getCoursesTaken()
    {
        return coursesTaken;
    }
    public List<Double> getScores()
    {
        return this.scores;
    }
    public String getHouse()
    {
        return this.house;
    }

    public void showScore(String title)
    {
        System.out.println(scores.get(title));
    }

}
