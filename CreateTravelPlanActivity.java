package com.example.kosta.travel;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CreateTravelPlanActivity extends AppCompatActivity {

    private Button btn;
    private EditText speakingAbility;
    private EditText travelArea;
    private EditText numberOfVisits;
    private EditText theme;
    private EditText numberOfTraveler;
    private EditText preferGuide;
    private EditText selfIntroduction;
    private EditText startDate;
    private EditText endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_travel_plan);

        speakingAbility = (EditText)findViewById(R.id.speakingAbility);
        travelArea= (EditText)findViewById(R.id.travelArea);
        numberOfVisits= (EditText)findViewById(R.id.numberOfVisits);
        theme= (EditText)findViewById(R.id.theme);
        numberOfTraveler= (EditText)findViewById(R.id.numberOfTraveler);
        preferGuide= (EditText)findViewById(R.id.preferGuide);
        selfIntroduction= (EditText)findViewById(R.id.selfIntroduction);
        startDate= (EditText)findViewById(R.id.startDate);
        endDate= (EditText)findViewById(R.id.endDate);


//create travelPlan
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TravelPlan travelPlan = new TravelPlan();
                travelPlan.setSpeakingAbility(speakingAbility.getText().toString());
                travelPlan.setTravelArea(travelArea.getText().toString());
                travelPlan.setNumberOfVisits(numberOfVisits.getText().toString());
                travelPlan.setTheme(theme.getText().toString());
                travelPlan.setNumberOfTraveler(numberOfTraveler.getText().toString());
                travelPlan.setPreferGuide(preferGuide.getText().toString());
                travelPlan.setSelfIntroduction(selfIntroduction.getText().toString());
                travelPlan.setStartDate(startDate.getText().toString());
                travelPlan.setEndDate(endDate.getText().toString());

                CreateTravelPlanTask task = new CreateTravelPlanTask();
                task.execute("http://10.0.2.2:8888/EveryYeoga/travel/mobileCreateTravelPlan.do?" +
                        "speakingAbility="+travelPlan.getSpeakingAbility()
                        + "&travelArea="+travelPlan.getTravelArea()
                        + "&numberOfVisits="+travelPlan.getNumberOfVisits()
                        + "&theme="+travelPlan.getTheme()
                        + "&numberOfTraveler="+travelPlan.getNumberOfTraveler()
                        + "&preferGuide="+travelPlan.getPreferGuide()
                        + "&selfIntroduction="+travelPlan.getSelfIntroduction()
                        + "&startDate="+travelPlan.getStartDate()
                        + "&endDate="+travelPlan.getEndDate()
                );

            }
        });
    }


    private class CreateTravelPlanTask extends AsyncTask<String, Object, Boolean> {

        protected Boolean doInBackground(String... params) {
            HttpURLConnection http = null;
            InputStream is = null;
            String checkStr = null;

            try {

                URL url = new URL(params[0]);

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                Document doc = documentBuilder.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean check) {

            if(check){

            }else{

            }

        }
    }

}
