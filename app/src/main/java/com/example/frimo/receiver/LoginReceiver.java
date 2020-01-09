package com.example.frimo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.frimo.utils.Constants;

public class LoginReceiver extends BroadcastReceiver {
    private IisLogin mIisLogin;

    private Bundle bundle;
    @Override
    public void onReceive(Context context, Intent intent) {
          if(intent.getAction().equals(Constants.UNLOGIN_RECEIVER_ACTION)){
              mIisLogin.setData(0);
          }else if(intent.getAction().equals(Constants.ISLOGIN_RECEIVER_ACTION)){
              mIisLogin.setData(1);
          }
    }
    public interface IisLogin {
        void setData(int tag);
    }
    public void setReceiverListener (IisLogin mIisLogin) {
        this.mIisLogin = mIisLogin;
    }
}
