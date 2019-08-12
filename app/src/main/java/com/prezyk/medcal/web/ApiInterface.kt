package com.prezyk.medcal.web

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    fun doGetSendDrone(@Query("startPointX") startPointX: Int,
                       @Query("startPointY") startPointY: Int,
                        @Query("endPointX") endPointX: Int,
                       @Query("endPointY") endPointY: Int) : Call<ResponseBody>
}