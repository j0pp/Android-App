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

        Intent intent = getIntent();
        SourcePage sp = (SourcePage) intent.getSerializableExtra("sp");

        gradeText = (TextView) (findViewById(R.id.gradeTextView));
        gradeValText = (TextView) (findViewById(R.id.gradeValTextView));
        teacherText = (TextView) (findViewById(R.id.teacherTextView));
        classText = (TextView) (findViewById(R.id.classTextView));

        gradeText.setText(sp.getGradeLetters().get(0));
        gradeValText.setText(sp.getGradeNumber().get(0) + "");



    }
}
