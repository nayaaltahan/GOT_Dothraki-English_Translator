package com.example.got.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.got.R;
import com.example.got.model.Translate;
import com.example.got.ui.main.HistoryTranslateFragment;
import com.example.got.vm.HistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    final private OnListItemClickListener mOnListItemClickListener;

    private ArrayList<Translate> mTranslates;
    private HistoryViewModel viewModel;

    public HistoryAdapter(OnListItemClickListener listener, HistoryTranslateFragment fragment){
        viewModel = ViewModelProviders.of(fragment).get(HistoryViewModel.class);
        mTranslates = new ArrayList<Translate>();
        viewModel.getHistory().observe(fragment, new Observer<List<Translate>>() {
            @Override
            public void onChanged(List<Translate> translates) {
                mTranslates.clear();
                for(Translate translate: translates){
                    mTranslates.add(translate);
                    notifyDataSetChanged();
                }
            }
        });
        mOnListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.translate_history_item, parent, false);
        return new ViewHolder(view);
    }

    public int getTranslateId(int count) {
        return mTranslates.get(count).getId();
    }


    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position) {
        viewHolder.english.setText(mTranslates.get(position).getEnglish());
        viewHolder.dothraki.setText(mTranslates.get(position).getDothraki());

    }

    @Override
    public int getItemCount() {
        return mTranslates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView english;
        TextView dothraki;

        ViewHolder(View itemView){
            super(itemView);
            english = itemView.findViewById(R.id.tv_english);
            dothraki = itemView.findViewById(R.id.tv_dothraki);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
