package jonb.ugh3;

import android.graphics.Color;


/**
 * Created by Jin on 6/9/2016.
 */
public class Course {

    private String TEACHER;
    private String NAME;
    private Double GRADEVAL;
    private String GRADE;

    public Course(String teacher, String name, double gradeVal, String grade) {
        this.TEACHER = teacher;
        this.NAME = name;
        this.GRADEVAL = gradeVal;
        this.GRADE = grade;

    }

    public int getGradeColor() {

        if(GRADEVAL >= 90) {
            return Color.rgb(46, 203, 114);
        }else if(GRADEVAL >= 80) {
            return Color.rgb(74, 163, 223);
        }else if(GRADEVAL >= 70) {
            return Color.rgb(241, 196, 15);
        }else if(GRADEVAL >= 60) {
            return Color.rgb(243, 156, 18);
        }else {
            return Color.rgb(192, 57, 43);
        }
    }

    public String getTEACHER() {
        return TEACHER;
    }

    public String getGRADE() {
        return GRADE;
    }

    public String getCOURSE() {
        return NAME;
    }

    public Double getGRADEVAL() {
        return GRADEVAL;
    }
}
