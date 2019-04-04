package com.example.medcal.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.medcal.R
import com.example.medcal.adapters.LocationAdapter
import kotlinx.android.synthetic.main.summon_drone_layout.*

class SummonDroneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summon_drone_layout)

        var locationArray = resources.getStringArray(R.array.location_list)

        spinner_location.adapter = LocationAdapter(this, locationArray)



        btnSummonDrone.setOnClickListener{


            val queue = Volley.newRequestQueue(this)

            //TODO do sth with setting value of param var
            var param: Char = (spinner_location.selectedItemPosition+65).toChar()
            val url = "http://10.0.2.2:8880/?location=$param"
//            val url = URL("http://httpbin.org/get")
//            val urlConnection = url.openConnection() as HttpURLConnection
            Log.d("BTN CLICKED", "SUMMON DRONE MOTHERFUCKER")


            val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                Log.d("RESPONSE RECEIVED", response)
            }, Response.ErrorListener { Log.d("RESPONSE ERROR", "FUCK, THERE'S AN FUCKIN' ERROR") })


//            urlConnection.setRequestProperty("location", spinner_location.selectedItem.toString())
//            Log.d("RESPONSE", urlConnection.inputStream.bufferedReader().use { it.readText() })

            queue.add(stringRequest)
        }






    }


}