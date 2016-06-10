package jonb.ugh3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    Button login;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        login = (Button) (findViewById(R.id.btnLogin));
        user = (EditText) (findViewById(R.id.usernameText));
        pass = (EditText) (findViewById(R.id.passText));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SourcePage sp = SourcePage.getInstance();
                sp.login(user.getText().toString(), pass.getText().toString());

                Thread th = new Thread() {
                    @Override
                    public void run() {

                        while(!sp.pageLoaded) {

                        }

                        if(sp.pageLoaded) {
                            if(sp.isConnected()) {
                                Toast.makeText(getApplicationContext(), sp.getGradeLetters().get(0), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, gradepage.class));
                            }else {
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                };

                th.run();

            }
        });


    }


}
