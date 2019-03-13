package com.example.retrofit;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.retrofit.model.Picture;
import com.example.retrofit.model.Character;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private MangadbRepository mangadbRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mangadbRepository = new MangadbRepository();
    }

    public LiveData<List<Character>> getCharacters(){
        return mangadbRepository.getCharacters();
    }

    public LiveData<List<Picture>> getPictures(){
        return mangadbRepository.getPictures();
    }
}
