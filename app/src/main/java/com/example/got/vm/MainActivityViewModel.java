package com.example.got.vm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.got.model.Translate;
import com.example.got.repos.TranslateRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private TranslateRepository repository;

    public MainActivityViewModel(Application app) {
        super(app);
        repository = TranslateRepository.getInstance(app);
    }

    public void clearHistory() {
        repository.deleteAllTranslates();
    }
}
