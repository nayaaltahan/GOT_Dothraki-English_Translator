package com.example.got.repos;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.got.db.GOTDatabase;
import com.example.got.db.TranslateDao;
import com.example.got.model.Translate;

import java.util.ArrayList;
import java.util.List;

public class TranslateRepository {
    private TranslateDao translateDao;
    private static TranslateRepository instance;
    private LiveData<List<Translate>> translates;

    private TranslateRepository(Application application){
        GOTDatabase database = GOTDatabase.getInstance(application);
        translateDao = database.translateDao();
        translates = translateDao.getAllNotes();
    }

    public static TranslateRepository getInstance(Application application) {
        if(instance == null){
            instance = new TranslateRepository(application);
        }
        return instance;
    }

    public void deleteAllTranslates() {

        new DeleteTranslateAsync(translateDao).execute();
    }

    public void insert(Translate translate) {

        new InsertTranslateAsync(translateDao).execute(translate);
    }

    public LiveData<List<Translate>> getHistory() {

        return translates;
    }

    private static class InsertTranslateAsync extends AsyncTask<Translate,Void,Void> {
        private TranslateDao translateDao;

        private InsertTranslateAsync(TranslateDao translateDao) {
            this.translateDao = translateDao;
        }

        @Override
        protected Void doInBackground(Translate... translates) {
            translateDao.insert(translates[0]);
            return null;
        }
    }

    private static class DeleteTranslateAsync extends AsyncTask<Void,Void,Void> {
        private TranslateDao translateDao;

        private DeleteTranslateAsync(TranslateDao translateDao) {
            this.translateDao = translateDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            translateDao.deleteAllNotes();
            return null;
        }
    }
}
