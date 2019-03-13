package com.example.retrofit.view.fragments;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.retrofit.GlideApp;
import com.example.retrofit.MainViewModel;
import com.example.retrofit.R;
import com.example.retrofit.model.Picture;

import java.util.ArrayList;
import java.util.List;


public class PictureListFragment extends Fragment {
    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private PictureListFragment.CharacterListAdapter mCharacterListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);


        mRecyclerView = view.findViewById(R.id.characterList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCharacterListAdapter = new PictureListFragment.CharacterListAdapter();
        mRecyclerView.setAdapter(mCharacterListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getPictures().observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(@Nullable List<Picture> pictures) {
                mCharacterListAdapter.pictureList = pictures;
                mCharacterListAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    public static class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>{
        public List<Picture> pictureList = new ArrayList<>();

        @NonNull
        @Override
        public CharacterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
            return new CharacterListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CharacterListViewHolder holder, int position) {
          Picture picture = pictureList.get(position);
         GlideApp.with(holder.itemView.getContext()).load(picture.large).into(holder.large);
        }

        @Override
        public int getItemCount() {
            return pictureList.size();
        }

        class CharacterListViewHolder extends RecyclerView.ViewHolder {
            ImageView large;

            public CharacterListViewHolder(View itemView) {
                super(itemView);
                large = itemView.findViewById(R.id.pictureImg);

            }
        }
    }
}

