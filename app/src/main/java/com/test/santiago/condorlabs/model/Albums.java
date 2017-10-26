package com.test.santiago.condorlabs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santiago on 10/25/17.
 */

public class Albums {
    @SerializedName("items")
    private List<Album> albums = new ArrayList<>();

    public List<Album> getAlbums() {
        return albums;
    }
}
