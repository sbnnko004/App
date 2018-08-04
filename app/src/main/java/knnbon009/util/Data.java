package knnbon009.util;

import java.util.ArrayList;

public class Data{
    private int _id;
    private double litres;
    private String activity;
    public Data(String activity,double litres){
        this.litres=litres;this.activity=activity;
    }
    public Data(int _id, String activity,double litres){
        this._id=_id; this.litres=litres;this.activity=activity;
    }
    public void set_id(int _id){
        this._id=_id;
    }
    public double getLitres() {
        return litres;
    }

    public String getActivity() {
        return activity;
    }

    @Override
    public String toString() {

        return activity+": "+litres;
    }
}


