package com.prezyk.medcal.presenters

import com.prezyk.medcal.web.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class SendDroneBackPresenter(var view: View) {

    var startPoint = intArrayOf(1, 1)
    var endPoints = arrayOf(
        intArrayOf(1, 7),
        intArrayOf(11, 2),
        intArrayOf(11, 7),
        intArrayOf(11, 12)
    )

    val URL = "http://192.168.1.20:8080"

    var endpointIndex = 0

    fun sendRequest(index: Int) {
        this.endpointIndex = index
        view.showProgressBar()
        var okHttpClient = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()

        var retrofit = Retrofit.Builder().baseUrl(this.URL).client(okHttpClient).build()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        var call = apiInterface.doGetSendDrone(this.startPoint[0], this.startPoint[1], this.endPoints[endpointIndex][0], this.endPoints[endpointIndex][1])
        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                view.finishWithError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var responseString = String(response.body()?.bytes()!!, Charsets.UTF_8)
                if(responseString == "SUCCESS") {
                    view.hideProgessBar()
                } else {
                    view.finishWithError()
                }
            }
        })
    }

    fun sendDroneBack() {

        view.showProgressBar()
        var okHttpClient = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()

        var retrofit = Retrofit.Builder().baseUrl(this.URL).client(okHttpClient).build()

        var apiInterface = retrofit.create(ApiInterface::class.java)
        var call = apiInterface.doGetSendDrone(this.endPoints[endpointIndex][0], this.endPoints[endpointIndex][1], this.startPoint[0], this.startPoint[1])
        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                view.finishWithError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var responseString = String(response.body()?.bytes()!!, Charsets.UTF_8)
                if(responseString == "SUCCESS") {
                    view.finishWithMessage()
                } else {
                    view.finishWithError()
                }
            }
        })
    }





    interface View {
        fun showProgressBar()
        fun hideProgessBar()
        fun finishWithError()
        fun finishWithMessage()
    }
}