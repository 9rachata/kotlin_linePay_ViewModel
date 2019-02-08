package com.coe.myapplication

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log

import com.coe.myapplication.model.ResponseLinePay
import com.coe.myapplication.utility.LinePayViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linePayFragment = LinePayFragment();
        val ft : FragmentTransaction = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, linePayFragment);
        ft.commit()

    }
}

