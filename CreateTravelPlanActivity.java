package com.example.kosta.travel;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

               // CreateTravelPlanTask


            }
        });
    }


    private class CreateTravelPlanTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection http = null;
            InputStream is = null;
            String checkStr = null;

            try {
                URL url = new URL(strings[0]);
                http = (HttpURLConnection)url.openConnection();
                http.setRequestMethod("GET");
                http.connect();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
