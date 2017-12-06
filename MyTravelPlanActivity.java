package com.example.kosta.travel;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MyTravelPlanActivity extends AppCompatActivity {

    private List<TravelPlan> travelPlans;

    private TravelPlan travelPlan;
    private TextView textTravelArea;
    private TextView textTheme;
    private TextView textPreferGuide;
    private TextView textSpeakingAbility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_travel_plan);

        travelPlans = new ArrayList<>();

        textTravelArea = (TextView)findViewById(R.id.travelArea);
        textTheme= (TextView)findViewById(R.id.theme);
        textPreferGuide = (TextView)findViewById(R.id.preferGuide);
        textSpeakingAbility = (TextView)findViewById(R.id.speakingAbility);



        //id세션에서 받아와야 한다.
        String id = "1";

        MyTravelPlanTask task = new MyTravelPlanTask();
        task.execute("http://10.0.2.2:8888/EveryYeoga/travel/mobileMyTravelPlan.do?userId="+id.toString());

    }
    private class MyTravelPlanTask extends AsyncTask<String, Void, TravelPlan>{

        @Override
        protected TravelPlan doInBackground(String... params) {
            HttpURLConnection http = null;
            InputStream is = null;
            String checkStr = null;

            try {
                URL url = new URL(params[0]);

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                Document doc = documentBuilder.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
                NodeList nodeList = doc.getElementsByTagName("travelPlan");

                for(int i=0; i<nodeList.getLength(); i++){
                    TravelPlan travelPlan = new TravelPlan();

                    Node node = nodeList.item(i);
                    Element fElement = (Element)node;
                    travelPlan = new TravelPlan();
                    travelPlan.setTravelPlanId(getTagValue("travelPlanId", fElement));
                    travelPlan.setTheme(getTagValue("theme", fElement));
                    travelPlan.setTravelArea(getTagValue("travelArea", fElement));
                    travelPlan.setPreferGuide(getTagValue("preferGuide", fElement));
                    travelPlans.add(travelPlan);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return travelPlan;
        }

        @Override
        protected void onPostExecute(TravelPlan travelPlan) {

            textPreferGuide.setText(travelPlans.get(0).getPreferGuide());
            textTheme.setText(travelPlans.get(0).getTheme());
            textTravelArea.setText(travelPlans.get(0).getTravelArea());

        }
    }

    private static String getTagValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node)nodeList.item(0);

        return nValue.getNodeValue();
    }

}
