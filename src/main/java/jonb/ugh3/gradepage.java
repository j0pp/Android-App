package jonb.ugh3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class gradepage extends AppCompatActivity {
    TextView gradeText, gradeValText, teacherText, classText;
    ArrayList<Course> coursesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradepage);

        SourcePage sp = SourcePage.getInstance();
        coursesList = sp.getCoursesList();

        gradeText = (TextView) (findViewById(R.id.gradeTextView));
        gradeValText = (TextView) (findViewById(R.id.gradeValTextView));
        teacherText = (TextView) (findViewById(R.id.teacherTextView));
        classText = (TextView) (findViewById(R.id.classTextView));

        setTitle(sp.getNAME());

        for(int i = 0; i < 1; i++) {
            Course c = new Course("Mr. MemeMan McGee", "AP DANK MEMES", 82.5, "B-");

            int color = c.getGradeColor();

            gradeText.setText(c.getGRADE());
            gradeText.setTextColor(color);

            gradeValText.setText(c.getGRADEVAL() + "%");
            gradeValText.setTextColor(color);

            teacherText.setText(c.getTEACHER());
            classText.setText(c.getCOURSE());
        }





    }

}
