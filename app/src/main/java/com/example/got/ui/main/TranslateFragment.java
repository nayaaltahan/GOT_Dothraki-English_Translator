package com.example.got.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.got.R;
import com.example.got.ui.MainActivity;
import com.example.got.ui.TranslateResultActivity;

public class TranslateFragment extends Fragment {

    private EditText translateEditText;
    private Button translate_button;
    public static final String EXTRA_TRANSLATE = "com.example.got.ui.TRANSLATE_EDIT";
    private ViewGroup root;

    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.translation_fragment, container, false);
        translateEditText = root.findViewById(R.id.editDothraki);
        translate_button = root.findViewById(R.id.translate_button);

        translate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateBtnListener(v);
            }
        });
        return root;
    }


    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("translateText", translateEditText.getText().toString());
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String translateText = prefs.getString("translateText", "default_name");
        translateEditText.setText(translateText);
    }

    public void translateBtnListener(View v){
        Intent intent = new Intent(root.getContext(), TranslateResultActivity.class);

        intent.putExtra(EXTRA_TRANSLATE, translateEditText.getText().toString());
        startActivity(intent);

    }





}
