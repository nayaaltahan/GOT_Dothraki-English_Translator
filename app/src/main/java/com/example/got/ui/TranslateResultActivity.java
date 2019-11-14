package com.example.got.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.got.R;
import com.example.got.ui.main.HistoryTranslateFragment;

public class TranslateResultActivity extends AppCompatActivity {

    EditText englishEdit;
    EditText dothrakiEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_result_activity);
        englishEdit = findViewById(R.id.editEnglish);
        dothrakiEdit = findViewById(R.id.editDothraki);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(MainActivity.EXTRA_TRANSLATE)) {
            String to_be_translated_text = bundle.getString(MainActivity.EXTRA_TRANSLATE);
            englishEdit.setText(to_be_translated_text);
        } if (bundle != null && bundle.containsKey(HistoryTranslateFragment.EXTRA_HISTORY_TRANSLATE)){

        }

    }
}
