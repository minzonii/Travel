package com.example.kosta.travel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TravelPlanListActivity extends AppCompatActivity {

    private List<TravelPlan> travelPlans;
    private TravelPlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_plan_list);

        ListView list = (ListView)findViewById(R.id.list);

        AllTravelPlansLoadingTask task = new AllTravelPlansLoadingTask();
        task.execute("http://10.0.2.2:8080/EveryYeoga/travel/mobileTravelPlanList.do");

        travelPlans = new ArrayList<>();
        adapter = new TravelPlanAdapter(this, travelPlans);

        list.setAdapter(adapter);

    }
    private class AllTravelPlansLoadingTask extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            try {
                URL url = new URL((String)params[0]);

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                Document doc = documentBuilder.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("travelPlan");
                for(int i=0; i<nodeList.getLength(); i++){
                    TravelPlan travelPlan = new TravelPlan();

                    Node node = nodeList.item(i);
                    Element fElement = (Element)node;

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
            return null;
        }
    }

    private static String getTagValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node)nodeList.item(0);

        return nValue.getNodeValue();
    }


}
