package com.test.santiago.condorlabs.presenters;

import android.app.Activity;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.test.santiago.condorlabs.model.Album;
import com.test.santiago.condorlabs.services.SpotifyService;
import com.test.santiago.condorlabs.services.dto.ArtistResponse;
import com.test.santiago.condorlabs.services.interfaces.ISuscriptor;
import com.test.santiago.condorlabs.util.Constants;
import com.test.santiago.condorlabs.util.ErrrorUtils;
import com.test.santiago.condorlabs.view.interfaces.IHomeView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Santiago on 10/25/17.
 */

public class HomePresenter extends Presenter  implements ISuscriptor{

    private ExecutorService executorService;
    private IHomeView iHomeView;

    public void search(String query) {
        Runnable runnable = () -> {
            SpotifyService spotifyService = new SpotifyService(view.getContext(), this);
            spotifyService.search(query);
        };
        this.executorService.execute(runnable);
    }

    @Override
    public void onError(Throwable e) throws IOException {
        view.showMessage(ErrrorUtils.getErrorMessage(view.getContext(), e));
    }

    @Override
    public void onResultArtist(ArtistResponse artistResponse) {
        if(artistResponse.getArtists().getArtists().size() > 0) {
            iHomeView.showArtist(artistResponse.getArtists().getArtists().get(0));
        }else {
            iHomeView.showEmptyArtist();
        }
    }

    @Override
    public void onResultAlbums(List<Album> albums) {
        iHomeView.showAlbums(albums);
    }

    @Override
    public void init() {
        iHomeView = (IHomeView) view.getContext();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void getAlbums(String artistId) {
        Runnable runnable = () -> {
            SpotifyService spotifyService = new SpotifyService(view.getContext(), this);
            spotifyService.getAlbumsByArtist(artistId);
        };
        this.executorService.execute(runnable);
    }
}
