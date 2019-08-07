package com.mydesign.network;

import com.mydesign.BaseApp;
import com.mydesign.R;
import com.mydesign.network.response.profilelist.Result;
import com.mydesign.network.response.profilelist.UserProfileListsModel;
import com.mydesign.utils.NoInternetException;
import com.mydesign.utils.Utils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class DataProvider implements RemoteDataProvider {

    private static DataProvider mInstance;
    private static ApiInterface mServices;
    private static ApiInterface pullSMSService;
    private static ApiInterface tokenService;
    private static ApiInterface priceFetchService;
    private static Utils utils;

    private DataProvider() {
        mServices = ApiClient.getClient();
        utils = Utils.getInstance();
    }

    public static DataProvider getInstance() {
        if (mInstance == null) {
            synchronized (DataProvider.class) {
                if (mInstance == null) {
                    mInstance = new DataProvider();
                }
            }
        }
        return mInstance;
    }

    private boolean isNetworkAvailable() {
        return utils.isNetworkAvailable(BaseApp.getContext());
    }

    private void noInternetAvailable(Consumer<Throwable> error) {
        try {
            error.accept(new NoInternetException(BaseApp.getContext().getString(R.string.no_internet_connection)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(UserProfileListsModel baseResponse, Consumer<Throwable> error) {
        try {
            if (baseResponse.getResults().size() == 0) {
                error.accept(new Throwable(BaseApp.getContext().getString(R.string.went_wrong)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Disposable getDefaultDisposable() {
        return new Disposable() {
            @Override
            public void dispose() {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        };
    }


    @Override
    public Disposable fetchUserProfileList(Consumer<ArrayList<Result>> success, Consumer<Throwable> error) {

        if (isNetworkAvailable()) {
            return mServices.updateRequest().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userProfileListsModel -> {
                        if (userProfileListsModel == null) {
                            handleResponse(userProfileListsModel, error);
                            return;
                        }
                        success.accept(userProfileListsModel.getResults());

                    }, error);
        } else {
            noInternetAvailable(error);
            return getDefaultDisposable();
        }
    }

}
