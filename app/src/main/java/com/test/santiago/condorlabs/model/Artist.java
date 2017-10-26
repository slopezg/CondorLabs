package com.test.santiago.condorlabs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Santiago on 10/25/17.
 */

public class Artist {
    private String id;
    private String name;
    private Followers followers;
    private List<Image> images = new ArrayList<>();
    private int popularity;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Followers getFollowers() {
        return followers;
    }

    public List<Image> getImages() {
        return images;
    }

    public int getPopularity() {
        return popularity;
    }
}
