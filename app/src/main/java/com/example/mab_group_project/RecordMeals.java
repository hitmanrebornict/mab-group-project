package com.example.myapplication;

import android.app.AlertDialog;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordMeals extends AppCompatActivity {

    //basic functions:
    //add what have eaten for each meal (food data central api, store into database)
    //user search meal + select meal type
    //returns meal result & nutrients from api database
    //search food -> return list of foods -> user selects -> (another activity) displays food nutrients -> user click ok -> add new row

    ListView lvFoodResults;
    EditText etSearchBar;
    DeviceListAdapter adapter;
    Button btnSearchQuery;
    Handler handler;
    FoodDataCentralApi api;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_meals);

        lvFoodResults = findViewById(R.id.lvFoodSearchResults);
        etSearchBar = findViewById(R.id.etSearchFood);
        btnSearchQuery = findViewById(R.id.btnSubmitQuery);
        api = new FoodDataCentralApi();

        //setting up list view
        adapter = new DeviceListAdapter(this);
        lvFoodResults.setAdapter(adapter);
        lvFoodResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food f = adapter.getFood(i);
                long fdcid = f.getFdcId();
                Intent intent = new Intent(RecordMeals.this, ShowNutrientsForRecord.class);
                intent.putExtra("foodfdcid", fdcid);
                RecordMeals.this.startActivity(intent);
            }
        });

        //setting up search bar
        etSearchBar.addTextChangedListener(new TextValidator(etSearchBar));

        //setting up submit button
        btnSearchQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = etSearchBar.getText().toString();
                searchFoods(query);
            }
        });

    }

    private void searchFoods(String query) {
        new Thread(() -> {
            try {
                List<Food> foods = api.searchFoods(query);
                for (Food food : foods) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addFood(food);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //setting up text watcher for search bar
    //after text changed, will connect to API for search query, then returns result list of food desc and food fdcid
    public class TextValidator implements TextWatcher {

        private final EditText et;

        public TextValidator(EditText e) {
            this.et = e;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { return;}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { return; }

        @Override
        public void afterTextChanged(Editable s) {
            String text = et.getText().toString();
            Log.i("Input text: ", text);
            if (text.equals("")) {
                adapter.clearList();
                adapter.notifyDataSetChanged();
            }
        }
    }


    //setting up list adapter
    public static class DeviceListAdapter extends BaseAdapter {
        private Context c;
        private List<Food> foods;

        public DeviceListAdapter(Context c) {
            super();
            this.c = c;
            foods = new ArrayList<>();
        }

        //adds a new food into the list adapter
        public void addFood(Food f) {
            if(!foods.contains(f)) {
                foods.add(f);
                Log.i("Food added", "food added to list view");
            }

            else Log.i("Food Not Added", "Food not added to list view");
        }

        public Food getFood(int pos) {
            return foods.get(pos);
        }

        //clear the list
        public void clearList() {
            foods.clear();
        }

        @Override
        public int getCount() {
            return foods.size();
        }

        @Override
        public Object getItem(int i) {
            return foods.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(c);
            View view = inflater.inflate(R.layout.activity_listview_recordnutrients, viewGroup, false);

            TextView tvFoodDesc = view.findViewById(R.id.tvFoodDesc);
            TextView tvFoodfdcid = view.findViewById(R.id.tvFoodfdcid);

            tvFoodDesc.setText("Food Description: "+ foods.get(pos).getDescription());
            tvFoodfdcid.setText("Food FDCID: " + Long.toString(foods.get(pos).getFdcId()));

            return view;
        }
    }
}
