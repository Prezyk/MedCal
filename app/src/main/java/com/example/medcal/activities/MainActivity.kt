package com.example.medcal.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.medcal.R
import kotlinx.android.synthetic.main.main_view.*
import java.util.*


class MainActivity : AppCompatActivity() {

//    private val TAG = "MainActivity"
//
//    private lateinit var sectionsPageAdapter: SectionsPageAdapter
//    private lateinit var viewPager: ViewPager
//    var locations = arrayOf("Kuchnia", "Salon", "Sypialnia", "Łazienka")
//    lateinit var spinner: Spinner

//    private lateinit var btnViewEvents: Button
//    private lateinit var btnAddEvents: Button

    private lateinit var selectedDate: Calendar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)
//        Log.d(TAG, "onCreate: Starting")
//
//        spinner = findViewById(R.id.spinner)
//
//        var spinnerArrayAdapter = ArrayAdapter<String>(this, R.layout.location_dropdown, locations)
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.location_dropdown)
//        spinner.adapter =  spinnerArrayAdapter


//        sectionsPageAdapter = SectionsPageAdapter(supportFragmentManager)
//
//        viewPager = findViewById(R.id.container)
//        setUpViewPager(viewPager)

        selectedDate = Calendar.getInstance()


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth -> selectedDate = GregorianCalendar(year, month, dayOfMonth) }


        btnViewEvents.setOnClickListener {
            Log.d("BTN VIEW EVENT", "BTN CLICKED")


            val viewEventsIntent = Intent(this, ViewEventsActivity::class.java).apply {
                putExtra("dateMillis", selectedDate.timeInMillis)
            }
            startActivity(viewEventsIntent)

        }


        btnAddEvents.setOnClickListener {
            Log.d("BTN ADD EVENT", "BTN CLICKED")

            val addEventsItent = Intent(this, AddEventActivity::class.java).apply {
                putExtra("dateMillis", selectedDate.timeInMillis)
            }
            startActivity(addEventsItent)
        }


    }
//
//    private fun setUpViewPager(viewPager: ViewPager) {
//        var adapter = SectionsPageAdapter(supportFragmentManager)
//        adapter.addFragment(Tab1Fragment(), "TAB1")
//        adapter.addFragment(Tab2Fragment(), "TAB2")
//        viewPager.adapter = adapter
//    }


//    fun onClickViewEventsBtn(view: View) {
//        Log.d("BTN VIEW EVENT", "BTN CLICKED")
//    }
//
//    fun onClickAddEventsBtn(view: View) {
//        Log.d("BTN ADD EVENT", "BTN CLICKED")
//    }


}
