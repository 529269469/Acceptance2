package com.example.acceptance2.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.acceptance2.ui.activity.main.LoginActivity;

/**
 * @author :created by ${ WYW }
 * 时间：2020/8/4 14
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
