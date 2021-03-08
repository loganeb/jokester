package com.example.jokesteralarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

public class Alarm extends BroadcastReceiver {
    private MediaPlayer mp = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Alarm","Alarm broadcast receiver triggered.");
        if(mp != null && mp.isPlaying()){
            mp.stop();
            mp.release();
            mp=null;
        }else{
            mp=MediaPlayer.create(context, R.raw.mitch_hedberg_ice_cold);
            mp.start();
        }
    }

}
