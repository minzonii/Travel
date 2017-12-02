package com.example.kosta.travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kosta on 2017-11-30.
 */

public class TravelPlanAdapter extends BaseAdapter{

    private Context context;
    private List<TravelPlan> travelPlans;
    private LayoutInflater inflater;

    public TravelPlanAdapter(Context context, List<TravelPlan> travelPlans){
        this.context = context;
        this.travelPlans = travelPlans;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return travelPlans.size();
    }

    @Override
    public Object getItem(int i) {
        return travelPlans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        TextView travelerId = (TextView) convertView.findViewById(R.id.travelerId);
        TextView travelArea = (TextView) convertView.findViewById(R.id.travelArea);
        TextView theme= (TextView) convertView.findViewById(R.id.theme);
        TextView preferGuide = (TextView) convertView.findViewById(R.id.preferGuide);

        travelerId.setText(travelPlans.get(i).getTravelPlanId ());
        travelArea.setText(travelPlans.get(i).getTravelArea());
        theme.setText(travelPlans.get(i).getTheme());
        preferGuide.setText(travelPlans.get(i).getPreferGuide());


        return convertView;
    }
}
