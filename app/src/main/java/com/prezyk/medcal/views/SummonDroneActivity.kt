package com.prezyk.medcal.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.prezyk.medcal.R
import com.prezyk.medcal.adapters.LocationAdapter
import com.prezyk.medcal.presenters.SummonDronePresenter
import kotlinx.android.synthetic.main.summon_drone_layout.*

class SummonDroneActivity : AppCompatActivity(), SummonDronePresenter.View {

    lateinit var presenter: SummonDronePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summon_drone_layout)

        this.presenter = SummonDronePresenter(this)

        var locationArray = resources.getStringArray(R.array.location_list)

        spinner_location.adapter = LocationAdapter(this, locationArray)


        btnSummonDrone.setOnClickListener{
            intent = Intent(this, SendDroneBackActivity::class.java).apply {
                putExtra("selectedOption", spinner_location.selectedItemPosition)
            }
            presenter.updateDatabase(this)
            startActivity(intent)
        }







    }


}