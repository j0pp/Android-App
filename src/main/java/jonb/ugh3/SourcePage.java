package jonb.ugh3;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jin on 6/5/2016.
 */
public class SourcePage implements Serializable{

    private String NAME;

    private Document mainDoc;

    public static final String URL = "https://ps.seattleschools.org/public/";
    public static final String POST_URL = "https://ps.seattleschools.org/guardian/home.html";

    public static final String userAgent = "\"Mozilla/5.0 (Windows NT\" +\n" +
            "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";

    private static ArrayList<Double> gradeNumber = new ArrayList<>();
    private static ArrayList<String> gradeLetter = new ArrayList<>();

    private static ArrayList<String> teacherList = new ArrayList<>();
    private static ArrayList<String> classList = new ArrayList<>();

    private static ArrayList<Course> coursesList = new ArrayList<>();

    public static boolean validPage;
    public static boolean pageLoaded;

    private static SourcePage sourcePage = new SourcePage();

    private SourcePage() {}

    public static SourcePage getInstance() {
        return sourcePage;
    }

    protected void login(String USERNAME, String PASSWORD) {

        final String user = USERNAME;
        final String pass = PASSWORD;

        validPage = false;
        pageLoaded = false;

        Thread th = new Thread() {

            public void run() {

                try {

                    HashMap<String, String> cookies = new HashMap<>();
                    HashMap<String, String> formData = new HashMap<>();


                    Connection.Response loginForm = Jsoup.connect(URL)
                            .method(Connection.Method.GET)
                            .userAgent(userAgent)
                            .execute();

                    Document loginDoc = loginForm.parse();

                    String pstoken = loginDoc.select("#LoginForm > input[type=\"hidden\"]:nth-child(1)").first().attr("value");
                    String contextData = loginDoc.select("#contextData").first().attr("value");
                    String dbpw = loginDoc.select("#LoginForm > input[type=\"hidden\"]:nth-child(3)").first().attr("value");
                    String serviceName = "PS Parent Portal";
                    String credentialType = "User Id and Password Credential";


                    cookies.putAll(loginForm.cookies());


                    //Inserting all hidden form data things
                    formData.put("pstoken", pstoken);
                    formData.put("contextData", contextData);
                    formData.put("dbpw", dbpw);
                    formData.put("serviceName", serviceName);
                    formData.put("credentialType", credentialType);
                    formData.put("Account", user);
                    formData.put("ldappassword", pass);
                    formData.put("pw", pass);


                    Connection.Response homePage = Jsoup.connect(POST_URL)
                            .cookies(cookies)
                            .data(formData)
                            .method(Connection.Method.POST)
                            .userAgent(userAgent)
                            .execute();


                    mainDoc = Jsoup.parse(homePage.parse().html());

                    validPage = mainDoc.select("div.feedback-alert").isEmpty();


                    if(validPage) {
                        NAME = mainDoc.select("div#sps-stdemo-non-conf").select("h1").first().text();

                        //Getting Grades for Semester 2
                        Elements grades = mainDoc.select("td.colorMyGrade").select("[href*='fg=S2']");
                        System.out.println(grades);
                        for (Element j : grades)
                        {

                            if (!j.text().equals("--")) {
                                String gradeText = j.text();
                                gradeLetter.add(gradeText.substring(0, gradeText.indexOf(" ")));
                                gradeNumber.add(Double.parseDouble(gradeText.substring(gradeText.indexOf(" ") + 1)));
                            }
                        }

                        Elements teachers = mainDoc.select("td[align='left']");
                        for (int i = 1; i < teachers.size(); i += 2)
                        {

                            String[] fullText = teachers.get(i).text().split("\\s{3,}+");
                            classList.add(fullText[0]);
                            teacherList.add(fullText[1]);


                        }
                    }
                    //Get persons name
                    for(int i = 0; i < classList.size(); i++) {
                        Course c = new Course(teacherList.get(i), classList.get(i), gradeNumber.get(i), gradeLetter.get(i));
                        coursesList.add(c);
                    }

                    pageLoaded = true;


                }catch (IOException e) {
                    System.out.println(e);
                }

            }
        };

        th.start();


    }

    public boolean isConnected() {
        return validPage;
    }



    public ArrayList<String> getGradeLetters() {
        return gradeLetter;
    }

    public ArrayList<Double> getGradeNumber() {
        return gradeNumber;
    }

    public ArrayList<String> getTeacherList() {
        return teacherList;
    }

    public static ArrayList<String> getClassList() {
        return classList;
    }

    public String getNAME() {
        return NAME;
    }

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }
}
