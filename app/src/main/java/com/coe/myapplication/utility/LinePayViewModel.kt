package com.coe.myapplication.utility

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.coe.myapplication.model.ResponseLinePay
import com.google.gson.GsonBuilder

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LinePayViewModel (): ViewModel() {


    private var responseLinePay: MutableLiveData<ResponseLinePay>? = null

    fun getResponseLinePay(): LiveData<ResponseLinePay>{

        if(responseLinePay == null){
            responseLinePay = MutableLiveData<ResponseLinePay>();

            requestLinePay(null , null)
        }

        return responseLinePay as MutableLiveData<ResponseLinePay>;
    }


    fun requestLinePay(orderId: String?, amount: Int?){


        if(orderId != null && amount != null){
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://128.199.225.90:3000")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val feedData: FeedData = retrofit.create(FeedData::class.java)


            feedData.requestLinePay(orderId , amount).enqueue(object : Callback<ResponseLinePay> {
                override fun onFailure(call: Call<ResponseLinePay>, t: Throwable) {
                    Log.i("TAG ERROR" , t.toString());
                }

                override fun onResponse(call: Call<ResponseLinePay>, response: Response<ResponseLinePay>) {
                    responseLinePay!!.value = response.body()
                }

            })
        }

    }
}