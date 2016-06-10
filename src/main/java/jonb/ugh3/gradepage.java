package jonb.ugh3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class gradepage extends AppCompatActivity {
    TextView gradeText, gradeValText, teacherText, classText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradepage);

        SourcePage sp = SourcePage.getInstance();

        gradeText = (TextView) (findViewById(R.id.gradeTextView));
        gradeValText = (TextView) (findViewById(R.id.gradeValTextView));
        teacherText = (TextView) (findViewById(R.id.teacherTextView));
        classText = (TextView) (findViewById(R.id.classTextView));

        setTitle(sp.getNAME());

        gradeText.setText(sp.getGradeLetters().get(0));
        gradeValText.setText(sp.getGradeNumber().get(0).toString() + "%");
        teacherText.setText(sp.getTeacherList().get(0));
        classText.setText(sp.getClassList().get(0));



    }
}
