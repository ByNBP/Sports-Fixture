package com.example.sportsapp.utils;

import android.os.Handler;

import android.util.Log;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sportsapp.R;


public class NavigationManager {

    private static FragmentManager manager;

    public static void setManager(FragmentManager manager) {
        NavigationManager.manager = manager;
    }

    public static void navigate(Fragment fragment, String stack) {
        FragmentTransaction transaction = manager.beginTransaction();
        int size = manager.getFragments().size();

        if (size > 0) {
            Fragment lastFragment = manager.getFragments().get(size - 1);
            lastFragment.onPause();

            Log.d("FragmentTest", "hidden");
            transaction.hide(lastFragment);
        }

        transaction.setCustomAnimations(
                R.anim.enter_animation,//enter
                R.anim.empty_animation,//exit
                R.anim.empty_animation,//popEnter
                R.anim.pop_enter_animation//popExit
        );

        transaction.add(R.id.fragmentLayout, fragment);
        transaction.addToBackStack(stack);
        transaction.commit();
    }

    public static void removeStack(FragmentManager manager, String stack) {
        manager.popBackStackImmediate(stack, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        int size = manager.getFragments().size();

        Handler handler = new Handler();
        handler.postDelayed(() -> manager.getFragments().get(size - 1).onResume(), 250);
    }
}
