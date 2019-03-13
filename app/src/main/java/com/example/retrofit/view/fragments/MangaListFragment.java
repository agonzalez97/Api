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
import android.widget.TextView;

import com.example.retrofit.GlideApp;
import com.example.retrofit.MainViewModel;
import com.example.retrofit.R;
import com.example.retrofit.model.Character;

import java.util.ArrayList;
import java.util.List;


public class MangaListFragment extends Fragment {
    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private AnimeListAdapter mAnimeListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime_list, container, false);


        mRecyclerView = view.findViewById(R.id.animeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAnimeListAdapter = new AnimeListAdapter();
        mRecyclerView.setAdapter(mAnimeListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getCharacters().observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(@Nullable List<Character> characters) {
                mAnimeListAdapter.characterList = characters;
                mAnimeListAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

   public static class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.AnimeListViewHolder>{
        public List<Character> characterList = new ArrayList<>();

        @NonNull
        @Override
        public AnimeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime, parent, false);
            return new AnimeListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AnimeListViewHolder holder, int position) {
            Character character = characterList.get(position);

            holder.name.setText(character.name);
            holder.role.setText(character.role);
            GlideApp.with(holder.itemView.getContext()).load(character.image_url).into(holder.image_url);
        }

        @Override
        public int getItemCount() {
            return characterList.size();
        }

        class AnimeListViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView role;
            ImageView image_url;

            public AnimeListViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.characterName);
                role = itemView.findViewById(R.id.characterRole);
                image_url = itemView.findViewById(R.id.characterImg);
            }
        }
    }
}

