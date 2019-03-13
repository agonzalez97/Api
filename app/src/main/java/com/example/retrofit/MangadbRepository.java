package com.example.retrofit;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.retrofit.api.MangadbAPI;
import com.example.retrofit.api.MoviedbModule;
import com.example.retrofit.model.MangaPictures;
import com.example.retrofit.model.MangaCharacterList;
import com.example.retrofit.model.Picture;
import com.example.retrofit.model.Character;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangadbRepository {
    MangadbAPI mangadbAPI;

    public MangadbRepository(){
        mangadbAPI = MoviedbModule.getAPI();
    }

    public LiveData<List<Character>> getCharacters(){
       final MutableLiveData<List<Character>> lista = new MutableLiveData<>();

        mangadbAPI.getCharacters().enqueue(new Callback<MangaCharacterList>() {
            @Override
            public void onResponse(Call<MangaCharacterList> call, Response<MangaCharacterList> response) {
              lista.setValue(response.body().characters);
            }

            @Override
            public void onFailure(Call<MangaCharacterList> call, Throwable t) {
            }
        });

        return lista;
    }


    public LiveData<List<Picture>> getPictures(){
        final MutableLiveData<List<Picture>> listchar = new MutableLiveData<>();

        mangadbAPI.getPictures().enqueue(new Callback<MangaPictures>() {
            @Override
            public void onResponse(Call<MangaPictures> call, Response<MangaPictures> response) {
             listchar.setValue(response.body().pictures);
            }

            @Override
            public void onFailure(Call<MangaPictures> call, Throwable t) {
            }
        });

       return listchar;
    }


}