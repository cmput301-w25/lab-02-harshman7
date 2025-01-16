package com.example.listycity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int ind, long l) {
                index = ind;
            }
        });

        EditText newCity = findViewById(R.id.new_city_text);
        Button addCity = findViewById(R.id.add_city);
        Button delCity = findViewById(R.id.delete_city);
        Button confirm = findViewById(R.id.button4);

        addCity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Make text box and button visible
                newCity.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataList.add(newCity.getText().toString());
                cityAdapter.notifyDataSetChanged();
                newCity.setVisibility(View.INVISIBLE);
                confirm.setVisibility(View.INVISIBLE);
            }
        });

        delCity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(index != -1) {
                    dataList.remove(index);
                    cityAdapter.notifyDataSetChanged();
                    index = -1;
                }
            }
        });
    }
}