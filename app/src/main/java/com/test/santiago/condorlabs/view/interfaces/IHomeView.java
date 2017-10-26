package com.test.santiago.condorlabs.view.interfaces;

import com.test.santiago.condorlabs.model.Album;
import com.test.santiago.condorlabs.model.Artist;

import java.util.List;

/**
 * Created by Santiago on 10/25/17.
 */

public interface IHomeView extends BaseView {

    void showArtist(Artist artist);

    void showEmptyArtist();

    void showAlbums(List<Album> albums);

    void saveToken(String token);
}
