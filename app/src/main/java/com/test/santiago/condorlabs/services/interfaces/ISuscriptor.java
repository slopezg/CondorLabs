package com.test.santiago.condorlabs.services.interfaces;

import com.test.santiago.condorlabs.model.Album;
import com.test.santiago.condorlabs.services.dto.AlbumResponse;
import com.test.santiago.condorlabs.services.dto.ArtistResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by Santiago on 10/25/17.
 */

public interface ISuscriptor {

    void onError(Throwable e) throws IOException;

    void onResultArtist(ArtistResponse artistResponse);

    void onResultAlbums(List<Album> albums);
}