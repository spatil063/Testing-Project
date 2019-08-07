package com.mydesign.network.response.profilelist;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileListsModel {

    @SerializedName("results")
    @Expose
    private ArrayList<Result> results = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
