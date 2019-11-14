package com.example.got.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.got.R;
import com.example.got.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Button translate_button;
    private EditText translateEditText;
    public static final String EXTRA_TRANSLATE = "com.example.got.ui.TRANSLATE_EDIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        translate_button = findViewById(R.id.translate_button);

        translate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.translateBtnListener();
            }
        });


    }


    public void translateBtnListener(){
        Intent intent = new Intent(MainActivity.this, TranslateResultActivity.class);
        intent.putExtra(EXTRA_TRANSLATE, translateEditText.getText().toString());
        startActivity(intent);

    }
}