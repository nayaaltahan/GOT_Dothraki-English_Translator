package com.example.got.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.got.R;
import com.example.got.adapter.HistoryAdapter;
import com.example.got.ui.TranslateResultActivity;

public class HistoryTranslateFragment extends Fragment implements HistoryAdapter.OnListItemClickListener {

    private RecyclerView mTranslatesList;
    private RecyclerView.Adapter mHistoryAdapter;
    public static final String EXTRA_HISTORY_TRANSLATE = "com.example.got.ui.HISTORY_TRANSLATE";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mTranslatesList = getActivity().findViewById(R.id.rv);
        mTranslatesList.hasFixedSize();
        mTranslatesList.setLayoutManager(new LinearLayoutManager(getContext()));
        mHistoryAdapter = new HistoryAdapter(this, this);
        mTranslatesList.setAdapter(mHistoryAdapter);

        return inflater.inflate(R.layout.history_translate_fragment, container, false);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), TranslateResultActivity.class);
        intent.putExtra(EXTRA_HISTORY_TRANSLATE, clickedItemIndex + 1);
        startActivity(intent);
    }

}
