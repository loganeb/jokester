package com.example.jokesteralarmclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.*
import androidx.annotation.RequiresApi

import java.util.Calendar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private  lateinit var alarmTimePicker: TimePicker

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alarmTimePicker = findViewById(R.id.alarmTimePicker)
        var btn_cancel: Button = findViewById(R.id.cancel_alarm)
        var btn_set: Button = findViewById(R.id.set_alarm)
        btn_set.setOnClickListener(this)
        btn_cancel.setOnClickListener{Alarm_cancel()}

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View){
        var cal: Calendar = Calendar.getInstance()
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                alarmTimePicker.getHour(),
                alarmTimePicker.getMinute(),
                0);
        cal.set(Calendar.SECOND, 0)
        Alarm_set(cal.timeInMillis)
    }

    fun Alarm_set(timeInMillis: Long){
        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, Alarm::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this,0,intent,0)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        Toast.makeText(this, "Your Alarm is set.", Toast.LENGTH_SHORT).show()
    }

    fun Alarm_cancel(){
        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, Alarm::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this,"Your Alarm is Cancelled",Toast.LENGTH_SHORT).show();
    }


}