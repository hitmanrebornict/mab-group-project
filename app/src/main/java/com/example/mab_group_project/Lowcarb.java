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

public class Lowcarb extends AppCompatActivity{
    ExpandableListViewAdapter foodinfotlistviewadapter;
    ExpandableListView foodintolistview;
    List<String> foodinfolist1;
    HashMap<String,List<String>> foodinfolist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paleo);
        getSupportActionBar().setTitle("Low Carbs Diet");
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
        imageView.setImageResource(R.drawable.int3);
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
        title.setText("Turkey and Lettuce Wrap [4 Servings] - 10 Min prep, 5 Min cook");
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
        description.setText("Fast and Easy protein wrap, a fabulous any-time snack..");
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

        ImageView ing1 = new ImageView(this);
        ing1.setImageResource(R.drawable.lettuce);
        ing1.setPadding(16,16,16,16);
        row1.addView(ing1);
        ImageView ing2 = new ImageView(this);
        ing2.setImageResource(R.drawable.ham);
        ing2.setPadding(16,16,16,16);
        row2.addView(ing2);

        TextView ingredient1 = new TextView(this);
        ingredient1.setText("Lettuce");
        ingredient1.setTypeface(Typeface.SERIF);
        ingredient1.setTextSize(12);
        ingredient1.setTextColor(Color.BLACK);
        ingredient1.setLayoutParams(rowparams);
        ingredient1.setGravity(Gravity.CENTER);
        row1.addView(ingredient1);

        TextView ingredient2 = new TextView(this);
        ingredient2.setText("Turkey breast sliced or deli meat");
        ingredient2.setTypeface(Typeface.SERIF);
        ingredient2.setTextSize(12);
        ingredient2.setTextColor(Color.BLACK);
        ingredient2.setLayoutParams(rowparams);
        ingredient2.setGravity(Gravity.CENTER);
        row2.addView(ingredient2);

        TextView cal1 = new TextView(this);
        cal1.setText("15 Cals");
        cal1.setTypeface(Typeface.SERIF);
        cal1.setTextSize(12);
        cal1.setTextColor(Color.BLACK);
        cal1.setLayoutParams(rowparams);
        cal1.setGravity(Gravity.CENTER);
        row1.addView(cal1);

        TextView cal2 = new TextView(this);
        cal2.setText("26 Cals");
        cal2.setTypeface(Typeface.SERIF);
        cal2.setTextSize(12);
        cal2.setTextColor(Color.BLACK);
        cal2.setLayoutParams(rowparams);
        cal2.setGravity(Gravity.CENTER);
        row2.addView(cal2);

        LinearLayout.LayoutParams totalcalparam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        TextView Totalcal = new TextView(this);
        Totalcal.setText("Total: 324 Cals");
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
        m1n1.add("Fat 22g 28% of Cals");
        m1n1.add("Protein 21g 41% of Cals");
        m1n1.add("Carbs 3g 1% of Cals");

        List<String> m1n3 = new ArrayList<>();
        m1n3.add("1. Stir mayonnaise, pickle and mustard together in a small bowl.");
        m1n3.add("2. Overlap 2 lettuce leaves on a clean cutting board. Spread a generous 1 tablespoon of the mayonnaise mixture over the lettuce.");
        m1n3.add("3. Top with 3 ounces turkey  and 2 tomato slices");
        m1n3.add("4. Roll into a wrap, then cut in half.");

        List<String> m1n2 = new ArrayList<>();
        m1n2.add("8 large green-leaf lettuce leaves");
        m1n2.add("12 ounces sliced deli turkey");
        m1n2.add("8 slices tomato");
        m1n2.add("2 teaspoons whole-grain mustard");
        m1n2.add("3 tablespoons chopped dill pickle");
        m1n2.add("1/4 cup mayonnaise");

        foodinfolist2.put(foodinfolist1.get(0),m1n1);
        foodinfolist2.put(foodinfolist1.get(1),m1n2);
        foodinfolist2.put(foodinfolist1.get(2),m1n3);
    }
}
