package com.prezyk.medcal.presenters

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


//TODO separate volley part from presenter
class SummonDronePresenter(view: View) {

    var view = view

    var locationList = arrayListOf("A", "B", "C", "D")
    var selectedOption = 0

    fun sendHttpRequest(context: Context, id: Int) {
        val queue = Volley.newRequestQueue(context)

        //TODO do sth with setting value of param var
        var param: Char = (selectedOption+65).toChar()
        val url = "http://10.0.2.2:8880/?location=$param"

        Log.d("BTN CLICKED", "SUMMON DRONE MOTHERFUCKER")


        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            Log.d("RESPONSE RECEIVED", response)
        }, Response.ErrorListener { Log.d("RESPONSE ERROR", "FUCK, THERE'S AN FUCKIN' ERROR") })

        queue.add(stringRequest)
    }

    public interface View {

    }
}