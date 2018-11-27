package com.neilpirch.firebasephotos;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Help {
    public String address;
    public double lat;
    public double lon;
    public String image_path;

    public Help(){

    }

    public Help(String address, double lat, double lon, String image_path){
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.image_path = image_path;
    }

    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}

    public double getLat(){return lat;}
    public void setLat(double lat){this.lat=lat;}

    public double getLon(){return lon;}
    public void setLon(double lon){this.lon=lon;}

    public String getImage_path(){return image_path;}
    public void setImage_path(String image_path){this.image_path=image_path;}

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("lat", lat);
        result.put("lon", lon);
        result.put("image_path", image_path);

        return result;
    }
}
