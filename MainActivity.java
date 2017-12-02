package com.example.kosta.travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button my_travel_plan_btn;
    private Button create_travel_plan_btn;
    private Button travel_plan_list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_travel_plan_btn = (Button)findViewById(R.id.my_travel_plan_btn);
        my_travel_plan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyTravelPlanActivity.class);
                startActivity(intent);

            }
        });

        create_travel_plan_btn = (Button)findViewById(R.id.create_travel_plan_btn);
        create_travel_plan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateTravelPlanActivity.class);
                startActivity(intent);
            }
        });

        travel_plan_list_btn = (Button)findViewById(R.id.travel_plan_list_btn);
        travel_plan_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TravelPlanListActivity.class);
                startActivity(intent);
            }
        });


    }
}
