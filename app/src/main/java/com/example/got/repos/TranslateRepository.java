package com.example.got.repos;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.got.db.GOTDatabase;
import com.example.got.db.TranslateDao;
import com.example.got.model.Translate;
import com.example.got.rds.DothrakiApi;
import com.example.got.rds.DothrakiResponse;
import com.example.got.rds.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateRepository {
    private TranslateDao translateDao;
    private static TranslateRepository instance;
    //MutableLiveData
    private LiveData<List<Translate>> translates;
    Translate dothraki = null;

    private TranslateRepository(Application application){
        GOTDatabase database = GOTDatabase.getInstance(application);
        translateDao = database.translateDao();
        translates = translateDao.getAllNotes();
    }

    public static synchronized TranslateRepository getInstance(Application application) {
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

    public Translate translateToDothraki(String text) {
        List<Translate> history = translates.getValue();
        for(int i = 0; i<history.size();i++){
            if(history.get(i).getEnglish().trim().toLowerCase().equals(text.trim().toLowerCase())){
                return history.get(i);
            }
        }
        return getTranslationFromApi(text);
    }

    private Translate getTranslationFromApi(String text){
        DothrakiApi dothrakiApi = ServiceGenerator.getDothrakiApi();
        Call<DothrakiResponse> call = dothrakiApi.TranslateToDothraki(text);
        call.enqueue(new Callback<DothrakiResponse>() {
            @Override
            public void onResponse(Call<DothrakiResponse> call, Response<DothrakiResponse> response) {
                if (response.code() == 200) {
                    dothraki = response.body().getTranslate();
                    insert(dothraki);
                }
            }

            @Override
            public void onFailure(Call<DothrakiResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
        return dothraki;
    }
}
