package com.example.got.vm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.got.model.Translate;
import com.example.got.repos.TranslateRepository;
import com.example.got.ui.UpdateUI;

import java.util.List;

public class TranslateResultViewModel extends AndroidViewModel {
    private TranslateRepository repository;

    public TranslateResultViewModel(Application app) {
        super(app);
        repository = TranslateRepository.getInstance(app);
    }

    public void translateToDothraki(String text) {
        repository.translateToDothraki(text);
    }

    public Translate getTranslateById(int id){
        return repository.getTranslateById(id);
    }

    public void setUpdateUi(UpdateUI updateUi){
        repository.setUpdateUI(updateUi);
    }

}
