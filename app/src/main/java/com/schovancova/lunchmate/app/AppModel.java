package com.schovancova.lunchmate.app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.schovancova.lunchmate.global.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppModel extends ViewModel {

    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    private final MutableLiveData<Status> status = new MutableLiveData<Status>();
    public Status currentStatus;


    public boolean validateEmail(String email) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(email);

        if (email.equals("") || email.length() == 0 || !m.find()) return false;
        else return true;
    }

    public void setStatus(Status new_status) {
        currentStatus = new_status;
        status.setValue(new_status);
    }

    public LiveData<Status> getStatus() {
        return status;
    }
}
