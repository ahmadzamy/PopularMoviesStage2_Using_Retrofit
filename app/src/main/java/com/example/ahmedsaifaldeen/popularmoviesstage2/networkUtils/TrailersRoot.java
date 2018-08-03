package com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TrailersRoot {


    /**
     * id : 157336
     * results : [{"id":"5794fffbc3a36829ab00056f","iso_639_1":"en","iso_3166_1":"US","key":"2LqzF5WauAw","name":"Interstellar Movie - Official Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817a91c3a36873ea000adf","iso_639_1":"en","iso_3166_1":"US","key":"nyc6RJEEe0U","name":"Interstellar Movie - Official Teaser","site":"YouTube","size":1080,"type":"Teaser"},{"id":"57817aa69251417bfc000a58","iso_639_1":"en","iso_3166_1":"US","key":"zSWdZVtXT7E","name":"Interstellar - Trailer - Official Warner Bros. UK","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817ab4c3a36813870024b7","iso_639_1":"en","iso_3166_1":"US","key":"KlyknsTJk0w","name":"INTERSTELLAR - Own it TODAY on Blu-ray and DIGITAL HD!","site":"YouTube","size":1080,"type":"Teaser"},{"id":"57817accc3a368592500392e","iso_639_1":"en","iso_3166_1":"US","key":"0vxOhd4qlnA","name":"Interstellar Movie - Official Trailer 3","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817ada9251417c28000b02","iso_639_1":"en","iso_3166_1":"US","key":"827FNDpQWrQ","name":"Interstellar - Teaser Trailer - Official Warner Bros. UK","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817b0fc3a368592500394d","iso_639_1":"en","iso_3166_1":"US","key":"LY19rHKAaAg","name":"Interstellar \u2013 Trailer 4 \u2013 Official Warner Bros.","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5795006f92514142390035ae","iso_639_1":"en","iso_3166_1":"US","key":"Rt2LHkSwdPQ","name":"Interstellar Movie - Official Trailer 2","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5add84850e0a2608d8009256","iso_639_1":"en","iso_3166_1":"US","key":"ePbKGoIGAXY","name":"Interstellar \u2013 Trailer 3 \u2013 Official Warner Bros.","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<Trailers> results;

    public static List<TrailersRoot> arrayTrailersFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TrailersRoot>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trailers> getResults() {
        return results;
    }

    public void setResults(List<Trailers> results) {
        this.results = results;
    }
}
