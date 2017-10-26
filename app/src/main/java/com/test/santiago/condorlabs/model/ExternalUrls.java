package com.test.santiago.condorlabs.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Santiago on 10/25/17.
 */

public class ExternalUrls {
    @SerializedName("spotify")
    private String url;

    public String getUrl() {
        return url;
    }
}