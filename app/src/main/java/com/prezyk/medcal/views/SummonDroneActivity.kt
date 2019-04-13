package com.prezyk.medcal.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
            presenter.sendHttpRequest(this, spinner_location.selectedItemId.toInt())
            finish()
        }






    }


}