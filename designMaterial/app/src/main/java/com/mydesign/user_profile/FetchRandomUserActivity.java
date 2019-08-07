package com.mydesign.user_profile;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.Slide;
import androidx.transition.TransitionManager;
import com.bumptech.glide.Glide;
import com.mydesign.R;
import com.mydesign.base.BaseActivity;
import com.mydesign.base.BaseNavigator;
import com.mydesign.base.GenericAdapter;
import com.mydesign.databinding.ActivityFetchRandomUserBinding;
import com.mydesign.databinding.ProfileViewBinding;
import com.mydesign.network.response.profilelist.Result;
import com.mydesign.network.response.profilelist.UserProfileListsModel;
import com.mydesign.utils.Utils;

public class FetchRandomUserActivity extends BaseActivity<ActivityFetchRandomUserBinding, RandomUserViewModel> implements RandomUserNavigator {

    private static final String TAG = "fetchActTAG";
    private Context mContext;
    Animation animation;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fetch_random_user);

    }*/

    @Override
    public void onError(String message) {
        Utils.getInstance().showMessage(mContext, message);
    }

    @Override
    public void onNoInternet() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fetch_random_user;
    }

    @Override
    public Class getViewModel() {
        return RandomUserViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return FetchRandomUserActivity.this;
    }

    @Override
    public void onBinding() {
        mContext = this;
        mBinding.setViewModel(mViewModel);
        animation = AnimationUtils.loadAnimation(mContext,R.anim.anim_slide_exit_right);
        //animation = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.anim.anim_slide_exit_right);
        setUpListRecyclerView();
        mViewModel.getUserListCall();

    }

    @Override
    public void onDecline() {

    }

    @Override
    public void onConnect() {

    }

    private void setUpListRecyclerView(){
        mBinding.userProfileRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        GenericAdapter <Result, ProfileViewBinding> adapter =
                new GenericAdapter<Result, ProfileViewBinding>(mContext) {
                    @Override
                    public int getLayoutId() {
                        return R.layout.profile_view;
                    }

                    @Override
                    public void onBindView(ProfileViewBinding binding, Result item, int position) {

                        binding.setModel(item);
                        binding.circleImageView1.setOnClickListener(v->{
                            Toast.makeText(mContext, "decline click", Toast.LENGTH_SHORT).show();

                            mViewModel.onDeclinePress(animation,binding.constraintCardLay,this,item);
                            binding.constraintCardLay.startAnimation(animation);

                        });
                        Glide.with(mContext).load(item.getPicture().getMedium()).into(binding.circleImageView);
                    }
                };

        mBinding.userProfileRecycler.setAdapter(adapter);
        mViewModel.getUserList().observe(this,adapter::updateData);
    }


}
