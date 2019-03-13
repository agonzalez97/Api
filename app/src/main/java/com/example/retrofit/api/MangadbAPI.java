package com.example.retrofit.api;

import com.example.retrofit.model.MangaPictures;
import com.example.retrofit.model.MangaCharacterList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MangadbAPI {

    @GET("/v3/manga/15/characters")
    Call<MangaCharacterList> getCharacters();

    @GET("/v3/manga/15/pictures")
    Call<MangaPictures> getPictures();
}
