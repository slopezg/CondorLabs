package com.test.santiago.condorlabs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santiago on 10/25/17.
 */

public class Artists {
    @SerializedName("items")
    private List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }
}
