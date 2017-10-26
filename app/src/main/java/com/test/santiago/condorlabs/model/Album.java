package com.test.santiago.condorlabs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santiago on 10/25/17.
 */

public class Album {

    private String id;
    private String name;
    private List<Image> images = new ArrayList<>();

    @SerializedName("external_urls")
    private ExternalUrls externalUrls;

    @SerializedName("available_markets")
    private List<String> availableCountries = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Image> getImages() {
        return images;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public List<String> getAvailableCountries() {
        return availableCountries;
    }
}
