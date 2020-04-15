package com.schovancova.lunchmate.global;

import androidx.fragment.app.FragmentManager;

import com.schovancova.lunchmate.R;
import com.schovancova.lunchmate.login.ForgotPassword_Fragment;
import com.schovancova.lunchmate.login.Initial_Fragment;
import com.schovancova.lunchmate.login.Login_Fragment;
import com.schovancova.lunchmate.login.SignUp_Fragment;

public class FragmentChanger  {

    private static FragmentManager fragmentManager;
    public FragmentChanger(FragmentManager fragmentManager) {
        FragmentChanger.fragmentManager = fragmentManager;
    }
    public void change_login(){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Login_Fragment(),
                        com.schovancova.lunchmate.global.Utils.Login_Fragment).commit();
    }
    public void change_initial(){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Initial_Fragment(),
                        Utils.Initial_Fragment).commit();
    }
    public void change_register(){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new SignUp_Fragment(),
                        Utils.SignUp_Fragment).commit();
    }
    public void change_forgot_password(){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new ForgotPassword_Fragment(),
                        Utils.ForgotPassword_Fragment).commit();
    }
}
