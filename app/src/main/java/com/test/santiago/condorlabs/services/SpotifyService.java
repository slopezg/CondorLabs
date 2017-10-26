package com.test.santiago.condorlabs.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.santiago.condorlabs.model.Album;
import com.test.santiago.condorlabs.services.dto.ArtistResponse;
import com.test.santiago.condorlabs.services.interfaces.ISuscriptor;
import com.test.santiago.condorlabs.util.Constants;
import com.test.santiago.condorlabs.util.Preferences;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Santiago on 10/25/17.
 */

public class SpotifyService {

    private ISuscriptor iSuscriptor;
    private Context mContext;

    public SpotifyService(Context mContext, ISuscriptor iSuscriptor) {
        this.iSuscriptor = iSuscriptor;
        this.mContext = mContext;
        this.iSuscriptor = iSuscriptor;
    }


    public void search(String query) {
        String getArtistUrl = Constants.SEARCH_URL + query.replace(" ", "+") + Constants.ARTIST_REQUEST_TYPE;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getArtistUrl, null, response -> {
            ArtistResponse artistResponse = new Gson().fromJson(response.toString(), ArtistResponse.class);
            iSuscriptor.onResultArtist(artistResponse);
        }, error -> {
            try {
                iSuscriptor.onError(error);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer " + Preferences.getString(mContext, Constants.TOKEN));
                return params;
            }
        };

        BaseVolleyRequestQueue.getInstance(mContext).getRequestQueue().cancelAll(request -> true);
        BaseVolleyRequestQueue.getInstance(mContext).addToRequestQueue(jsonObjectRequest);
    }


    public void getAlbumsByArtist(String artistId) {
        String getArtistUrl = Constants.GET_ALBUMS_URL + artistId + Constants.ALBUMS_REQUEST_TYPE;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getArtistUrl, null, response -> {
            try {
                List<Album> albums = new Gson().fromJson(response.getJSONArray("items")
                        .toString(), new TypeToken<List<Album>>() {
                }.getType());
                iSuscriptor.onResultAlbums(albums);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            try {
                iSuscriptor.onError(error);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + Preferences.getString(mContext, Constants.TOKEN));
                return params;
            }
        };

        BaseVolleyRequestQueue.getInstance(mContext).addToRequestQueue(jsonObjectRequest);
    }
}
