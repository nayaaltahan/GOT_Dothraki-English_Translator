package com.example.got.vm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.got.model.Translate;
import com.example.got.repos.TranslateRepository;

import java.util.List;

public class TranslateResultViewModel extends AndroidViewModel {
    private TranslateRepository repository;

    public TranslateResultViewModel(Application app) {
        super(app);
        repository = TranslateRepository.getInstance(app);
    }

    public Translate translateToDothraki(String text) {

        return repository.translateToDothraki(text);
    }

}
