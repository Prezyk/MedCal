package com.example.medcal

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager

class MainFragmentActivity : FragmentActivity() {
//    private lateinit var mainFragmentPagerAdapter: MainFragmentPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)


    }




}