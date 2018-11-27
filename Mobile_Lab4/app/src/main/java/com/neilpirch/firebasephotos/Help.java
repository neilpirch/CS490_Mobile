package com.neilpirch.firebasephotos;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Help {
    private String address;
    private double lat;
    private double lon;
    private String imageUrl;
    private String mKey;

    public Help(){

    }

    public Help(String address, double lat, double lon, String imageUrl){
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.imageUrl = imageUrl;
    }

    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}

    public double getLat(){return lat;}
    public void setLat(double lat){this.lat=lat;}

    public double getLon(){return lon;}
    public void setLon(double lon){this.lon=lon;}

    public String getImageUrl(){return imageUrl;}
    public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("lat", lat);
        result.put("lon", lon);
        result.put("imageUrl", imageUrl);

        return result;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
