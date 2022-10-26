package com.example.fuel.adapterClass;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fuel.SignInFragment;
import com.example.fuel.userLogin.User_SignUpFragment;
import com.example.fuel.stationOwnerLogin.StationOwner_SignUpFragment;

//Adapter class to connect Fragments
public class FragmentAdapter extends FragmentStateAdapter{

//    Constructor
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

//    Returning SignUp and SignIn fragments based on position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new User_SignUpFragment();
        }else if (position == 2){
            return new StationOwner_SignUpFragment();
        }
        return new SignInFragment();
    }

//    Returning total fragment count
    @Override
    public int getItemCount() {
        return 3;
    }
}
