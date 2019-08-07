package com.mydesign.network;

import com.mydesign.network.response.profilelist.Result;
import com.mydesign.network.response.profilelist.UserProfileListsModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;

public interface RemoteDataProvider {

    Disposable fetchUserProfileList(Consumer<ArrayList<Result>> success, Consumer<Throwable> error);

}
