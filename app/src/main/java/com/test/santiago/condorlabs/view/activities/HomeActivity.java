package com.test.santiago.condorlabs.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.test.santiago.condorlabs.R;
import com.test.santiago.condorlabs.model.Album;
import com.test.santiago.condorlabs.model.Artist;
import com.test.santiago.condorlabs.presenters.HomePresenter;
import com.test.santiago.condorlabs.util.Constants;
import com.test.santiago.condorlabs.util.Preferences;
import com.test.santiago.condorlabs.view.adapters.AlbumAdapter;
import com.test.santiago.condorlabs.view.dialogs.DialogLogin;
import com.test.santiago.condorlabs.view.interfaces.IHomeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeView, SearchView.OnQueryTextListener {

    @BindView(R.id.lyArtistInfo)
    FrameLayout lyArtistInfo;
    @BindView(R.id.lyContainer)
    LinearLayout lyContainer;
    @BindView(R.id.lyNoSearch)
    LinearLayout lyNoSearch;
    @BindView(R.id.lyNoResult)
    LinearLayout lyNoResult;
    @BindView(R.id.ivArtist)
    ImageView ivArtist;
    @BindView(R.id.tvArtistName)
    TextView tvArtistName;
    @BindView(R.id.tvFollowers)
    TextView tvFollowers;
    @BindView(R.id.tvError)
    TextView tvError;
    @BindView(R.id.pbPopularity)
    AnimateHorizontalProgressBar pbPopularity;
    @BindView(R.id.rvAlbums)
    RecyclerView rvAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomePresenter();
        ButterKnife.bind(this);
        presenter.addView(this);
        presenter.init();
        configActionBar();
        isExistsToken();
        overridePendingTransition(R.animator.slide_out_left, R.animator.slide_out_right);
    }

    private void isExistsToken() {
        if (Preferences.getString(this, Constants.TOKEN) == null ||
                Preferences.getString(this, Constants.TOKEN).isEmpty()) {
            login();
        }
    }

    private void configActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(1);
        }
    }

    private void login() {
        DialogLogin dialogLogin = new DialogLogin();
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogLogin.show(fragmentManager, "");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showArtist(Artist artist) {
        lyContainer.setVisibility(View.VISIBLE);
        lyNoResult.setVisibility(View.GONE);
        lyNoSearch.setVisibility(View.GONE);

        if (artist.getImages().size() > 0)
            Glide.with(this).load(artist.getImages().get(0).getUrl()).into(ivArtist);

        tvArtistName.setText(artist.getName());
        tvFollowers.setText(String.valueOf(artist.getFollowers().getTotal()));
        pbPopularity.setProgress(artist.getPopularity());
        presenter.getAlbums(artist.getId());
    }

    @Override
    public void showEmptyArtist() {
        failedSearch();
    }

    private void failedSearch() {
        lyNoResult.setVisibility(View.VISIBLE);
        lyNoSearch.setVisibility(View.GONE);
        lyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showAlbums(List<Album> albums) {
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));
        rvAlbums.setAdapter(new AlbumAdapter(this, albums));
    }

    @Override
    public void saveToken(String token) {
        Preferences.saveString(this, Constants.TOKEN, token);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        failedSearch();
        tvError.setText(message);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()) {
            presenter.search(newText);
        } else {
            lyNoResult.setVisibility(View.GONE);
            lyNoSearch.setVisibility(View.VISIBLE);
            lyContainer.setVisibility(View.GONE);
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_LOGIN_SPOTIFY) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                saveToken(response.getAccessToken());
            }else {
                login();
            }
        }
    }
}
