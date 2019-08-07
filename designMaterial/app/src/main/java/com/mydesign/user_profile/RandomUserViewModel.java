package com.mydesign.user_profile;

import android.app.Application;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.TransitionManager;
import com.google.gson.Gson;
import com.mydesign.base.BaseViewModel;
import com.mydesign.base.GenericAdapter;
import com.mydesign.databinding.ProfileViewBinding;
import com.mydesign.network.DataProvider;
import com.mydesign.network.response.profilelist.Result;
import com.mydesign.network.response.profilelist.UserProfileListsModel;

import java.util.ArrayList;

public class RandomUserViewModel extends BaseViewModel<RandomUserNavigator> {

    private Application mApplication;
    private MutableLiveData<ArrayList<Result>> userLists = new MutableLiveData<>();
    private UserProfileListsModel userProfileListsModel;


    public MutableLiveData<ArrayList<Result>> getUserList() {
        if(userLists==null){
            userLists=new MutableLiveData<>(new ArrayList<>());
        }
        return userLists;
    }


    public void onDeclinePress(Animation animation, View view, GenericAdapter<Result, ProfileViewBinding> genericAdapter, Result item) {


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //toggleVisibility(view);
                ArrayList<Result> results = new ArrayList<>(genericAdapter.getList());
                results.remove(item);
                genericAdapter.updateData(results);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void onConnectPress() {

    }


    private  void toggleVisibility(View... views) {
        for(View view: views){
            boolean isVisble = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisble ? View.GONE : View.VISIBLE);
        }
    }


    public void getUserListCall(){

        mDisposable.add(DataProvider.getInstance().fetchUserProfileList(response -> {
            dialogVisibility.setValue(false);
            getUserList().setValue(response);
            Log.d("RandomActTAG","gson: "+new Gson().toJson(response));
        }, this::checkError));

    }

    @Override
    protected void checkError(Throwable throwable) {
        super.checkError(throwable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }



}
