package com.example.mab_group_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.CollationElementIterator;
import java.util.ArrayList;


public class PremiumRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollView scrl = new ScrollView(this);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER_HORIZONTAL);

        //suggestions of different diet - columns, each column have 3 menus
        //when users click on the menu, the recipe and the nutrients value will appear

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(16, 16, 16, 16);

        ImageView diet1 = new ImageView(this);
        diet1.setLayoutParams(layoutParams);
        diet1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        diet1.setPadding(16, 16, 16, 16);

        Bitmap mbitmap1=((BitmapDrawable) getResources().getDrawable(R.drawable.r1)).getBitmap();
        Bitmap imageRounded1=Bitmap.createBitmap(mbitmap1.getWidth(), mbitmap1.getHeight(), mbitmap1.getConfig());
        Canvas canvas1=new Canvas(imageRounded1);
        Paint mpaint1=new Paint();
        mpaint1.setAntiAlias(true);
        mpaint1.setShader(new BitmapShader(mbitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas1.drawRoundRect((new RectF(0, 0, mbitmap1.getWidth(), mbitmap1.getHeight())), 30, 30, mpaint1);
        
        Drawable roundedDrawable1 = new BitmapDrawable(getResources(), imageRounded1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            diet1.setBackground(roundedDrawable1);
        } else {
            diet1.setBackgroundDrawable(roundedDrawable1);
        }

        TextView name1 = new TextView(this);
        name1.setText("Paleo Diet");
        name1.setTextSize(20);
        name1.setTypeface(Typeface.SANS_SERIF);
        name1.setTextColor(Color.BLACK);
        name1.setGravity(Gravity.CENTER);

        ImageView diet2 = new ImageView(this);
        diet2.setLayoutParams(layoutParams);
        diet2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        diet2.setPadding(16, 16, 16, 16);

        Bitmap mbitmap2=((BitmapDrawable) getResources().getDrawable(R.drawable.r2)).getBitmap();
        Bitmap imageRounded2=Bitmap.createBitmap(mbitmap2.getWidth(), mbitmap2.getHeight(), mbitmap2.getConfig());
        Canvas canvas2=new Canvas(imageRounded2);
        Paint mpaint2=new Paint();
        mpaint2.setAntiAlias(true);
        mpaint2.setShader(new BitmapShader(mbitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas2.drawRoundRect((new RectF(0, 0, mbitmap2.getWidth(), mbitmap2.getHeight())), 30, 30, mpaint2);
        
        Drawable roundedDrawable2 = new BitmapDrawable(getResources(), imageRounded2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            diet2.setBackground(roundedDrawable2);
        } else {
            diet2.setBackgroundDrawable(roundedDrawable2);
        }
        
        TextView name2 = new TextView(this);
        name2.setText("Vegan");
        name2.setTextSize(20);
        name2.setTypeface(Typeface.SANS_SERIF);
        name2.setTextColor(Color.BLACK);
        name2.setGravity(Gravity.CENTER);

        ImageView diet3 = new ImageView(this);
        diet3.setLayoutParams(layoutParams);
        diet3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        diet3.setPadding(16, 16, 16, 16);;

        Bitmap mbitmap3=((BitmapDrawable) getResources().getDrawable(R.drawable.r3)).getBitmap();
        Bitmap imageRounded3=Bitmap.createBitmap(mbitmap3.getWidth(), mbitmap3.getHeight(), mbitmap3.getConfig());
        Canvas canvas3=new Canvas(imageRounded3);
        Paint mpaint3=new Paint();
        mpaint3.setAntiAlias(true);
        mpaint3.setShader(new BitmapShader(mbitmap3, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas3.drawRoundRect((new RectF(0, 0, mbitmap3.getWidth(), mbitmap3.getHeight())), 30, 30, mpaint3);
        
        Drawable roundedDrawable3 = new BitmapDrawable(getResources(), imageRounded3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            diet3.setBackground(roundedDrawable3);
        } else {
            diet3.setBackgroundDrawable(roundedDrawable3);
        }

        TextView name3 = new TextView(this);
        name3.setText("Low Carbs");
        name3.setTextSize(20);
        name3.setTypeface(Typeface.SANS_SERIF);
        name3.setTextColor(Color.BLACK);
        name3.setGravity(Gravity.CENTER);


        ImageView diet4 = new ImageView(this);
        diet4.setLayoutParams(layoutParams);
        diet4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        diet4.setPadding(16, 16, 16, 16);

        Bitmap mbitmap4=((BitmapDrawable) getResources().getDrawable(R.drawable.r4)).getBitmap();
        Bitmap imageRounded4=Bitmap.createBitmap(mbitmap4.getWidth(), mbitmap4.getHeight(), mbitmap4.getConfig());
        Canvas canvas4=new Canvas(imageRounded4);
        Paint mpaint4=new Paint();
        mpaint4.setAntiAlias(true);
        mpaint4.setShader(new BitmapShader(mbitmap4, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas4.drawRoundRect((new RectF(0, 0, mbitmap4.getWidth(), mbitmap4.getHeight())), 30, 30, mpaint4);
        
        Drawable roundedDrawable4 = new BitmapDrawable(getResources(), imageRounded4);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            diet4.setBackground(roundedDrawable4);
        } else {
            diet4.setBackgroundDrawable(roundedDrawable4);
        }

        TextView name4 = new TextView(this);
        name4.setText("Dukan");
        name4.setTextSize(20);
        name4.setTypeface(Typeface.SANS_SERIF);
        name4.setTextColor(Color.BLACK);
        name4.setGravity(Gravity.CENTER);


        ImageView diet5 = new ImageView(this);
        diet5.setLayoutParams(layoutParams);
        diet5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        diet5.setPadding(16, 16, 16, 16);

        Bitmap mbitmap5=((BitmapDrawable) getResources().getDrawable(R.drawable.r5)).getBitmap();
        Bitmap imageRounded5=Bitmap.createBitmap(mbitmap5.getWidth(), mbitmap5.getHeight(), mbitmap5.getConfig());
        Canvas canvas5=new Canvas(imageRounded5);
        Paint mpaint5=new Paint();
        mpaint5.setAntiAlias(true);
        mpaint5.setShader(new BitmapShader(mbitmap5, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas5.drawRoundRect((new RectF(0, 0, mbitmap5.getWidth(), mbitmap5.getHeight())), 30, 30, mpaint5);
        
        Drawable roundedDrawable5 = new BitmapDrawable(getResources(), imageRounded5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            diet5.setBackground(roundedDrawable5);
        } else {
            diet5.setBackgroundDrawable(roundedDrawable5);
        }

        TextView name5 = new TextView(this);
        name5.setText("Atkins");
        name5.setTextSize(20);
        name5.setTypeface(Typeface.SANS_SERIF);
        name5.setTextColor(Color.BLACK);
        name5.setGravity(Gravity.CENTER);


        ll.addView(diet1);
        ll.addView(name1);
        ll.addView(diet2);
        ll.addView(name2);
        ll.addView(diet3);
        ll.addView(name3);
        ll.addView(diet4);
        ll.addView(name4);
        ll.addView(diet5);
        ll.addView(name5);

        scrl.addView(ll);
        setContentView(scrl);
        //paleo
        diet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paleodiet = new Intent(PremiumRecipe.this, paleo.class);
                startActivity(paleodiet);

            }
        });

        //vegan
        diet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vegandiet = new Intent(PremiumRecipe.this, vegan.class);
                startActivity(vegandiet);

            }
        });

        //low carbs
        diet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lowcarbdiet = new Intent(PremiumRecipe.this, Lowcarb.class);
                startActivity(lowcarbdiet);

            }
        });

        //dukan
        diet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dukandiet = new Intent(PremiumRecipe.this, dukan.class);
                startActivity(dukandiet);

            }
        });

        //atkins
        diet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atkindiet = new Intent(PremiumRecipe.this, atkins.class);
                startActivity(atkindiet);

            }
        });
    }
}
