package com.mydesign.network;

import com.mydesign.network.response.profilelist.UserProfileListsModel;
import io.reactivex.Single;
import retrofit2.http.*;

import java.util.ArrayList;

public interface ApiInterface {

    @GET("?results=10")
    Single<UserProfileListsModel> updateRequest();

}
