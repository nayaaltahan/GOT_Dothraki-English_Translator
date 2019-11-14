package com.example.got.vm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.got.model.Translate;
import com.example.got.repos.TranslateRepository;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel {

    private TranslateRepository repository;

    public HistoryViewModel(Application app) {
        super(app);
        repository = TranslateRepository.getInstance(app);
    }

    public LiveData<List<Translate>> getHistory() {

        return repository.getHistory();
    }


}


