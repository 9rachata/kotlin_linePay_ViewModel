package com.coe.myapplication.utility


import com.coe.myapplication.model.ResponseLinePay
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  FeedData {

    @GET("/")
    fun requestLinePay(@Query("orderID") orderID:String , @Query("amount") amount:Int) : Call<ResponseLinePay>
}