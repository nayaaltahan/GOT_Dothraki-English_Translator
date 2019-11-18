package com.example.got.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.got.R;
import com.example.got.model.Translate;
import com.example.got.repos.TranslateRepository;
import com.example.got.ui.main.HistoryTranslateFragment;
import com.example.got.ui.main.TranslateFragment;
import com.example.got.vm.TranslateResultViewModel;

public class TranslateResultActivity extends AppCompatActivity  implements UpdateUI{

    TranslateResultViewModel viewModel;
    EditText englishEdit;
    EditText dothrakiEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_result_activity);

        Toolbar toolbar = findViewById(R.id.result_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(TranslateResultViewModel.class);

        viewModel.setUpdateUi(this);

        englishEdit = findViewById(R.id.editEnglish);
        dothrakiEdit = findViewById(R.id.editDothraki);
        englishEdit.setEnabled(false);
        dothrakiEdit.setEnabled(false);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(TranslateFragment.EXTRA_TRANSLATE)) {
            String to_be_translated_text = bundle.getString(TranslateFragment.EXTRA_TRANSLATE);
            englishEdit.setText(to_be_translated_text);
            viewModel.translateToDothraki(englishEdit.getText().toString());
        } if (bundle != null && bundle.containsKey(HistoryTranslateFragment.EXTRA_HISTORY_TRANSLATE)){
            int translateId = bundle.getInt(HistoryTranslateFragment.EXTRA_HISTORY_TRANSLATE);
            Translate translate = viewModel.getTranslateById(translateId);
            englishEdit.setText(translate.getEnglish());
            dothrakiEdit.setText(translate.getDothraki());
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_result_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String extraText = englishEdit.getText() + " -> " + dothrakiEdit.getText();
                sendIntent.putExtra(Intent.EXTRA_TEXT, extraText);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share translate result through");
                startActivity(shareIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void updateUiWithTranslate(Translate translate) {
        dothrakiEdit.setText(translate.getDothraki());
    }

    @Override
    public void updateUiWithError(String error) {
        Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
    }
}
