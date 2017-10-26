package com.test.santiago.condorlabs.services.dto;

import com.google.gson.annotations.SerializedName;
import com.test.santiago.condorlabs.model.Artists;

/**
 * Created by Santiago on 10/25/17.
 */

public class ArtistResponse {
    @SerializedName("artists")
    private Artists artists;

    public Artists getArtists() {
        return artists;
    }
}
