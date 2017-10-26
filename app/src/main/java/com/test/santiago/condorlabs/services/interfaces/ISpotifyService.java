package com.test.santiago.condorlabs.services.interfaces;

import com.test.santiago.condorlabs.util.Constants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Santiago on 10/25/17.
 */

public interface ISpotifyService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET(Constants.SEARCH_URL)
    Call<JSONObject> search(@Header("Authorization") String token,
                            @Query("q") String query,
                            @Query("type") String type);
}
