package com.example.got.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.got.R;

public class TranslateFragment extends Fragment {

    private EditText translateEditText;

    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        translateEditText = getView().findViewById(R.id.editDothraki);
        return inflater.inflate(R.layout.translation_fragment, container, false);
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





}
