package com.example.nguyenpro231998.navibarmenu.Clicker;



import android.support.v4.app.FragmentManager;

import com.example.nguyenpro231998.navibarmenu.Interface.Navigation;
import com.example.nguyenpro231998.navibarmenu.MainActivity;

public class FragmentNavigation implements Navigation {
    private static FragmentNavigation mInstance;
    private FragmentManager mFragmentManager;
    private MainActivity mainActivity;

    public static FragmentNavigation getmInstance(MainActivity mainActivity){
        if (mInstance == null)
            mInstance = new FragmentNavigation();
            mInstance.configure(mainActivity);
            return mInstance;

    }

    private void configure(MainActivity mainActivity) {
        mainActivity = mainActivity;
        mFragmentManager = mainActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragment(String title) {

    }
}
