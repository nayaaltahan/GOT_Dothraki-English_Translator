package com.example.got.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.EditText;

import com.example.got.R;
import com.example.got.model.Translate;
import com.example.got.ui.main.HistoryTranslateFragment;
import com.example.got.ui.main.TranslateFragment;
import com.example.got.vm.TranslateResultViewModel;

public class TranslateResultActivity extends AppCompatActivity {

    TranslateResultViewModel viewModel;
    EditText englishEdit;
    EditText dothrakiEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_result_activity);
        englishEdit = findViewById(R.id.editEnglish);
        dothrakiEdit = findViewById(R.id.editDothraki);
        viewModel = ViewModelProviders.of(this).get(TranslateResultViewModel.class);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(TranslateFragment.EXTRA_TRANSLATE)) {
            String to_be_translated_text = bundle.getString(TranslateFragment.EXTRA_TRANSLATE);
            englishEdit.setText(to_be_translated_text);
        } if (bundle != null && bundle.containsKey(HistoryTranslateFragment.EXTRA_HISTORY_TRANSLATE)){

        }
        Translate translatedText = viewModel.translateToDothraki(englishEdit.getText().toString());

    }
}
