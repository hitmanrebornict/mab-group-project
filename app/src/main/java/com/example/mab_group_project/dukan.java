package com.example.mab_group_project;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class dukan extends AppCompatActivity {
    ExpandableListViewAdapter foodinfotlistviewadapter;
    ExpandableListView foodintolistview;
    List<String> foodinfolist1;
    HashMap<String,List<String>> foodinfolist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paleo);
        getSupportActionBar().setTitle("Atkin Diet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        // Create a parent LinearLayout to hold both the image and the ScrollView
        LinearLayout parent = new LinearLayout(this);
        parent.setOrientation(LinearLayout.VERTICAL);

        // Create the LinearLayout with the image
        LinearLayout layout1 = new LinearLayout(this);
        layout1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                displayMetrics.heightPixels / 3
        ));
        parent.addView(layout1);

        // Add the image to the LinearLayout
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.int4);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        layout1.addView(imageView);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        //hold all the text
        LinearLayout Secondpart = new LinearLayout(this);
        Secondpart.setOrientation(LinearLayout.VERTICAL);
        Secondpart.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        Secondpart.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));


        // Create a ScrollView with a TextView containing a long string of text
        TextView title = new TextView(this);
        title.setText("Dukan Diet Chocolate Muffins [5 Servings] - 5 Min prep, 30 Min cook");
        title.setTextSize(20);
        title.setTextColor(Color.BLACK);
        title.setTypeface(Typeface.SERIF,Typeface.BOLD);
        title.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        title.setPadding(16, 16, 16, 16);
        Secondpart.addView(title);

        //description
        TextView description = new TextView(this);
        description.setText("Super low-cal muffins with an added bonus of protein!");
        description.setTextSize(12);
        description.setTypeface(Typeface.SERIF);
        description.setTextColor(Color.BLACK);
        description.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        description.setPadding(16, 16, 16, 16);
        Secondpart.addView(description);

        TableRow.LayoutParams rowparams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        rowparams.gravity = Gravity.CENTER_VERTICAL;

        //preptime and calories
        TableLayout table1 = new TableLayout(this);
        LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        tableParams.gravity = Gravity.CENTER_HORIZONTAL;
        table1.setStretchAllColumns(true);
        table1.setLayoutParams(tableParams);

        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.getPaint().setColor(Color.DKGRAY);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable.getPaint().setStrokeWidth(2);
        table1.setBackground(shapeDrawable);

        TableRow row1 = new TableRow(this);
        TableRow row2 = new TableRow(this);
        TableRow row3 = new TableRow(this);

        ImageView ing1 = new ImageView(this);
        ing1.setImageResource(R.drawable.chocolate);
        ing1.setPadding(16,16,16,16);
        row1.addView(ing1);
        ImageView ing2 = new ImageView(this);
        ing2.setImageResource(R.drawable.milk);
        ing2.setPadding(16,16,16,16);
        row2.addView(ing2);
        ImageView ing3 = new ImageView(this);
        ing3.setImageResource(R.drawable.egg);
        ing3.setPadding(16,16,16,16);
        row3.addView(ing3);


        TextView ingredient1 = new TextView(this);
        ingredient1.setText("Chocolate");
        ingredient1.setTypeface(Typeface.SERIF);
        ingredient1.setTextSize(12);
        ingredient1.setTextColor(Color.BLACK);
        ingredient1.setLayoutParams(rowparams);
        ingredient1.setGravity(Gravity.CENTER);
        row1.addView(ingredient1);

        TextView ingredient2 = new TextView(this);
        ingredient2.setText("Non-fat Milk");
        ingredient2.setTypeface(Typeface.SERIF);
        ingredient2.setTextSize(12);
        ingredient2.setTextColor(Color.BLACK);
        ingredient2.setLayoutParams(rowparams);
        ingredient2.setGravity(Gravity.CENTER);
        row2.addView(ingredient2);

        TextView ingredient3 = new TextView(this);
        ingredient3.setText("Eggs");
        ingredient3.setTypeface(Typeface.SERIF);
        ingredient3.setTextSize(12);
        ingredient3.setTextColor(Color.BLACK);
        ingredient3.setLayoutParams(rowparams);
        ingredient3.setGravity(Gravity.CENTER);
        row3.addView(ingredient3);

        TextView cal1 = new TextView(this);
        cal1.setText("10 Cals");
        cal1.setTypeface(Typeface.SERIF);
        cal1.setTextSize(12);
        cal1.setTextColor(Color.BLACK);
        cal1.setLayoutParams(rowparams);
        cal1.setGravity(Gravity.CENTER);
        row1.addView(cal1);

        TextView cal2 = new TextView(this);
        cal2.setText("90 Cals");
        cal2.setTypeface(Typeface.SERIF);
        cal2.setTextSize(12);
        cal2.setTextColor(Color.BLACK);
        cal2.setLayoutParams(rowparams);
        cal2.setGravity(Gravity.CENTER);
        row2.addView(cal2);

        TextView cal3 = new TextView(this);
        cal3.setText("155 Cals");
        cal3.setTypeface(Typeface.SERIF);
        cal3.setTextSize(12);
        cal3.setTextColor(Color.BLACK);
        cal3.setLayoutParams(rowparams);
        cal3.setGravity(Gravity.CENTER);
        row3.addView(cal3);

        LinearLayout.LayoutParams totalcalparam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        TextView Totalcal = new TextView(this);
        Totalcal.setText("Total: 69.4 Cals");
        Totalcal.setTextSize(16);
        Totalcal.setTypeface(Typeface.SERIF,Typeface.BOLD);
        Totalcal.setTextColor(Color.BLACK);
        Totalcal.setLayoutParams(rowparams);
        Totalcal.setGravity(Gravity.RIGHT);
        Totalcal.setLayoutParams(totalcalparam);

        table1.addView(row1);
        table1.addView(row2);
        Secondpart.addView(table1);

        Secondpart.addView(Totalcal);

        //nutrition value
        foodintolistview = new ExpandableListView(this);
        showList1();
        foodinfotlistviewadapter = new ExpandableListViewAdapter(this,foodinfolist1,foodinfolist2);
        foodintolistview.setAdapter(foodinfotlistviewadapter);
        foodintolistview.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        scrollView.addView(Secondpart);

        parent.addView(scrollView);
        parent.addView(foodintolistview);

        setContentView(parent);
    }
    private void showList1(){
        foodinfolist1 = new ArrayList<>();
        foodinfolist2 = new HashMap<String, List<String>>();

        foodinfolist1.add("Nutrition Value");
        foodinfolist1.add("Ingredient Needed");
        foodinfolist1.add("Recipe");

        List<String> m1n1 = new ArrayList<>();
        m1n1.add("Fat 2.3g 3.3% of Cals");
        m1n1.add("Protein 7.5g 10.8% of Cals");
        m1n1.add("Carbs 11.9g 17.1% of Cals");

        List<String> m1n3 = new ArrayList<>();
        m1n3.add("1. Combine all the ingredients in a mixing bowl and blend until there are no lumps.");
        m1n3.add("2. Pour batter into muffin tins.");
        m1n3.add("3. Bake at 350 for 20-30 minutes.");
        m1n3.add("4. Check the middle with a toothpick or fork to ensure they are done!");

        List<String> m1n2 = new ArrayList<>();
        m1n2.add("1 tbsp baking powder");
        m1n2.add("1/4 cup cocoa powder");
        m1n2.add("1 egg");
        m1n2.add("2 tbsp oat bran");
        m1n2.add("1/2 cup nonfat milk");
        m1n2.add("1 scoop chocolate whey protein powder");

        foodinfolist2.put(foodinfolist1.get(0),m1n1);
        foodinfolist2.put(foodinfolist1.get(1),m1n2);
        foodinfolist2.put(foodinfolist1.get(2),m1n3);
    }
}
