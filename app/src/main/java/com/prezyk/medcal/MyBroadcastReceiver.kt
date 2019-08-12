package com.prezyk.medcal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.prezyk.medcal.views.SummonDroneActivity

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var activityIntent = Intent(context, SummonDroneActivity::class.java)
        context?.startActivity(activityIntent)
    }
}