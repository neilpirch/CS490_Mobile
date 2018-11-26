package com.neilpirch.firebasephotos;

import com.google.android.gms.maps.model.LatLng;

public class Help {
    public String address;
    public double lat;
    public double lon;
    public String filePath;

    public Help(){

    }

    public Help(String address, double lat, double lon, String filePath){
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.filePath = filePath;
    }

    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}

    public double getLat(){return lat;}
    public void setLat(double lat){this.lat=lat;}

    public double getLon(){return lon;}
    public void setLon(double lon){this.lon=lon;}

    public String getFilePath(){return filePath;}
    public void setFilePath(String filePath){this.filePath=filePath;}
}
