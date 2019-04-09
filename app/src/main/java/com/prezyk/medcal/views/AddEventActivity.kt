package com.prezyk.medcal.views

import android.app.DatePickerDialog
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.prezyk.medcal.adapters.DrugAdapter
import com.prezyk.medcal.R
import com.prezyk.medcal.adapters.RecycleDrugAdapter
import com.prezyk.medcal.adapters.RecyclerEventsAdapter
import kotlinx.android.synthetic.main.add_event_layout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddEventActivity : AppCompatActivity() {
    //TODO add drug listview and configure all that shit...

    companion object {
        val NONE = -1
        val ONCE = 0
        val WEEKLY = 1
        val EVERYDAY = 2
    }

    val NONE = -1
    val ONCE = 0
    val WEEKLY = 1
    val EVERYDAY = 2

    private lateinit var selectedStartDate: Calendar
    private lateinit var selectedEndDate: Calendar
    private lateinit var selectedHours: Array<Calendar?>
    private lateinit var drugList: Array<String>
    private var periodic: Int = NONE


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_event_layout)

        selectedStartDate = Calendar.getInstance()
        selectedEndDate = Calendar.getInstance()

        selectedStartDate.timeInMillis = intent.extras.getLong("dateMillis")
        selectedEndDate.timeInMillis = intent.extras.getLong("dateMillis")

        val format = SimpleDateFormat("dd/MM/yyyy" )


        eventStartDateText.text = format.format(this.selectedStartDate.time)
        eventEndDateText.text = format.format(this.selectedEndDate.time)



        //TODO activity for picking hour, geting result date from started activity ("sending data to invoking activity"?)
        selectHoursBtn.setOnClickListener {
            var sHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            var sMin = Calendar.getInstance().get(Calendar.MINUTE)




            val intent = Intent(this, HourPickActivity::class.java).apply {
                putExtra("dateMillis", selectedStartDate.timeInMillis)
            }
            startActivityForResult(intent, 0)


        }

        everydayRadBtn.id = this.EVERYDAY
        onceRadBtn.id = this.ONCE
        weeklyRadBtn.id = this.WEEKLY

        periodicOptionsRadGrp.setOnCheckedChangeListener { _, checkedId -> this.periodic=checkedId }

        eventStartDateText.setOnClickListener {
            var sDay = selectedStartDate.get(Calendar.DAY_OF_MONTH)
            var sMonth = selectedStartDate.get(Calendar.MONTH)
            var sYear = selectedStartDate.get(Calendar.YEAR)

            var datePickerDialog = DatePickerDialog(this ,DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedStartDate.set(year, month, dayOfMonth)
                eventStartDateText.text = format.format(selectedStartDate.time)

            }, sYear, sMonth, sDay)

            datePickerDialog.show()

        }


        eventEndDateText.setOnClickListener {
            var sDay = selectedEndDate.get(Calendar.DAY_OF_MONTH)
            var sMonth = selectedEndDate.get(Calendar.MONTH)
            var sYear = selectedEndDate.get(Calendar.YEAR)

            var datePickerDialog = DatePickerDialog(this ,DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedEndDate.set(year, month, dayOfMonth)
                eventEndDateText.text = format.format(selectedEndDate.time)

            }, sYear, sMonth, sDay)

            datePickerDialog.show()

        }


        //TODO ehh...
        var list = ArrayList<String>()
        list.add("")

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecycleDrugAdapter(list)


        recyclerView = findViewById<RecyclerView>(R.id.drugRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==0) {

            var hoursList = data?.extras?.getSerializable(HourPickActivity.PICKED_HOURS) as ArrayList<Calendar>

            var format = SimpleDateFormat("HH:mm")

            for(c: Calendar in hoursList) {
                Log.e("NO ERROR", format.format(c.time).toString())
            }

//            var millisHour = ArrayList<Long>()
//
//            for(s: String in intent.extras.keySet()) {
//                if(s.subSequence(0, 10)=="pickedHour") {
//                    millisHour.add(intent.extras.get(s) as Long)
//                }
//            }
//
//            millisHour.sort()
//
//            selectedHours = arrayOfNulls<Calendar>(millisHour.size)
//
//
//            for(i in 1 until selectedHours.size) {
//                selectedHours[i] = Calendar.getInstance()
//                selectedHours[i]!!.timeInMillis = millisHour[i]
//                //TODO check what fuckin' !! means after variable name
//            }


        }
    }
}