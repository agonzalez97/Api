package com.example.retrofit.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.retrofit.MainViewModel;
import com.example.retrofit.R;
import com.example.retrofit.model.Picture;
import com.example.retrofit.model.Character;
import com.example.retrofit.view.fragments.PictureListFragment;
import com.example.retrofit.view.fragments.MangaListFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel, getmViewModel;
    private RecyclerView mRecyclerView, getmRecyclerView;
    private MangaListFragment.AnimeListAdapter mAnimeListAdapter;
    private PictureListFragment.CharacterListAdapter mCharacterListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.animeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Segunda lista
        getmRecyclerView = findViewById(R.id.characterList);
        getmRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAnimeListAdapter = new MangaListFragment.AnimeListAdapter();
        mRecyclerView.setAdapter(mAnimeListAdapter);

        //Segundo adapter
        mCharacterListAdapter = new PictureListFragment.CharacterListAdapter();
        getmRecyclerView.setAdapter(mCharacterListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getCharacters().observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(@Nullable List<Character> characters) {
              mAnimeListAdapter.characterList = characters;
             mAnimeListAdapter.notifyDataSetChanged();
            }
        });

        getmViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getmViewModel.getPictures().observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(@Nullable List<Picture> pictures) {
                mCharacterListAdapter.pictureList = pictures;
                mCharacterListAdapter.notifyDataSetChanged();
            }
        });

    }
}
